package com.hospitalapp.fragments;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.hospitalapp.R;
import com.hospitalapp.models.Hospital;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HospitalDetailsFragment extends DialogFragment {

    public static HospitalDetailsFragment getInstance(Hospital hospital) {
        HospitalDetailsFragment fragment = new HospitalDetailsFragment();
        fragment.hospital = hospital;
        return fragment;
    }

    Hospital hospital;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.phones)
    TextView phones;
    @BindView(R.id.spectializations)
    TextView spectializations;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name.setText(hospital.getName());
        address.setText(hospital.getAddress());
        area.setText(hospital.getArea().getName());
        spectializations.setText(hospital.getSpecializationData());
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(hospital.getPhone());
        while (m.find()) {
            phones.setText(String.format("%s\n%s", phones.getText(), m.group()));
        }

        Linkify.TransformFilter filter = (match, url) -> url.replaceAll("/", "");

        Pattern pattern = Pattern.compile("\\+?\\d+");
        Linkify.addLinks(phones, pattern, "tel:", null, filter);
    }


    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hospital_details, container, false);
        ButterKnife.bind(this, v);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v1 -> getDialog().dismiss());
        }
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTrans);
    }
}
