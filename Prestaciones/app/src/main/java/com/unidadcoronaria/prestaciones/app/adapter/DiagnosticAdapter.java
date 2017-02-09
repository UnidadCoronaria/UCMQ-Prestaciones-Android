package com.unidadcoronaria.prestaciones.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.prestaciones.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class DiagnosticAdapter extends RecyclerView.Adapter<DiagnosticAdapter.DiagnosticViewHolder> {

    private List<Diagnostic> mList = new ArrayList<>();

    public DiagnosticAdapter(List<Diagnostic> mList) {
        this.mList = mList;
    }

    public void add(Diagnostic diagnostic){
        if(!mList.contains(diagnostic)) {
            this.mList.add(diagnostic);
            this.notifyDataSetChanged();
        }
    }

    public void remove(Diagnostic diagnostic){
        if(mList.contains(diagnostic)) {
            this.mList.remove(diagnostic);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public DiagnosticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiagnosticViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagnostic, parent, false));
    }

    @Override
    public void onBindViewHolder(DiagnosticViewHolder holder, int position) {
        final Diagnostic diagnostic = mList.get(position);
        holder.vName.setText(diagnostic.getName());
        holder.vDescription.setText(diagnostic.getDescription());
        holder.vDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(diagnostic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Diagnostic> getList(){
        return mList;
    }


    /**
     * @author Agustin.Bala
     * @since 0.0.1
     */
    public class DiagnosticViewHolder extends RecyclerView.ViewHolder {

        //region Properties
        @BindView(R.id.list_item_diagnostic_name)
        protected TextView vName;

        @BindView(R.id.list_item_diagnostic_description)
        protected TextView vDescription;

        @BindView(R.id.list_item_diagnostic_delete)
        protected View vDelete;



        //@BindView(R.id.item_Medicament_number_picker)
        //protected NumberPicker vNumberPicker;

        //endregion

        //region Constructor
        public DiagnosticViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            /*vNumberPicker.setMinValue(0);
            vNumberPicker.setMaxValue(100);
            vNumberPicker.setWrapSelectorWheel(true);*/
        }
        //endregion

    }
}
