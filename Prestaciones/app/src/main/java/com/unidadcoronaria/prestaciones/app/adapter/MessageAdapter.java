package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Message;
import com.unidadcoronaria.prestaciones.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private MessageAdapter.Callback callback;
    private List<Message> mList = new ArrayList<>();

    public MessageAdapter(MessageAdapter.Callback callback, List<Message> mList) {
        this.callback = callback;
        this.mList = mList;
    }

    public void addAll(List<Message> list) {
        this.mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void add(Message message){
        if(!mList.contains(message)) {
            this.mList.add(message);
            this.notifyDataSetChanged();
        }
    }

    public void remove(Message message){
        if(mList.contains(message)) {
            this.mList.remove(message);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageAdapter.MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MessageViewHolder holder, int position) {
        final Message message = mList.get(position);
        holder.vMessage.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Message> getList(){
        return mList;
    }


    public interface Callback{
        void onMessageClick(Message message);
    }


    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class MessageViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_message)
        protected TextView vMessage;
        //endregion

        //region Constructor
        public MessageViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        //endregion
    }


}
