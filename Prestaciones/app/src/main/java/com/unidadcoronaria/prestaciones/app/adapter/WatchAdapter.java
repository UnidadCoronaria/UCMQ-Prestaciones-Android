package com.unidadcoronaria.prestaciones.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.domain.model.WatchItem;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.viewpager.WatchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class WatchAdapter extends RecyclerView.Adapter<WatchAdapter.WatchItemHolder> {

    private List<WatchItem> mList;
    private WatchView mCallback;
    private Context mContext;
    private Integer mVisibleItems = 0;

    public WatchAdapter(Context context, WatchView callback){
        this.mCallback = callback;
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    public void add(List<WatchItem> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public WatchItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WatchItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watch, parent, false));
    }

    @Override
    public void onBindViewHolder(final WatchItemHolder holder, int position) {
        final WatchItem watchItem = mList.get(position);
        holder.vLabel.setText(watchItem.getName());
        holder.vCheckContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckState(holder.vCheck, watchItem);
            }
        });
        holder.vNote.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showNoteDialog(watchItem);
             }
           });
    }

    private void setCheckState(View view,WatchItem item){
        if(view.getVisibility() == View.VISIBLE){
            if(view.isSelected()){
                view.setSelected(false);
                view.setVisibility(View.INVISIBLE);
                mVisibleItems--;
                mCallback.onWatchItemsIncompleted();
            } else {
                item.setStatus(false);
                view.setSelected(true);
            }
        } else {
            mVisibleItems++;
            view.setVisibility(View.VISIBLE);
            view.setSelected(false);
            item.setStatus(true);
            if(mVisibleItems == mList.size()){
                mCallback.onWatchItemsCompleted();
            }
        }
    }

    private void showNoteDialog(final WatchItem watchItem){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_note, null);
        // Set up the input
        final EditText input = (EditText) view.findViewById(R.id.view_note_text);
        input.setText(watchItem.getNote());
        TextView textView = (TextView) view.findViewById(R.id.view_note_title);
        textView.setText(App.getInstance().getString(R.string.init_watch_dialog_title));
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton( mContext.getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                watchItem.setNote(input.getText().toString());
            }
        });
        builder.setNegativeButton(mContext.getString(R.string.button_close) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<WatchItem> getList() {
        return mList;
    }

    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class WatchItemHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.item_watch_check)
        protected View vCheck;
        @BindView(R.id.item_watch_check_container)
        protected View vCheckContainer;
        @BindView(R.id.item_watch_label)
        protected TextView vLabel;
        @BindView(R.id.item_watch_note)
        protected View vNote;

        //endregion

        //region Constructor
        public WatchItemHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        //endregion

    }
}
