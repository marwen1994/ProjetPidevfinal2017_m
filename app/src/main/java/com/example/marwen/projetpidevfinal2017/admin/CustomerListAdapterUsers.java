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
import android.widget.Toast;

import com.example.marwen.projetpidevfinal2017.Matdispo;
import com.example.marwen.projetpidevfinal2017.Matnondisponible;
import com.example.marwen.projetpidevfinal2017.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwen on 26/12/2017.
 */

public class CustomerListAdapterUsers extends BaseAdapter implements Filterable {
    private Activity activity;
    private Context mContext;
    private LayoutInflater inflater;
    private List<User> movieItems=null;
    private List<User> contactListFiltered=null;
    private ItemFilter mFilter = new ItemFilter();
    public CustomerListAdapterUsers(Activity activity, List<User> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        this.contactListFiltered=movieItems;
        //mContext = context ;
    }


    @Override
    public int getCount()   {
        return contactListFiltered.size();
    }

    @Override
    public Object getItem(int location) {return contactListFiltered.get(location);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.rowallusers, null);

        //  imageLoader = AppController.getInstance().getImageLoader();
        TextView groupe = (TextView) convertView.findViewById(R.id.email);
        TextView name = (TextView) convertView.findViewById(R.id.gr);
        ImageView im = (ImageView) convertView.findViewById(R.id.im);



        User m = contactListFiltered.get(position);

        groupe.setText(m.getEmail());
        Picasso.with(activity.getApplicationContext()).load(m.getImage_path()+"").into(im);
        name.setText(m.getGrpouname());
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

            final List<User> list = movieItems;

            int count = list.size();
            final ArrayList<User> nlist = new ArrayList<User>(count);

            User filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getEmail().toLowerCase().contains(filterString.toString().toLowerCase()) ||filterableString.getGrpouname().toLowerCase().contains(filterString.toString().toLowerCase())) {
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
            contactListFiltered = (ArrayList<User>) results.values;
            notifyDataSetChanged();
        }

    }
}
