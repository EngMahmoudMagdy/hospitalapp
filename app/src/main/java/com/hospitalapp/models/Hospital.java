package com.hospitalapp.models;

import java.io.Serializable;

public class Hospital implements Serializable {
    private String name, address, specializationData, phone;
    private double latitude, longitude;
    private Area area;

    public Hospital() {

    }

    public Hospital(String name, String address, String phone, double latitude, Double longitude, String specializationData, Area area) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.specializationData = specializationData;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
