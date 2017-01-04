package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MedicamentView;
import com.unidadcoronaria.prestaciones.app.activity.MedicamentActivity;
import com.unidadcoronaria.prestaciones.app.adapter.MedicamentAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.MedicamentFilterAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.MedicamentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since  0.0.1
 */
public class MedicamentFragment extends BaseFragment implements MedicamentView, MedicamentAdapter.Callback, MedicamentFilterAdapter.Callback {


    //region Variables
    @BindView(R.id.fragment_supply_autocomplete)
    AppCompatAutoCompleteTextView vSupplyAutocomplete;

    @BindView(R.id.fragment_supply_list)
    RecyclerView vSupplyList;

    private MedicamentAdapter adapter;
    private MedicamentPresenter presenter;
    private MedicalService medicalService;
    //endregions

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new MedicamentFragment();
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        medicalService = (MedicalService) getActivity().getIntent().getSerializableExtra(MedicamentActivity.MEDICAL_SERVICE_KEY);
        presenter = new MedicamentPresenter(this, getContext());
        vSupplyList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MedicamentAdapter(this, new ArrayList<Medicament>());
        vSupplyList.setAdapter(adapter);
        return view;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_supply;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        presenter.getList();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onListMedicamentRetrieved(List<Medicament> medicamentList) {
        final ArrayAdapter<Medicament> filterAdapter = new MedicamentFilterAdapter
                (getContext(),android.R.layout.select_dialog_item, medicamentList, this);
        vSupplyAutocomplete.setThreshold(3);
        vSupplyAutocomplete.setAdapter(filterAdapter);
        vSupplyAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Medicament medicament = filterAdapter.getItem(position);
                showQuantityDialog(medicament);
            }
        });

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void showLoading() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void onMedicamentClick(Medicament medicament) {
        adapter.remove(medicament);
    }

    @OnClick(R.id.fragment_supply_accept_button)
    protected void onAcceptButtonClick(View view){
        List<Medicament> supplyList = adapter.getList();
    }

    @OnClick(R.id.fragment_supply_cancel_button)
    protected void onCancelButtonClick(View view){
        getActivity().finish();
    }

    private void showQuantityDialog(final Medicament medicament){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_supply, null);
        // Set up the input
        final TextView vTextView = (TextView) view.findViewById(R.id.view_supply_title);
        vTextView.setText(getString(R.string.supply_confirmation, medicament.getName()));
        final EditText input = (EditText) view.findViewById(R.id.view_supply_quantity);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                medicament.setNumber(input.getText().toString());
                adapter.add(medicament);
                vSupplyAutocomplete.setText("");
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getActivity().getString(R.string.button_close) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}