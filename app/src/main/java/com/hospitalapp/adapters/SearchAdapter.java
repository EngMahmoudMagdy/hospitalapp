package com.hospitalapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hospitalapp.R;
import com.hospitalapp.fragments.HospitalDetailsFragment;
import com.hospitalapp.models.Hospital;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> implements Filterable {
    private FragmentActivity activity;
    private List<Hospital> list, total;
    private RecyclerView recyclerView;
    private TextView empty;

    public SearchAdapter(FragmentActivity activity, List<Hospital> list, RecyclerView recyclerView, TextView empty) {
        this.activity = activity;
        this.list = list;
        this.recyclerView = recyclerView;
        this.empty = empty;
        this.total = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(activity).inflate(R.layout.item_hospital, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Hospital hospital = list.get(position);
        holder.name.setText(hospital.getName());
        holder.address.setText(hospital.getAddress());
        holder.itemView.setOnClickListener(v -> {
            //TODO details page
            HospitalDetailsFragment.getInstance(hospital).show(activity.getSupportFragmentManager(),"hospital");
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
                String s = constraint.toString().toLowerCase();
                if (s.isEmpty())
                    list = new ArrayList<>(total);
                else {
                    list = new ArrayList<>();
                    for (Hospital hospital : total) {
                        if (hospital.getName().toLowerCase().contains(s) || hospital.getAddress().toLowerCase().contains(s)) {
                            list.add(hospital);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Hospital>) results.values;
                notifyChanges();
            }
        };
    }

    public void notifyChanges(List<Hospital> hospitals) {
        list.clear();
        list.addAll(hospitals);
        total.clear();
        total.addAll(hospitals);
        notifyChanges();
    }

    public void notifyChanges() {
        notifyDataSetChanged();
        recyclerView.setVisibility(list.isEmpty() ? GONE : VISIBLE);
        empty.setText(R.string.no_result_found);
        empty.setVisibility(list.isEmpty() ? VISIBLE : GONE);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
