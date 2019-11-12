package com.hospitalapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hospitalapp.R;
import com.hospitalapp.models.Hospital;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> implements Filterable {
    private Context context;
    private List<Hospital> list, total;

    public SearchAdapter(Context context, List<Hospital> list) {
        this.context = context;
        this.list = list;
        this.total = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_hospital, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Hospital hospital = list.get(position);
        holder.name.setText(hospital.getName());
        holder.address.setText(hospital.getAddress());
        holder.itemView.setOnClickListener(v -> {
            //TODO details page
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty())
                    list = total;
                else {
                    list = new ArrayList<>();
                for (Hospital hospital : total) {
                    if (s.toLowerCase().contains(hospital.getName()) || s.toLowerCase().contains(hospital.getAddress())) {
                    list.add(hospital);
                    }
                }}
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Hospital>) results.values;
            }
        };
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
