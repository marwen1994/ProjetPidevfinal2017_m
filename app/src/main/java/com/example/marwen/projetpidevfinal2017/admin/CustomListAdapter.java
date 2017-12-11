package com.example.marwen.projetpidevfinal2017.admin;
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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.marwen.projetpidevfinal2017.Matdispo;
import com.example.marwen.projetpidevfinal2017.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter implements Filterable{
    private Activity activity;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Matdispo> movieItems=null;
    private List<Matdispo> contactListFiltered=null;
    private ItemFilter mFilter = new ItemFilter();


    public CustomListAdapter(Activity activity, List<Matdispo> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        this.contactListFiltered=movieItems;
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
            convertView = inflater.inflate(R.layout.row2, null);

      //  imageLoader = AppController.getInstance().getImageLoader();
        TextView nom = (TextView) convertView.findViewById(R.id.nom);
        TextView categorie = (TextView) convertView.findViewById(R.id.cat);
        ImageView im = (ImageView) convertView.findViewById(R.id.im);
        ImageView im1 = (ImageView) convertView.findViewById(R.id.im1);

        // getting movie data for the row
        Matdispo m = contactListFiltered.get(position);


        // title
         nom.setText(m.getName());
         Picasso.with(activity.getApplicationContext()).load(m.getImage_path()).into(im);
        Picasso.with(activity.getApplicationContext()).load(m.getImage_path()).into(im1);
         categorie.setText(m.getQte()+"");
        // im.setImageResource(m.getCover());

       // im.setImageUrl(m.getCover(),imageLoader);

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