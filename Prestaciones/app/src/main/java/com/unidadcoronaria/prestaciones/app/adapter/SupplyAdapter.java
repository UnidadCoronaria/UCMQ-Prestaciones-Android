package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.prestaciones.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class SupplyAdapter extends RecyclerView.Adapter<SupplyAdapter.SupplyViewHolder> {

    private Callback callback;
    private List<Supply> mList = new ArrayList<>();

    public SupplyAdapter(SupplyAdapter.Callback callback, List<Supply> mList) {
        this.callback = callback;
        this.mList = mList;
    }

    public void add(Supply supply){
        if(!mList.contains(supply)) {
            this.mList.add(supply);
            this.notifyDataSetChanged();
        }
    }

    public void remove(Supply supply){
        if(mList.contains(supply)) {
            this.mList.remove(supply);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public SupplyAdapter.SupplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SupplyAdapter.SupplyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supply, parent, false));
    }

    @Override
    public void onBindViewHolder(SupplyAdapter.SupplyViewHolder holder, int position) {
        final Supply supply = mList.get(position);
        holder.vName.setText(supply.getName());
        holder.vQuantity.setText(supply.getQuantity());
        holder.vDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onSupplyClick(supply);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Supply> getList(){
        return mList;
    }


    public interface Callback{
        void onSupplyClick(Supply supply);
    }


    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class SupplyViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_supply_name)
        protected TextView vName;

        @BindView(R.id.list_item_supply_quantity)
        protected TextView vQuantity;

        @BindView(R.id.list_item_supply_delete)
        protected View vDelete;



        //@BindView(R.id.item_supply_number_picker)
        //protected NumberPicker vNumberPicker;

        //endregion

        //region Constructor
        public SupplyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            /*vNumberPicker.setMinValue(0);
            vNumberPicker.setMaxValue(100);
            vNumberPicker.setWrapSelectorWheel(true);*/
        }
        //endregion

    }
}
