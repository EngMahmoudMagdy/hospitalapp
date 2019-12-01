package com.hospitalapp.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.hospitalapp.R;
import com.hospitalapp.helpers.StaticMembers;
import com.hospitalapp.models.Interception;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hospitalapp.helpers.StaticMembers.*;

public class DrugInterceptionActivity extends AppCompatActivity {


    @BindView(R.id.drug1)
    AppCompatSpinner drug1Spinner;
    @BindView(R.id.drug2)
    AppCompatSpinner drug2Spinner;
    @BindView(R.id.interception)
    TextView interceptionText;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<String> drug1List, drug2List;
    ArrayAdapter drug1Adapter, drug2Adapter;
    String selectedDrug1, selectedDrug2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_interception);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        drug1List = new ArrayList<>();
        drug2List = new ArrayList<>();
        drug1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {p
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                if (p > 0) {
                    selectedDrug1 = drug1List.get(p);
                    drug2List.clear();
                    drug2List.add(getString(R.string.drug_2));
                    drug2List.addAll(StaticMembers.getAllDrugs());
                    drug2List.remove(selectedDrug1);
                    drug2Adapter.notifyDataSetChanged();
                    if (!selectedDrug1.equals(selectedDrug2))
                        drug2Spinner.setSelection(drug2List.indexOf(selectedDrug2));
                    if (selectedDrug2 != null && !selectedDrug2.isEmpty())
                        selectInterception();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        drug1List.clear();
        drug1List.add(getString(R.string.drug_1));
        drug1List.addAll(StaticMembers.getAllDrugs());
        drug1Adapter = getSpinnerAdapter(drug1List,this);
        drug1Spinner.setAdapter(drug1Adapter);
        drug2List.clear();
        drug2List.add(getString(R.string.drug_2));
        drug2List.addAll(StaticMembers.getAllDrugs());
        drug2Adapter = getSpinnerAdapter(drug2List,this);
        drug2Spinner.setAdapter(drug2Adapter);
        drug2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectedDrug2 = drug2List.get(position);/*
                    drug1List.clear();
                    drug1List.add(getString(R.string.drug_1));
                    drug1List.addAll(StaticMembers.getAllDrugs());
                    drug1List.remove(selectedDrug2);
                    drug1Adapter.notifyDataSetChanged();*/
                    if (selectedDrug1 != null && !selectedDrug1.isEmpty())
                        selectInterception();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void selectInterception() {
        boolean f = false;
        for (Interception interception : StaticMembers.getAllInterception()) {
            if ((interception.getDrugs().first.equals(selectedDrug1) || interception.getDrugs().second.equals(selectedDrug1)) && (interception.getDrugs().first.equals(selectedDrug2) || interception.getDrugs().second.equals(selectedDrug2))) {
                f = true;
                interceptionText.setText(interception.getDescription());
                break;
            }
        }
        if (!f)
            interceptionText.setText(R.string.no_interception);
    }

}
