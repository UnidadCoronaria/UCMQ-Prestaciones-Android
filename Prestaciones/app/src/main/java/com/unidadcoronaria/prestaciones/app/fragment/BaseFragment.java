package com.unidadcoronaria.prestaciones.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unidadcoronaria.prestaciones.app.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public abstract class BaseFragment extends Fragment {

    //region Fragment Implementation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(makeContentViewResourceId(), container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    //endregion


    //region Abstract Declaration
    protected abstract int makeContentViewResourceId();
    //endregion
}
