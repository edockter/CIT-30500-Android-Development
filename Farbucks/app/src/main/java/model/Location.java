package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ericd on 3/6/2017.
 */

@Entity
public class Location {

    @Id
    private Long Id;

    @NotNull
    private String name;

    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String latitude;
    private String longitude;
    private String storeImage;
    @Generated(hash = 1785896805)
    public Location(Long Id, @NotNull String name, String address, String city,
            String state, String zipcode, String latitude, String longitude,
            String storeImage) {
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storeImage = storeImage;
    }
    @Generated(hash = 375979639)
    public Location() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipcode() {
        return this.zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getStoreImage() {
        return this.storeImage;
    }
    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }
}
