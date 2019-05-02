package com.example.travelexperts;


import java.io.Serializable;

/** Creates a package object with the following properties
 * Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 24 2019
 */
public class Package implements Serializable {
    private int PackageId;
    private String PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgImg;
    private double PkgBasePrice, PkgAgencyCommission;

    public Package(int packageId, String pkgName, String pkgStartDate, String pkgEndDate, String pkgDesc, String pkgImg, double pkgBasePrice, double pkgAgencyCommission) {
        PackageId = packageId;
        PkgName = pkgName;
        PkgStartDate = pkgStartDate;
        PkgEndDate = pkgEndDate;
        PkgDesc = pkgDesc;
        PkgImg = pkgImg;
        PkgBasePrice = pkgBasePrice;
        PkgAgencyCommission = pkgAgencyCommission;
    }

    public int getPackageId() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        PackageId = packageId;
    }

    public String getPkgName() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        PkgName = pkgName;
    }

    public String getPkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(String pkgStartDate) {
        PkgStartDate = pkgStartDate;
    }

    public String getPkgEndDate() {
        return PkgEndDate;
    }

    public void setPkgEndDate(String pkgEndDate) {
        PkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        PkgDesc = pkgDesc;
    }

    public String getPkgImg() {
        return PkgImg;
    }

    public void setPkgImg(String pkgImg) {
        PkgImg = pkgImg;
    }

    public double getPkgBasePrice() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(double pkgBasePrice) {
        PkgBasePrice = pkgBasePrice;
    }

    public double getPkgAgencyCommission() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(double pkgAgencyCommission) {
        PkgAgencyCommission = pkgAgencyCommission;
    }
}
