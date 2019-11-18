package com.hospitalapp.models;

import java.util.List;

public class Hospital {
    private String name, address, description, specializationData;
    private Double latitude, longitude;
    private List<Specialization> specializations;
    private Area area;

    public Hospital() {

    }

    public Hospital(String name, String address, String description, Double latitude, Double longitude, Area area, List<Specialization> specializations) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
        this.specializations = specializations;
    }

    public Hospital(String name, String address, String description, Double latitude, Double longitude,String specializationData, Area area ) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.specializationData = specializationData;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
    }

    public String getSpecializationData() {
        return specializationData;
    }

    public void setSpecializationData(String specializationData) {
        this.specializationData = specializationData;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }
}
