package com.example.marwen.projetpidevfinal2017;

/**
 * Created by marwe on 10/12/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter  extends BaseAdapter implements Filterable {
    private Activity activity;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Matdispo> movieItems=null;
    private List<Matdispo> contactListFiltered=null;
    private ItemFilter mFilter = new ItemFilter();


    public BasketAdapter(Activity activity, List<Matdispo> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        this.contactListFiltered=movieItems;
        //mContext = context ;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) convertView = inflater.inflate(R.layout.row_basket, null);

        //  imageLoader = AppController.getInstance().getImageLoader();
        TextView nom = (TextView) convertView.findViewById(R.id.nom_basket);
        TextView categorie = (TextView) convertView.findViewById(R.id.qte);
        ImageView im = (ImageView) convertView.findViewById(R.id.im_basket);

        // getting movie data for the row
        Matdispo m = contactListFiltered.get(position);


        // title
         nom.setText(m.getName());
        Toast.makeText(mContext, "gggggggggg", Toast.LENGTH_SHORT).show();
         Picasso.with(activity.getApplicationContext()).load(m.getImage_path()).into(im);
         categorie.setText(m.getQte()+"");
         return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter ;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Matdispo> list = movieItems;

            int count = list.size();
            final ArrayList<Matdispo> nlist = new ArrayList<Matdispo>(count);

            Matdispo filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getName().toLowerCase().contains(filterString.toString().toLowerCase()) || String.valueOf(filterableString.getQte()).toString().toLowerCase().contains(filterString.toString().toLowerCase())) {
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
            contactListFiltered = (ArrayList<Matdispo>) results.values;
            notifyDataSetChanged();
        }

    }

}





/////////////////////ITEM FILTER CLASSSS ///////////////////////////////////////////

