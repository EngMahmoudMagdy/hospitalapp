package com.hospitalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.hospitalapp.R;
import com.hospitalapp.adapters.SearchAdapter;
import com.hospitalapp.models.Hospital;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hospitalapp.helpers.StaticMembers.LOCATION_CODE;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    SearchAdapter adapter;
    List<Hospital> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        if (savedInstanceState == null) {
            startActivityForResult(new Intent(this, MapsActivity.class), LOCATION_CODE);
        }
        list = new ArrayList<>();
        Hospital hospital = new Hospital("Dar El Foad", "October", "Dar El Foad hospital is a huge leading hospital", 13.3555, 13.4555, null);
        list.add(hospital);
        hospital = new Hospital("Dar El Foad", "October", "Dar El Foad hospital is a huge leading hospital", 13.3555, 13.4555, null);
        list.add(hospital);
        hospital = new Hospital("Dar El Foad", "October", "Dar El Foad hospital is a huge leading hospital", 13.3555, 13.4555, null);
        list.add(hospital);
        hospital = new Hospital("Dar El Foad", "October", "Dar El Foad hospital is a huge leading hospital", 13.3555, 13.4555, null);
        list.add(hospital);
        hospital = new Hospital("Dar El Foad", "October", "Dar El Foad hospital is a huge leading hospital", 13.3555, 13.4555, null);
        list.add(hospital);
        adapter = new SearchAdapter(this, list);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == LOCATION_CODE) {
                //TODO: update the recycler with new options
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
