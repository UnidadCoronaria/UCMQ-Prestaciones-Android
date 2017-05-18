package com.unidadcoronaria.prestaciones.app.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
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

    @BindView(R.id.fragment_supply_container)
    View vContainer;

    @BindView(R.id.fragment_supply_error_container)
    View vErrorContainer;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    private MedicamentAdapter adapter;
    private MedicamentPresenter presenter;
    private MedicalServiceResource medicalService;
    private List<Diagnostic> diagnostics;
    private char ecg;
    private char copaymentPaid;
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
        medicalService = (MedicalServiceResource) getActivity().getIntent().getSerializableExtra(MedicamentActivity.MEDICAL_SERVICE_KEY);
        diagnostics = (List<Diagnostic>) getActivity().getIntent().getSerializableExtra(MedicamentActivity.MEDICAL_SERVICE_DIAGNOSTIC_KEY);
        presenter = new MedicamentPresenter(this, getContext());
        vSupplyList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MedicamentAdapter(this, new ArrayList<Medicament>());
        vSupplyList.setAdapter(adapter);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getList();
                swipeContainer.setRefreshing(true);
            }
        });
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
        swipeContainer.setRefreshing(false);
        vContainer.setVisibility(View.VISIBLE);
        vErrorContainer.setVisibility(View.GONE);
        vProgress.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.GONE);
    }

    @Override
    public void onUpdateError() {
        Toast.makeText(getActivity(), "Hubo un error actualizando la prestación. Intentelo nuevamente más tarde.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdate() {
        showSuccess();
    }

    @Override
    public void onListError() {
        vContainer.setVisibility(View.GONE);
        vErrorContainer.setVisibility(View.VISIBLE);
        vProgress.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.VISIBLE);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void displayError(String message) {
        // Nothing to do
        vContainer.setVisibility(View.GONE);
        vErrorContainer.setVisibility(View.VISIBLE);
        vProgress.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.VISIBLE);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        vContainer.setVisibility(View.GONE);
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vContainer.setVisibility(View.VISIBLE);
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void onMedicamentClick(Medicament medicament) {
        adapter.remove(medicament);
    }

    @OnClick(R.id.fragment_supply_accept_button)
    protected void onAcceptButtonClick(View view){
        if(medicalService.getMedicalService().getCopayment() == 'T'){
            showCopaymentDialog();
        } else {
            medicalService.getMedicalService().setCopaymentPaid('F');
            showECGDialog();
        }
    }

    @OnClick(R.id.fragment_supply_cancel_button)
    protected void onCancelButtonClick(View view){
        getActivity().finish();
    }

    private void showQuantityDialog(final Medicament medicament){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = null;
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_supply, null);
        // Set up the input
        final TextView vTextView = (TextView) view.findViewById(R.id.view_supply_title);
        vTextView.setText(getString(R.string.supply_confirmation, medicament.getName()));
        final EditText input = (EditText) view.findViewById(R.id.view_supply_quantity);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(view);

        // Set up the buttons
        builder.setNegativeButton(getActivity().getString(R.string.button_close) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = builder.create();
        final AlertDialog finalAlertDialog = alertDialog;
        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //finalAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(s.length() > 0);
            }
        });
        builder.setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText() != null && !input.getText().toString().isEmpty()) {
                    medicament.setAmmount(Double.valueOf(input.getText().toString()));
                    adapter.add(medicament);
                    vSupplyAutocomplete.setText("");
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Ingrese una cantidad para continuar", Toast.LENGTH_LONG).show();
                    showQuantityDialog(medicament);
                }
            }
        });
        builder.show();
    }

    private void showCopaymentDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("¿El paciente abonó el copago?");
        builder.setCancelable(false);
        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                copaymentPaid = 'T';
                dialog.dismiss();
                showECGDialog();
            }
        });
        builder.setNegativeButton(getActivity().getString(R.string.no) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                copaymentPaid = 'F';
                dialog.dismiss();
                showECGDialog();
            }
        });
        builder.setNeutralButton(getActivity().getString(R.string.button_cancel) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void showECGDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("¿Se realizó un ECG?");
        builder.setCancelable(false);
        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ecg = 'T';
                List<Medicament> medicamentList = adapter.getList();
                presenter.update(medicamentList, medicalService, diagnostics, ecg, copaymentPaid);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getActivity().getString(R.string.no) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ecg = 'F';
                List<Medicament> medicamentList = adapter.getList();
                presenter.update(medicamentList, medicalService, diagnostics, ecg, copaymentPaid);
                dialog.dismiss();
            }
        });
        builder.setNeutralButton(getActivity().getString(R.string.button_cancel) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public void showSuccess(){
        getActivity().finish();
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("La prestación se actualizó correctamente.");
        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().finish();
            }
        });
        builder.show();*/
    }
}