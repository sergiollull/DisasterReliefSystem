package com.example.disasterreliefsystem;

public class GetLocation {
    private static double longitude ;
    private static double latitude;

    public GetLocation() {
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        GetLocation.longitude = longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        GetLocation.latitude = latitude;
    }
}
