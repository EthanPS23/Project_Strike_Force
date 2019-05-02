package com.example.travelexperts;

// Ethan Shipley
// Creates a country object with properties
public class Countries {
    private String CountryId, CountryName;

    public Countries(String countryId, String countryName) {
        CountryId = countryId;
        CountryName = countryName;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
}
