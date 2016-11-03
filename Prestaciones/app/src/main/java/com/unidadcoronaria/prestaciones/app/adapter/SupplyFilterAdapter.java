package com.unidadcoronaria.prestaciones.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unidadcoronaria.domain.model.Supply;
import com.unidadcoronaria.prestaciones.R;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */

public class SupplyFilterAdapter extends ArrayAdapter<Supply> {

    private Callback callback;

    public SupplyFilterAdapter(Context context, int resource, List<Supply> objects, SupplyFilterAdapter.Callback callback) {
        super(context, resource, objects);
        this.callback = callback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Supply supply = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_supply_filter, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.list_item_supply_name);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(supply.getName());
        // Return the completed view to render on screen
        return convertView;
    }

    public interface Callback{
    }


    private static class ViewHolder {
        TextView name;
    }


}
