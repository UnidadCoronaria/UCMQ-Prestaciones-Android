package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.prestaciones.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class MedicamentAdapter extends RecyclerView.Adapter<MedicamentAdapter.MedicamentViewHolder> {

    private Callback callback;
    private List<Medicament> mList = new ArrayList<>();

    public MedicamentAdapter(MedicamentAdapter.Callback callback, List<Medicament> mList) {
        this.callback = callback;
        this.mList = mList;
    }

    public void add(Medicament Medicament){
        if(!mList.contains(Medicament)) {
            this.mList.add(Medicament);
            this.notifyDataSetChanged();
        }
    }

    public void remove(Medicament Medicament){
        if(mList.contains(Medicament)) {
            this.mList.remove(Medicament);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public MedicamentAdapter.MedicamentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MedicamentAdapter.MedicamentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supply, parent, false));
    }

    @Override
    public void onBindViewHolder(MedicamentAdapter.MedicamentViewHolder holder, int position) {
        final Medicament medicament = mList.get(position);
        holder.vName.setText(medicament.getName());
        holder.vQuantity.setText(medicament.getNumber());
        holder.vDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onMedicamentClick(medicament);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Medicament> getList(){
        return mList;
    }


    public interface Callback{
        void onMedicamentClick(Medicament Medicament);
    }


    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class MedicamentViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_supply_name)
        protected TextView vName;

        @BindView(R.id.list_item_supply_quantity)
        protected TextView vQuantity;

        @BindView(R.id.list_item_supply_delete)
        protected View vDelete;



        //@BindView(R.id.item_Medicament_number_picker)
        //protected NumberPicker vNumberPicker;

        //endregion

        //region Constructor
        public MedicamentViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            /*vNumberPicker.setMinValue(0);
            vNumberPicker.setMaxValue(100);
            vNumberPicker.setWrapSelectorWheel(true);*/
        }
        //endregion

    }
}
