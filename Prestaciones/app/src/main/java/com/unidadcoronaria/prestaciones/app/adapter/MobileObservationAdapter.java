package com.unidadcoronaria.prestaciones.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.MobileObservation;
import com.unidadcoronaria.domain.model.TypeMobileObservation;
import com.unidadcoronaria.prestaciones.App;
import com.unidadcoronaria.prestaciones.R;
import com.unidadcoronaria.prestaciones.app.viewpager.GuardView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class MobileObservationAdapter extends RecyclerView.Adapter<MobileObservationAdapter.MobileObservationHolder> {

    private List<TypeMobileObservation> mList;
    private GuardView mCallback;
    private Context mContext;
    private Integer mVisibleItems = 0;

    public MobileObservationAdapter(Context context, GuardView callback){
        this.mCallback = callback;
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    public void add(List<TypeMobileObservation> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public MobileObservationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MobileObservationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watch, parent, false));
    }

    @Override
    public void onBindViewHolder(final MobileObservationHolder holder, int position) {
        final TypeMobileObservation mobileObservation = mList.get(position);
        holder.vLabel.setText(mobileObservation.getName());
        holder.vCheckContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckState(holder.vCheck, mobileObservation);
            }
        });
        holder.vNote.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showNoteDialog(mobileObservation);
             }
           });
    }

    private void setCheckState(View view, TypeMobileObservation item){
        if(view.getVisibility() == View.VISIBLE){
            if(view.isSelected()){
                view.setSelected(false);
                view.setVisibility(View.INVISIBLE);
                mVisibleItems--;
                mCallback.onWatchMobileObservationItemsIncompleted();
            } else {
                //TODO nuevo elemento con status
                //item.setStatus(false);
                view.setSelected(true);
            }
        } else {
            mVisibleItems++;
            view.setVisibility(View.VISIBLE);
            view.setSelected(false);
            //TODO nuevo elemento con status
            //item.setStatus(true);
            if(mVisibleItems == mList.size()){
                mCallback.onMobileObservationItemsCompleted();
            }
        }
    }

    private void showNoteDialog(final TypeMobileObservation mobileObservation){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_note, null);
        // Set up the input
        final EditText input = (EditText) view.findViewById(R.id.view_note_text);
        //TODO Create object to send mobileObservation
        //input.setText(mobileObservation.getObservation());
        TextView textView = (TextView) view.findViewById(R.id.view_note_title);
        textView.setText(App.getInstance().getString(R.string.init_watch_dialog_title));
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton( mContext.getString(R.string.button_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //mobileObservation.setObservation(input.getText().toString());
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

    public List<TypeMobileObservation> getList() {
        return mList;
    }

    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class MobileObservationHolder extends RecyclerView.ViewHolder {

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
        public MobileObservationHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        //endregion

    }
}
