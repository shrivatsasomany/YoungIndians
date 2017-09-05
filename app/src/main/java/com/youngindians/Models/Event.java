package com.youngindians.Models;

/**
 * Created by shrivatsa on 14/08/17.
 */

public class Event {
    private int id;
    private String name;
    private String venue;
    private String event_time;
    private String vertical;
    private String latitude;
    private String longitude;
    private String address;

    public Event() {

    }

    public Event(String name, String venue, String event_time, String vertical, String latitude, String longitude, String address) {
        this.name = name;
        this.venue = venue;
        this.event_time = event_time;
        this.vertical = vertical;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", venue='" + venue + '\'' +
                ", event_time='" + event_time + '\'' +
                ", vertical='" + vertical + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
