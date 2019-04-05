package sample.Styles;

import java.sql.Date;

public class Booking {

    /*
     * Title: Booking object for bookingDetails tab
     * Date: March 24/2019
     * Author: James Cockriell
     */

    private Date tripStart;
    private Date tripEnd;
    private String description;
    private String destination;
    // you are going to receive this value as a char and need to convert it.
    private String basePrice;
    // you are going to receive this value as a char and need to convert it.
    private String agencyComission;
    private String regionId;
    private String classId;
    private String feeId;

    public Booking(Date tripStart, Date tripEnd, String description, String destination, String basePrice, String agencyComission, String regionId, String classId, String feeId) {
        this.tripStart = tripStart;
        this.tripEnd = tripEnd;
        this.description = description;
        this.destination = destination;
        this.basePrice = basePrice;
        this.agencyComission = agencyComission;
        this.regionId = regionId;
        this.classId = classId;
        this.feeId = feeId;
    }

    public Date getTripStart() {
        return tripStart;
    }

    public void setTripStart(Date tripStart) {
        this.tripStart = tripStart;
    }

    public Date getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(Date tripEnd) {
        this.tripEnd = tripEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getAgencyComission() {
        return agencyComission;
    }

    public void setAgencyComission(String agencyComission) {
        this.agencyComission = agencyComission;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }
}
