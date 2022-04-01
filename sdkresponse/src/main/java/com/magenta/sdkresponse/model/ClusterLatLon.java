package com.magenta.sdkresponse.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterLatLon implements  ClusterItem {

    private Double  latitude =0.0;
    private Double  longitude =0.0;
    private String  id;
    private String  name ;
    private String  number;
    private String  status;
    private String  address;
    private String  marker;
    private String  deactive;
    private LatLng  position;

    public ClusterLatLon(Double latitude, Double longitude) {
      this.position = new LatLng(latitude,longitude);
    }

    public ClusterLatLon(Double latitude, Double longitude, String id, String name, String number, String status, String address, String marker, String deactive, LatLng position) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.name = name;
        this.number = number;
        this.status = status;
        this.address = address;
        this.marker = marker;
        this.deactive = deactive;
        this.position = position;
    }

    public LatLng getPosition() {
        return new LatLng(latitude,longitude);
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getDeactive() {
        return deactive;
    }

    public void setDeactive(String deactive) {
        this.deactive = deactive;
    }


}
