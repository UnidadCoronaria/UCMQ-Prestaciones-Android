package com.unidadcoronaria.prestaciones.app.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.SplashView;
import com.unidadcoronaria.prestaciones.app.activity.SupplyActivity;
import com.unidadcoronaria.prestaciones.app.presenter.SplashPresenter;
import com.unidadcoronaria.prestaciones.util.location.LocationService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceDetailFragment extends BaseFragment implements OnMapReadyCallback, LocationService.Callback {


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
    @BindView(R.id.fragment_medical_detail_observations)
    TextView vObservations;
    @BindView(R.id.fragment_medical_detail_cancel_button)
    Button vCancel;

    private GoogleMap mGoogleMap;
    private Marker mDefaultMarker;
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
        medicalService = (MedicalService) getActivity().getIntent().getSerializableExtra(ListMedicalServicePendingFragment.MEDICAL_SERVICE_KEY);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fragment.getMapAsync(this);
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }
    }

    @Override
    protected int makeContentViewResourceId() {
        return R.layout.fragment_detail_medical_service;
    }

    @OnClick(R.id.fragment_medical_detail_cancel_button)
    protected void onCancelClick(View view){
        startActivity(SupplyActivity.getStartIntent(getContext()));
    }

    private void initView() {
        vInfo.setText(getString(R.string.medical_service_detail_info, medicalService.getSex(), medicalService.getAge()));
        vName.setText(medicalService.getName());
        vSymptom.setText(medicalService.getSymptom());
        vObservations.setText(medicalService.getObservations());
        vAddress.setText(medicalService.getMedicalServiceAddress().getStreet());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap != null) {
            final LatLng latLng = new LatLng(-34.7251985, -58.2608728);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
            mDefaultMarker = mGoogleMap.addMarker(new MarkerOptions().position(latLng));
            LocationService.getInstance().initLocationService(getActivity(), this);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        if (mGoogleMap != null) {
            final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
            mDefaultMarker.remove();
            mGoogleMap.addMarker(new MarkerOptions()
                    .title(medicalService.getMedicalServiceAddress().getStreet())
                    .snippet("Distancia: 2km")
                    .position(latLng));
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LocationService.PERMISSION_ACCESS_LOCATION) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                LocationService.getInstance().initLocationService(getActivity(), this);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}