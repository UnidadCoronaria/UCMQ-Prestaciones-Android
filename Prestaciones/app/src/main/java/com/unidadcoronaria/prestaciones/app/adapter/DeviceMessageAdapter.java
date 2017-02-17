package com.unidadcoronaria.prestaciones.app.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.DeviceMessage;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class DeviceMessageAdapter extends RecyclerView.Adapter<DeviceMessageAdapter.DeviceMessageViewHolder> {

    private List<DeviceMessage> mList = new ArrayList<>();

    public DeviceMessageAdapter(List<DeviceMessage> mList) {
        this.mList = mList;
    }

    public void addAll(List<DeviceMessage> list) {
        this.mList.clear();
        this.mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void add(DeviceMessage DeviceMessage){
        if(!mList.contains(DeviceMessage)) {
            this.mList.add(DeviceMessage);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public DeviceMessageAdapter.DeviceMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceMessageAdapter.DeviceMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(DeviceMessageViewHolder holder, int position) {
        final DeviceMessage deviceMessage = mList.get(position);
        holder.vDeviceMessage.setText(deviceMessage.getMessage());
        Date date = new Date(deviceMessage.getDateTime());
        if(DateUtil.isToday(deviceMessage.getDateTime())) {
            holder.vDeviceMessageDate.setText(DateUtil.getConvertedHourString(date));
        } else {
            holder.vDeviceMessageDate.setText(DateUtil.getConvertedHourString(date)+" "+DateUtil.getConvertedDayString(date));
        }
        holder.vContainer.setSelected('T' == deviceMessage.getSendCallcenter());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if('T' == deviceMessage.getSendCallcenter()) {
                holder.vDeviceMessageDate.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                holder.vContainer.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            } else {
                holder.vDeviceMessageDate.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                holder.vContainer.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<DeviceMessage> getList(){
        return mList;
    }


    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class DeviceMessageViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_message)
        protected TextView vDeviceMessage;

        @BindView(R.id.list_item_message_date)
        protected TextView vDeviceMessageDate;

        @BindView(R.id.list_item_message_container)
        protected View vContainer;

        //endregion

        //region Constructor
        public DeviceMessageViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        //endregion
    }


}
