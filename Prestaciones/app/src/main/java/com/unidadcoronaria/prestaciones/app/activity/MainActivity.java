package com.unidadcoronaria.prestaciones.app.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.unidadcoronaria.domain.model.Provider;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.MainView;
import com.unidadcoronaria.prestaciones.app.activity.event.OnUserChange;
import com.unidadcoronaria.prestaciones.app.fragment.BaseFragment;
import com.unidadcoronaria.prestaciones.app.fragment.MedicalServiceListFragment;
import com.unidadcoronaria.prestaciones.app.presenter.MainPresenter;
import com.unidadcoronaria.prestaciones.util.Constants;
import com.unidadcoronaria.prestaciones.util.SessionHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class MainActivity extends BaseNavActivity implements MainView {

    private MainPresenter presenter;
    private AppCompatSpinner spinner;
    private List<Provider> providerList = new ArrayList<>();
    private String notificationType;
    private MedicalServiceListFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this, this);
        notificationType = getActivity().getIntent().getStringExtra(Constants.NOTIFICATION_TYPE);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        if(fragment.isAdded()) {
            getSupportFragmentManager().putFragment(bundle, "myfragment", fragment);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        fragment = (MedicalServiceListFragment) getSupportFragmentManager().getFragment(bundle,"myfragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_base_fragment, fragment).commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.onResume();
        if(notificationType != null){
            openMessagesScreen();
            notificationType = null;
        }
    }


    @Override
    public void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected BaseFragment getFragment() {
        fragment = (MedicalServiceListFragment) MedicalServiceListFragment.newInstance();
        return fragment;
    }


    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_login:
                changeUser(true);
                return true;
            case android.R.id.home:
                doBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        doBack();
    }

    private void doBack(){
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.activity_base_fragment);
        if(!(f instanceof MedicalServiceListFragment)) {
            openMedicalServices();
        } else {
            getActivity().finish();
        }
    }

    @Override
    protected Boolean locationEnabled() {
        return true;
    }

    public void changeUser(Boolean isCancelable){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_change_user, null);
        // Set up the input
        spinner = (AppCompatSpinner) view.findViewById(R.id.view_change_user_spinner);
        ArrayAdapter<Provider> adapter = new ArrayAdapter<>(this, R.layout.item_person, providerList);
        spinner.setAdapter(adapter);
        Integer selectedIndex = 0;
        if(!SessionHelper.getUser().isEmpty()) {
            for (Provider provider : providerList) {
                if (provider.getProviderId().equals(Integer.valueOf(SessionHelper.getUser()))) {
                    selectedIndex = providerList.indexOf(provider);
                }
            }
        }
        spinner.setSelection(selectedIndex);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton( getActivity().getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(spinner.getSelectedItem() != null) {
                    Provider provider = (Provider) spinner.getSelectedItem();
                    SessionHelper.saveProvider(provider);
                    EventBus.getDefault().post(new OnUserChange());
                    dialog.dismiss();
                }
            }
        });
        if(isCancelable) {
            builder.setNegativeButton(getActivity().getString(R.string.button_close), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        builder.setCancelable(isCancelable);
        builder.show();

    }

    @Override
    public void onProviderListRetrieved(List<Provider> providerList) {
        Boolean isUserLogged = Boolean.FALSE;
        if(!SessionHelper.getUser().isEmpty()){
            for (Provider provider: providerList ) {
                if(provider.getProviderId().equals(Integer.valueOf(SessionHelper.getUser()))){
                    isUserLogged = true;
                    SessionHelper.saveProvider(provider);
                }
            }
        }
        this.providerList = providerList;
        if(!isUserLogged){
            changeUser(false);
            Log.i("MainActivity", "Usuario invalido");
        }
    }

    @Override
    public void onProviderListErrorRetrieved() {
        Log.e("MainActivity", "Error gettings provider list");
    }
}

