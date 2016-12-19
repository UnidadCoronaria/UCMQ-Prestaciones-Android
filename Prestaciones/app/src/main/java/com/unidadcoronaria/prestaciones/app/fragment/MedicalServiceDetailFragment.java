package com.unidadcoronaria.prestaciones.app.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.domain.model.directions.Point;
import com.unidadcoronaria.domain.model.directions.Route;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MedicalServiceDetailView;
import com.unidadcoronaria.prestaciones.app.activity.SupplyActivity;
import com.unidadcoronaria.prestaciones.app.presenter.MedicalServiceDetailPresenter;
import com.unidadcoronaria.prestaciones.app.type.MedicalServiceStatus;
import com.unidadcoronaria.prestaciones.util.LocationHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceDetailFragment extends BaseFragment implements OnMapReadyCallback, MedicalServiceDetailView {


    //region Variables
    private MedicalService medicalService;
    @BindView(R.id.fragment_medical_detail_address)
    TextView vAddress;
    @BindView(R.id.fragment_medical_detail_name)
    TextView vName;
    @BindView(R.id.fragment_medical_detail_info)
    TextView vInfo;
    @BindView(R.id.fragment_medical_detail_symptom)
    TextView vSymptom;
    @BindView(R.id.fragment_medical_detail_container)
    View vContainer;
    @BindView(R.id.fragment_medical_detail_buttons)
    View vButtonsContainer;
    @BindView(R.id.fragment_medical_detail_observations_icon)
    View vObservations;
    @BindView(R.id.fragment_medical_detail_sheet)
    View vSheet;
    @BindView(R.id.map)
    View vMap;
    @BindView(R.id.fragment_medical_detail_arrow)
    ImageView vArrow;

    private GoogleMap mGoogleMap;
    private MedicalServiceDetailPresenter presenter;
    private LatLng origin;
    private LatLng destination;
    private Marker markerDestination;
    private BottomSheetBehavior behavior;
    private SupportMapFragment supportMapFragment;
    //endregion

    //region Constructors implementations
    public static BaseFragment newInstance() {
        return new MedicalServiceDetailFragment();
    }
    //endregion

    //region Lifecycle implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new MedicalServiceDetailPresenter(this, getActivity());
        medicalService = (MedicalService) getActivity().getIntent().getSerializableExtra(ListMedicalServicePendingFragment.MEDICAL_SERVICE_KEY);
        initView();
        behavior = BottomSheetBehavior.from(vSheet);
        vMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    vArrow.setImageResource(R.drawable.ic_arrow_down);
                } else {
                    vArrow.setImageResource(R.drawable.ic_arrow_up);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
                vArrow.setImageResource(R.drawable.ic_arrow_down);
            }
        });
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            supportMapFragment.getMapAsync(this);
            fm.beginTransaction().replace(R.id.map, supportMapFragment).commit();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_detail_medical_service;
    }

    @OnClick(R.id.fragment_medical_detail_second_button)
    protected void onFirstClick(View view){
        //TODO Get proximo accion n1 y mandar ese estado al server
    }

    @OnClick(R.id.fragment_medical_detail_first_button)
    protected void onSecondClick(View view){
       // if(medicalService.getStatus().equals(MedicalServiceStatus.DONE)) {
            startActivity(SupplyActivity.getStartIntent(getContext(), medicalService));
       // } else {
            //TODO Get proximo accion n2 y mandar ese estado al server
       // }
    }

    private void initView() {
        vInfo.setText(getString(R.string.medical_service_detail_info, medicalService.getSex(), medicalService.getAge()));
        vName.setText(medicalService.getName());
        vSymptom.setText(medicalService.getSymptom());
        if(medicalService.getObservations().isEmpty()){
            vObservations.setVisibility(View.GONE);
        } else {
            vObservations.setVisibility(View.VISIBLE);
        }
        vAddress.setText(medicalService.getMedicalServiceAddress().getStreet());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap != null) {
            origin = new LatLng(Double.parseDouble(LocationHelper.getLatitude()), Double.parseDouble(LocationHelper.getLongitude()));
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 14));
            if(!LocationHelper.getLatitude().isEmpty() && !LocationHelper.getLongitude().isEmpty()){
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude()))));
            }
            destination = new LatLng(medicalService.getMedicalServiceAddress().getLatitude(), medicalService.getMedicalServiceAddress().getLongitude());
            markerDestination = mGoogleMap.addMarker(new MarkerOptions()
                    .title(medicalService.getMedicalServiceAddress().getStreet())
                    .position(destination));
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mGoogleMap.setMyLocationEnabled(true);

            presenter.getRoute(origin, medicalService);
        }
    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void drawDirections(Route route) {
        PolylineOptions rectOptions = new PolylineOptions().width(5).color(ContextCompat.getColor(getActivity(), R.color.colorPrimary)).geodesic(true);
        for (Point point : route.getPoints()) {
            rectOptions.add(new LatLng(point.getLat(), point.getLng()));
        }
        com.google.android.gms.maps.model.Polyline polyline = mGoogleMap.addPolyline(rectOptions);
        markerDestination.setSnippet(route.getDistance());
    }

    @OnClick(R.id.fragment_medical_detail_arrow)
    public void onArrowClick(View view){
        if(behavior.getState()  == BottomSheetBehavior.STATE_EXPANDED){
            vArrow.setImageResource(R.drawable.ic_arrow_down);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            vArrow.setImageResource(R.drawable.ic_arrow_up);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }


    @OnClick(R.id.fragment_medical_detail_observations_icon)
    public void onObservationClick(View view){
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.observations))
                .setMessage(medicalService.getObservations())
                .setCancelable(true)
                .setPositiveButton(R.string.button_accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}