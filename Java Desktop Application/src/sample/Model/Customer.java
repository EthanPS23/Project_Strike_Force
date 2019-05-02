// Author: Chris Potvin
// Date: May 1st, 2019
// About: This is the customers class with its private fields and constructors. Along with its getters and setters.

package sample.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer  {

    private SimpleIntegerProperty CustomerId;
    private SimpleStringProperty CustFirstName;
    private SimpleStringProperty CustLastName;
    private SimpleStringProperty CustAddress;
    private SimpleStringProperty CustCity;
    private SimpleStringProperty CustProvince;
    private SimpleStringProperty CustPostal;
    private SimpleStringProperty CustCountry;
    private SimpleStringProperty CustHomePhone;
    private SimpleStringProperty CustBusPhone;
    private SimpleStringProperty CustEmail;

    public Customer(Integer custId, String custFirstName, String custLastName,
                    String custAddress, String custCity,
                    String custProv, String custPostal,
                    String custCountry,
                    String custHomePhone, String custBusPhone,
                    String custEmail) {

        this.CustomerId = new SimpleIntegerProperty(custId);
        this.CustFirstName = new SimpleStringProperty(custFirstName);
        this.CustLastName = new SimpleStringProperty(custLastName);
        this.CustAddress = new SimpleStringProperty(custAddress);
        this.CustCity = new SimpleStringProperty(custCity);
        this.CustProvince = new SimpleStringProperty(custProv);
        this.CustPostal = new SimpleStringProperty(custPostal);
        this.CustCountry = new SimpleStringProperty(custCountry);
        this.CustHomePhone = new SimpleStringProperty(custHomePhone);
        this.CustBusPhone = new SimpleStringProperty(custBusPhone);
        this.CustEmail = new SimpleStringProperty(custEmail);
    }

    public int getCustomerId() {
        return CustomerId.get();
    }

    public void setCustomerId(int customerId) {
        this.CustomerId.set(customerId);
    }

    public String getCustFirstName() {
        return CustFirstName.get();
    }

    public void setCustFirstName(String custFirstName) {
        this.CustFirstName.set(custFirstName);
    }

    public String getCustLastName() {
        return CustLastName.get();
    }

    public void setCustLastName(String custLastName) {
        this.CustLastName.set(custLastName);
    }

    public String getCustAddress() {
        return CustAddress.get();
    }


    public void setCustAddress(String custAddress) {
        this.CustAddress.set(custAddress);
    }

    public String getCustCity() {
        return CustCity.get();
    }


    public void setCustCity(String custCity) {
        this.CustCity.set(custCity);
    }

    public String getCustProv() {
        return CustProvince.get();
    }

    public void setCustProv(String custProv) {
        this.CustProvince.set(custProv);
    }

    public String getCustPostal() {
        return CustPostal.get();
    }


    public void setCustPostal(String custPostal) {
        this.CustPostal.set(custPostal);
    }

    public String getCustCountry() {
        return CustCountry.get();
    }

    public void setCustCountry(String custCountry) {
        this.CustCountry.set(custCountry);
    }

    public String getCustHomePhone() {
        return CustHomePhone.get();
    }

    public void setCustHomePhone(String custHomePhone) {
        this.CustHomePhone.set(custHomePhone);
    }

    public String getCustBusPhone() {
        return CustBusPhone.get();
    }

    public void setCustBusPhone(String custBusPhone) {
        this.CustBusPhone.set(custBusPhone);
    }

    public String getCustEmail() {
        return CustEmail.get();
    }

    public void setCustEmail(String custEmail) {
        this.CustEmail.set(custEmail);
    }
}
