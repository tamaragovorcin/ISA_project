package com.isaproject.isaproject.DTO;

public class AddressWithGeoCordsDTO {
    private String town;

    private String street;

    private int number;

    private int postalCode;

    private String country;

    private Double longitude;

    private Double latitude;

    public AddressWithGeoCordsDTO() {
    }

    public AddressWithGeoCordsDTO(String town, String street, int number, int postalCode, String country, Double longitude, Double latitude) {
        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
