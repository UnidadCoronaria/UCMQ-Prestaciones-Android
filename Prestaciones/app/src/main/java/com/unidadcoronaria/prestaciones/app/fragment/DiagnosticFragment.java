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
import android.widget.Toast;

import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.DiagnosticView;
import com.unidadcoronaria.prestaciones.app.MedicamentView;
import com.unidadcoronaria.prestaciones.app.activity.DiagnosticActivity;
import com.unidadcoronaria.prestaciones.app.activity.MedicamentActivity;
import com.unidadcoronaria.prestaciones.app.adapter.DiagnosticAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.DiagnosticFilterAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.MedicamentAdapter;
import com.unidadcoronaria.prestaciones.app.adapter.MedicamentFilterAdapter;
import com.unidadcoronaria.prestaciones.app.presenter.DiagnosticPresenter;
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
public class DiagnosticFragment extends BaseFragment implements DiagnosticView {


    //region Variables
    @BindView(R.id.fragment_diagnostic_autocomplete)
    AppCompatAutoCompleteTextView vSupplyAutocomplete;

    @BindView(R.id.fragment_diagnostic_list)
    RecyclerView vSupplyList;

    private DiagnosticAdapter adapter;
    private DiagnosticPresenter presenter;
    private MedicalServiceResource medicalService;
    //endregions

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new DiagnosticFragment();
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        medicalService = (MedicalServiceResource) getActivity().getIntent().getSerializableExtra(DiagnosticActivity.MEDICAL_SERVICE_KEY);
        presenter = new DiagnosticPresenter(this, getContext());
        vSupplyList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DiagnosticAdapter(new ArrayList<Diagnostic>());
        vSupplyList.setAdapter(adapter);
        return view;
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_diagnostic;
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
    public void onListDiagnosticRetrieved(List<Diagnostic> diagnostics) {
        final ArrayAdapter<Diagnostic> filterAdapter = new DiagnosticFilterAdapter
                (getContext(),android.R.layout.select_dialog_item, diagnostics);
        vSupplyAutocomplete.setThreshold(3);
        vSupplyAutocomplete.setAdapter(filterAdapter);
        vSupplyAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Diagnostic diagnostic = filterAdapter.getItem(position);
                adapter.add(diagnostic);
                vSupplyAutocomplete.setText("");
            }
        });

    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getActivity(), "Hubo un error obteniendo la lista de diagnosticos. Intentelo nuevamente más tarde.", Toast.LENGTH_LONG).show();
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vProgress.setVisibility(View.GONE);
    }

    @OnClick(R.id.fragment_diagnostic_accept_button)
    protected void onAcceptButtonClick(View view){
        startActivity(MedicamentActivity.getStartIntent(getContext(), medicalService, new ArrayList<>(adapter.getList())));
        getActivity().finish();
    }

    @OnClick(R.id.fragment_diagnostic_cancel_button)
    protected void onCancelButtonClick(View view){
        getActivity().finish();
    }

}