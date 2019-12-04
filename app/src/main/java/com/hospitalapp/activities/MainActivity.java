package com.hospitalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hospitalapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.searchButton)
    Button searchButton;
    @BindView(R.id.drugInturruption)
    Button drugInturruption;
    @BindView(R.id.changeData)
    Button changeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        searchButton.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        drugInturruption.setOnClickListener(v -> startActivity(new Intent(this, DrugInterceptionActivity.class)));
    }
}
