package com.hospitalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hospitalapp.R;
import com.hospitalapp.adapters.SearchAdapter;
import com.hospitalapp.helpers.StaticMembers;
import com.hospitalapp.models.Area;
import com.hospitalapp.models.Hospital;
import com.hospitalapp.models.Specialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hospitalapp.helpers.StaticMembers.LAT;
import static com.hospitalapp.helpers.StaticMembers.LOCATION_CODE;
import static com.hospitalapp.helpers.StaticMembers.LONG;
import static com.hospitalapp.helpers.StaticMembers.getAllAreas;
import static com.hospitalapp.helpers.StaticMembers.getAllHospitals;
import static com.hospitalapp.helpers.StaticMembers.getAllSpec;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.emptyText)
    TextView emptyText;
    @BindView(R.id.areaGroup)
    RadioGroup areaGroup;
    @BindView(R.id.specGroup)
    RadioGroup specGroup;
    @BindView(R.id.filter)
    CardView filter;
    SearchAdapter adapter;
    List<Hospital> list, totalList;

    @BindView(R.id.drawer)
    DrawerLayout drawer;
    int selectedAreaId = -1, selectedSpecId = -1;
    private String selectedSearchWord = "";

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
        list = StaticMembers.getAllHospitals();
        totalList = StaticMembers.getAllHospitals();
        adapter = new SearchAdapter(this, list, recycler, emptyText);
        recycler.setAdapter(adapter);
        //Add the data to the filter

        updateLayout();

        //Filter button to filter now
        filter.setOnClickListener(view -> {

            List<Hospital> hospitals = new ArrayList<>();
            // FILTER Areas first
            for (Hospital hospital : getAllHospitals()) {
                if (selectedAreaId > -1) {
                    if (hospital.getArea().getId() == selectedAreaId) {
                        hospitals.add(hospital);
                    }
                } else {
                    //there is no selection of a specific area then Add all the hospitals that were arranged by location for the first time
                    hospitals.addAll(totalList);
                    break;
                }
            }

            // Now we need to remove from hospitals the unselected specialization
            //List will iterate through it one by one

            Iterator itr = hospitals.iterator();
            while (itr.hasNext()) {
                Hospital hospital = (Hospital) itr.next();
                if (selectedSpecId > -1) {
                    if (hospital.getSpecializations() != null) {
                        boolean hasThisSpecialization = false;
                        for (Specialization specialization : hospital.getSpecializations()) {
                            if (specialization.getId() == selectedSpecId)
                                hasThisSpecialization = true;
                        }

                        //If this hospital doesn't have this specialization, the flag will be false and then this hospital will be removed from my data
                        if (!hasThisSpecialization)
                            itr.remove();

                    }
                    //If this hospital doesn't have any specializations, this hospital will be removed from my data
                    else itr.remove();
                }
            }
            //Refresh the list
            adapter.notifyChanges(hospitals);

            //Close the drawer to see results
            if (drawer.isDrawerOpen(GravityCompat.END))
                drawer.closeDrawer(GravityCompat.END);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == LOCATION_CODE) {
                double lat = data.getDoubleExtra(LAT, 0);
                double longi = data.getDoubleExtra(LONG, 0);
                Collections.sort(totalList, (a, b) ->
                {
                    double aDist = calcDistance(a, lat, longi);
                    double bDist = calcDistance(b, lat, longi);
                    return (aDist - bDist) > 0 ? 1 : (aDist - bDist) < 0 ? -1 : 0;
                });
                list.clear();
                list.addAll(totalList);
                adapter.notifyChanges();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END))
            drawer.closeDrawer(GravityCompat.END);
        else
            super.onBackPressed();

    }


    void updateLayout() {
        areaGroup.removeAllViews();
        for (Area area : getAllAreas()) {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(this).inflate(R.layout.item_radio, null, false);
            radioButton.setText(area.getName());
            radioButton.setTag("" + area.getId());
            areaGroup.addView(radioButton);
        }
        areaGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            selectedAreaId = Integer.parseInt((String) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()).getTag());
        });
        specGroup.removeAllViews();
        for (Specialization specialization : getAllSpec()) {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(this).inflate(R.layout.item_radio, null, false);
            radioButton.setText(specialization.getName());
            radioButton.setTag("" + specialization.getId());
            specGroup.addView(radioButton);
        }
        specGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            selectedSpecId = Integer.parseInt((String) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()).getTag());
        });
    }

    private double calcDistance(Hospital a, double lat, double longi) {
        float[] result1 = new float[3];
        android.location.Location.distanceBetween(lat, longi, a.getLatitude(), a.getLongitude(), result1);
        return result1[0];
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
                selectedSearchWord = newText;
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                drawer.openDrawer(GravityCompat.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
