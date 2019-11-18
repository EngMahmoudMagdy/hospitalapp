package com.hospitalapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;
import com.hospitalapp.R;
import com.hospitalapp.helpers.PrefManager;
import com.hospitalapp.helpers.StaticMembers;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.searchButton)
    Button searchButton;
    @BindView(R.id.drugInturruption)
    Button drugInturruption;
    @BindView(R.id.changeData)
    Button changeData;

    public static final int SETTINGS_CODE = 1552;
    String[] nextLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        searchButton.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        drugInturruption.setOnClickListener(v -> startActivity(new Intent(this, DrugInterceptionActivity.class)));
        changeData.setOnClickListener(view -> {
            try {
                File csvfile = new File(Environment.getExternalStorageDirectory() + "/spec_tagamoa.csv");
                CSVReader reader = new CSVReader(new FileReader(csvfile));
                List<String> hospitals = new ArrayList<>();
                while ((nextLine = reader.readNext()) != null) {
                    hospitals.add(nextLine[0]);
                }
                PrefManager.getInstance(this).setList(StaticMembers.SPEC+12, hospitals);
               /* List<Hospital> hospitals = new ArrayList<>();
                while ((nextLine = reader.readNext()) != null) {
                    LatLng latLng = getLatLngFromExpandedUrl(nextLine[3]);
                    Log.i("CSV", nextLine[0] + " " + (nextLine[1]) + " "*//* + nextLine[2] + " " *//* + latLng + " ");
                    Hospital hospital = new Hospital(nextLine[0],
                            (nextLine[6] != null && !nextLine[6].isEmpty()) ? nextLine[6] + "," + nextLine[4] : nextLine[4],
                            "",
                            latLng.latitude,
                            latLng.longitude, nextLine[2],
                            new Area(nextLine[4], Integer.parseInt(nextLine[5])));
                    hospitals.add(hospital);
                }
                PrefManager.getInstance(this).setList(StaticMembers.HOSPITALS, hospitals);*/
            } catch (IOException e) {
                Log.e("CSV", Objects.requireNonNull(e.getLocalizedMessage()));
                requestCameraPermission();
            }
        });

    }

    private LatLng getLatLngFromExpandedUrl(String url) {
        Pattern pattern = Pattern.compile("/@(.*?([0-9]?[0-9]\\.[0-9]*),([0-9]?[0-9]\\.[0-9]*).*)/");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            double lat = Double.parseDouble(Objects.requireNonNull(matcher.group(2)));
            double longitude = Double.parseDouble(Objects.requireNonNull(matcher.group(3)));
            return new LatLng(lat, longitude);
        }
        return null;
    }

    private boolean isUrlFullExpanded(String url) {
        return url.contains("place/") && !url.contains("place//") && url.contains("/@");
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean requestCameraPermission() {
        String[] PERMISSIONS = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, SETTINGS_CODE);
        }
        return hasPermissions(this, PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SETTINGS_CODE && grantResults.length > 1) {
            if (
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                changeData.performClick();
            }
        }

    }
}
