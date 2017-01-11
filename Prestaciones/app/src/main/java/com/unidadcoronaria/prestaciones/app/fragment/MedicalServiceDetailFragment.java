package com.unidadcoronaria.prestaciones.app.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.unidadcoronaria.domain.model.MedicalServiceResource;
import com.unidadcoronaria.domain.model.directions.Point;
import com.unidadcoronaria.domain.model.directions.Route;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MedicalServiceDetailView;
import com.unidadcoronaria.prestaciones.app.activity.MedicamentActivity;
import com.unidadcoronaria.prestaciones.app.presenter.MedicalServiceDetailPresenter;
import com.unidadcoronaria.prestaciones.util.LocationHelper;
import com.unidadcoronaria.prestaciones.util.MedicalServiceStatusHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceDetailFragment extends BaseFragment implements OnMapReadyCallback, MedicalServiceDetailView {


    //region Variables
    @BindView(R.id.fragment_medical_detail_address)
    TextView vAddress;
    @BindView(R.id.fragment_medical_detail_name)
    TextView vName;
    @BindView(R.id.fragment_medical_detail_info)
    TextView vInfo;
    @BindView(R.id.fragment_medical_detail_first_button)
    Button vFirstButton;
    @BindView(R.id.fragment_medical_detail_second_button)
    Button vSecondButton;
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
    private Marker markerOrigin;
    private Marker markerDestination;
    private BottomSheetBehavior behavior;
    private SupportMapFragment supportMapFragment;
    private MedicalServiceResource medicalService;
    private com.google.android.gms.maps.model.Polyline polyline;
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
        medicalService = (MedicalServiceResource) getActivity().getIntent().getSerializableExtra(ListMedicalServicePendingFragment.MEDICAL_SERVICE_KEY);
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


    private void initView() {
        vInfo.setText(getString(R.string.medical_service_detail_info, medicalService.getMedicalService().getSex(), medicalService.getMedicalService().getAge()));
        vName.setText(medicalService.getMedicalService().getName());
        vAddress.setText(medicalService.getMedicalService().getAddressMedicalService().getStreet());
        vObservations.setVisibility(View.GONE);
        checkButtonsStatus();
    }

    private void checkButtonsStatus() {
        if(medicalService.getAuthorizedStates() != null && medicalService.getAuthorizedStates().size() > 0){
            setFirstButtonClick(medicalService.getAuthorizedStates().get(0));
            if( medicalService.getAuthorizedStates().size() > 1) {
                setSecondButtonClick(medicalService.getAuthorizedStates().get(1));
            } else {
                vSecondButton.setVisibility(View.GONE);
            }
        } else {
            vSecondButton.setVisibility(View.GONE);
            vFirstButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap != null) {
           drawMap();
        }
    }

    private void drawMap(){
        origin = new LatLng(Double.parseDouble(LocationHelper.getLatitude()), Double.parseDouble(LocationHelper.getLongitude()));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 14));
        if(markerOrigin != null){
            markerOrigin.remove();
        }
        if(destination != null){
            markerDestination.remove();
        }
        if(!LocationHelper.getLatitude().isEmpty() && !LocationHelper.getLongitude().isEmpty()){
            markerOrigin = mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude()))));
        }
        destination = new LatLng(medicalService.getMedicalService().getAddressMedicalService().getLatitude(), medicalService.getMedicalService().getAddressMedicalService().getLongitude());
        markerDestination = mGoogleMap.addMarker(new MarkerOptions()
                .title(medicalService.getMedicalService().getAddressMedicalService().getStreet())
                .position(destination));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);

        presenter.getRoute(origin, medicalService.getMedicalService());
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getActivity(), "Hubo un error obteniendo la información. Intentelo nuevamente más tarde.", Toast.LENGTH_LONG).show();
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
    public void drawDirections(Route route) {
        if(polyline != null){
            polyline.remove();
        }
        PolylineOptions rectOptions = new PolylineOptions().width(5).color(ContextCompat.getColor(getActivity(), R.color.colorPrimary)).geodesic(true);
        for (Point point : route.getPoints()) {
            rectOptions.add(new LatLng(point.getLat(), point.getLng()));
        }
        polyline = mGoogleMap.addPolyline(rectOptions);
        markerDestination.setSnippet(route.getDistance());
    }

    @Override
    public void onMedicalServiceUpdate(MedicalServiceResource medicalServiceResource) {
        this.medicalService = medicalServiceResource;
        initView();
        if (mGoogleMap != null) {
            drawMap();
        }
        Toast.makeText(getActivity(), "La prestación fue actualizada correctamente.", Toast.LENGTH_LONG).show();
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

    public void setSecondButtonClick(final Integer state) {
        vSecondButton.setText(MedicalServiceStatusHelper.getStatusName(state));
        vSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state == 6) {
                    startActivity(MedicamentActivity.getStartIntent(getContext(), medicalService));
                } else {
                    presenter.updateState(state, medicalService);
                }
            }
        });
    }

    public void setFirstButtonClick(final Integer state) {
        vFirstButton.setText(MedicalServiceStatusHelper.getStatusName(state));
        vFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state == 6) {
                    startActivity(MedicamentActivity.getStartIntent(getContext(), medicalService));
                } else {
                    presenter.updateState(state, medicalService);
                }
            }
        });
    }
}