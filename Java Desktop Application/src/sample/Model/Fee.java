package sample.Model;

public class Fee {

    /*
     * Title: Fee object for bookingDetails tab
     * Date: April 05/2019
     * Author: James Cockriell
     */

    private String FeeId;
    private String FeeName;
    private double FeeAmt;
    private String FeeDesc;

    public Fee(String feeId, String feeName, double feeAmt, String feeDesc) {
        FeeId = feeId;
        FeeName = feeName;
        FeeAmt = feeAmt;
        FeeDesc = feeDesc;
    }

    public String getFeeId() {
        return FeeId;
    }

    public void setFeeId(String feeId) {
        FeeId = feeId;
    }

    public String getFeeName() {
        return FeeName;
    }

    public void setFeeName(String feeName) {
        FeeName = feeName;
    }

    public double getFeeAmt() {
        return FeeAmt;
    }

    public void setFeeAmt(double feeAmt) {
        FeeAmt = feeAmt;
    }

    public String getFeeDesc() {
        return FeeDesc;
    }

    public void setFeeDesc(String feeDesc) {
        FeeDesc = feeDesc;
    }

    @Override
    public String toString() {
        //return FeeName +  "Amt: " + FeeAmt + " " + "\"" + FeeId + "\"";
        return FeeName;
    }
}
