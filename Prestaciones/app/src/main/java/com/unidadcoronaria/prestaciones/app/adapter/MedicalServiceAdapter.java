package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.MedicalService;
import com.unidadcoronaria.prestaciones.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class MedicalServiceAdapter extends RecyclerView.Adapter<MedicalServiceAdapter.MedicalServiceViewHolder> {

    private Callback callback;
    private List<MedicalService> mList = new ArrayList<>();

    public MedicalServiceAdapter(Callback callback, List<MedicalService> mList) {
        this.callback = callback;
        this.mList = mList;
    }

    public void addAll(List<MedicalService> medicalServiceList){
        this.mList.clear();
        this.mList.addAll(medicalServiceList);
        this.notifyDataSetChanged();
    }

    public void add(MedicalService medicalServiceList){
        this.mList.add(medicalServiceList);
        this.notifyDataSetChanged();
    }

    public void removeElement(MedicalService medicalService){
        this.mList.remove(medicalService);
        notifyDataSetChanged();
    }

    @Override
    public MedicalServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MedicalServiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_service, parent, false));
    }

    @Override
    public void onBindViewHolder(MedicalServiceViewHolder holder, int position) {
        final MedicalService medicalService = mList.get(position);
        holder.vAddress.setText(medicalService.getMedicalServiceAddress().getStreet());
        holder.vSymptom.setText(medicalService.getName());
        holder.vAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onMedicalServiceClick(medicalService);
            }
        });
        holder.vSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onMedicalServiceClick(medicalService);
            }
        });
        holder.vContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onMedicalServiceClick(medicalService);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface Callback{
        void onMedicalServiceClick(MedicalService medicalService);
    }



    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class MedicalServiceViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_address)
        protected TextView vAddress;

    
        @BindView(R.id.list_item_symptom)
        protected TextView vSymptom;

        @BindView(R.id.list_item_container)
        protected View vContainer;


        //endregion

        //region Constructor
        public MedicalServiceViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        //endregion

    }
}
