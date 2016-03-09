package com.amugika.progressexample;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anartzmugika on 23/2/16.
 */
public class Mountain implements Parcelable {

    private String name;
    private String province;
    private String range; //Mendi zerra
    private String altitude;
    private String prominence;
    private double relevance;
    private boolean active;
    private double our_distance;
    private double lon;
    private double lat;
    private String dms;
    private int climbs;

    public Mountain(String name, String province, String range, String altitude, double lat, double lon,
                    String dms, String prominence, double relevance, boolean active, double our_distance, int climbs)
    {
        setName(name);
        setProvince(province);
        setRange(range);
        setAltitude(altitude);
        setLat(lat);
        setLng(lon);
        setDms(dms);
        setProminence(prominence);
        setRelevance(relevance);
        setActive(active);
        setOur_distance(our_distance);
        setClimbs(climbs);
    }

    public Mountain(Parcel in) {

        readFromParcel(in);
    }

    public int getClimbs()
    {
        return this.climbs;
    }

    public void setClimbs (int climbs)
    {
        this.climbs = climbs;
    }

    public double getLng() {
        return lon;
    }

    public void setLng(double lng) {
        this.lon = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDms() {
        return dms;
    }

    public void setDms(String dms) {
        this.dms = dms;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getProminence() {
        return prominence;
    }

    public void setProminence(String prominence) {
        this.prominence = prominence;
    }

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    public double getOur_distance() {
        return our_distance;
    }

    public void setOur_distance(double our_distance) {

        this.our_distance = Math.round(our_distance * 100.0) / 100.0;
    }

    @Override
    public String toString()
    {
        System.out.println("**************************************************************");
        return "Izena: " + this.name + "\n" +
                "Mendi Zerra: " + this.range + "\n"+
                "Garaiera: " + this.altitude + "\n" +
                "Kokapena: " + this.lat + "/" + this.lon + "\n";

    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(province);
        dest.writeString(range);
        dest.writeString(altitude);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeString(dms);
        dest.writeString(prominence);
        dest.writeDouble(relevance);
        dest.writeBooleanArray(new boolean[]{active});
        dest.writeDouble(our_distance);
        dest.writeInt(climbs);
    }

    public void readFromParcel(Parcel in)
    {
        name = in.readString();
        province = in.readString();
        range = in.readString();
        altitude = in.readString();
        lat = in.readDouble();
        lon = in.readDouble();
        dms = in.readString();
        prominence = in.readString();
        relevance = in.readDouble();
        boolean[] temp = new boolean[1];
        in.readBooleanArray(temp);
        active = temp[0];
        our_distance = in.readDouble();
        climbs = in.readInt();
    }

    public static JSONArray getMountainJsonArray(String json, boolean from_server)
    {
        JSONObject object;
        try {
            if (from_server)
            {
                object = new JSONObject(json);
                // Getting JSON Array node
                return object.getJSONArray("mountain_list");
            }
            else
            {
                return new JSONArray(json);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final Creator<Mountain> CREATOR
            = new Creator<Mountain>() {
        public Mountain createFromParcel(Parcel in) {
            return new Mountain(in);
        }

        public Mountain[] newArray(int size) {
            return new Mountain[size];
        }
    };

}
