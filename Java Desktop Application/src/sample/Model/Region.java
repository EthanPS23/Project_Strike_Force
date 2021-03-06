package sample.Model;

public class Region {

    /*
     * Title: Region object for bookingDetails tab
     * Date: March 24/2019
     * Author: James Cockriell
     */



    private String RegionId;
    private String RegionName;

    public Region(String regionId, String regionName) {
        RegionId = regionId;
        RegionName = regionName;
    }

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    @Override
    public String toString() {
        //return RegionName + "\"" + RegionId + "\"";
        return RegionName;
    }
}
