package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.DeviceMessage;
import com.unidadcoronaria.prestaciones.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class DeviceMessageAdapter extends RecyclerView.Adapter<DeviceMessageAdapter.DeviceMessageViewHolder> {

    private DeviceMessageAdapter.Callback callback;
    private List<DeviceMessage> mList = new ArrayList<>();

    public DeviceMessageAdapter(DeviceMessageAdapter.Callback callback, List<DeviceMessage> mList) {
        this.callback = callback;
        this.mList = mList;
    }

    public void addAll(List<DeviceMessage> list) {
        this.mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void add(DeviceMessage DeviceMessage){
        if(!mList.contains(DeviceMessage)) {
            this.mList.add(DeviceMessage);
            this.notifyDataSetChanged();
        }
    }

    public void remove(DeviceMessage DeviceMessage){
        if(mList.contains(DeviceMessage)) {
            this.mList.remove(DeviceMessage);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public DeviceMessageAdapter.DeviceMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceMessageAdapter.DeviceMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(DeviceMessageAdapter.DeviceMessageViewHolder holder, int position) {
        final DeviceMessage deviceMessage = mList.get(position);
        holder.vDeviceMessage.setText(deviceMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<DeviceMessage> getList(){
        return mList;
    }


    public interface Callback{
    }


    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class DeviceMessageViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_message)
        protected TextView vDeviceMessage;
        //endregion

        //region Constructor
        public DeviceMessageViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        //endregion
    }


}
