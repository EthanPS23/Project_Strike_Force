package sample.Model;

import java.time.LocalDate;
import java.util.Date;

public class Package {
    private Integer packageId;
    private String pkgName;
    private LocalDate pkgStartDate;
    private LocalDate pkgEndDate;
    private String pkgDesc;
    private Float pkgBasePrice;
    private Float pkgAgencyCommission;

    public Package(Integer packageId, String pkgName, LocalDate pkgStartDate, LocalDate pkgEndDate, String pkgDesc, Float pkgBasePrice, Float pkgAgencyCommission) {
        this.packageId = packageId;
        this.pkgName = pkgName;
        this.pkgStartDate = pkgStartDate;
        this.pkgEndDate = pkgEndDate;
        this.pkgDesc = pkgDesc;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public LocalDate getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(LocalDate pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    public LocalDate getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(LocalDate pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public Float getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(Float pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public Float getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(Float pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }
}
