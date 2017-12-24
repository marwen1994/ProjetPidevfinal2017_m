package com.example.marwen.projetpidevfinal2017.admin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.marwen.projetpidevfinal2017.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwe on 22/12/2017.
 */

public class DemGrpAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private Context mContext;
    private LayoutInflater inflater;
    private List<String> movieItems = null;
    private List<String> contactListFiltered = null;
    private ItemFilter mFilter = new ItemFilter();


    public DemGrpAdapter(Activity activity, List<String> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        this.contactListFiltered = movieItems;
        //mContext = context ;
    }

    @Override
    public int getCount() {
        return contactListFiltered.size();
    }

    @Override
    public Object getItem(int location) {
        return contactListFiltered.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_grp, null);

        //  imageLoader = AppController.getInstance().getImageLoader();
        TextView grpname = (TextView) convertView.findViewById(R.id.nom_grp);





        grpname.setText(contactListFiltered.get(position));


        return convertView;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<String> list = movieItems;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString.toString().toLowerCase())) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contactListFiltered = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    }

}
