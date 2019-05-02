package model;

import java.io.Serializable;
import javax.persistence.*;


/** Ethan Shipley
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int customerId;

	public Customer(int customerId, int agentId, String custAddress, String custBusPhone, String custCity,
			String custCountry, String custEmail, String custFirstName, String custHomePhone, String custLastName,
			String custPassword, String custPostal, String custProv, String custUserId) {
		super();
		this.customerId = customerId;
		this.agentId = agentId;
		this.custAddress = custAddress;
		this.custBusPhone = custBusPhone;
		this.custCity = custCity;
		this.custCountry = custCountry;
		this.custEmail = custEmail;
		this.custFirstName = custFirstName;
		this.custHomePhone = custHomePhone;
		this.custLastName = custLastName;
		this.custPassword = custPassword;
		this.custPostal = custPostal;
		this.custProv = custProv;
		this.custUserId = custUserId;
	}

	private int agentId;

	private String custAddress;

	private String custBusPhone;

	private String custCity;

	private String custCountry;

	private String custEmail;

	private String custFirstName;

	private String custHomePhone;

	private String custLastName;

	private String custPassword;

	private String custPostal;

	private String custProv;

	private String custUserId;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustBusPhone() {
		return this.custBusPhone;
	}

	public void setCustBusPhone(String custBusPhone) {
		this.custBusPhone = custBusPhone;
	}

	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustCountry() {
		return this.custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustFirstName() {
		return this.custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustHomePhone() {
		return this.custHomePhone;
	}

	public void setCustHomePhone(String custHomePhone) {
		this.custHomePhone = custHomePhone;
	}

	public String getCustLastName() {
		return this.custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCustPassword() {
		return this.custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public String getCustPostal() {
		return this.custPostal;
	}

	public void setCustPostal(String custPostal) {
		this.custPostal = custPostal;
	}

	public String getCustProv() {
		return this.custProv;
	}

	public void setCustProv(String custProv) {
		this.custProv = custProv;
	}

	public String getCustUserId() {
		return this.custUserId;
	}

	public void setCustUserId(String custUserId) {
		this.custUserId = custUserId;
	}

}