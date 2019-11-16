package com.hospitalapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.hospitalapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugInterceptionActivity extends AppCompatActivity {


    @BindView(R.id.drug1)
    AppCompatSpinner drug1;
    @BindView(R.id.drug2)
    AppCompatSpinner drug2;
    @BindView(R.id.interception)
    TextView interception;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_interception);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
