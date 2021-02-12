package com.isaproject.isaproject.DTO;

public class GeographicCoordinatesDTO {
    private Double latitude;
    private Double longitude;

    public GeographicCoordinatesDTO(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeographicCoordinatesDTO() {
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
