package model;

import java.io.Serializable;
import javax.persistence.*;


/** Ethan Shipley
 * The persistent class for the provstates database table.
 * 
 */
@Entity
@Table(name="provstates")
@NamedQuery(name="Provstate.findAll", query="SELECT p FROM Provstate p")
public class Provstate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String provStateId;

	private String countryId;

	private String provStateName;

	public Provstate() {
	}

	public String getProvStateId() {
		return this.provStateId;
	}

	public void setProvStateId(String provStateId) {
		this.provStateId = provStateId;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getProvStateName() {
		return this.provStateName;
	}

	public void setProvStateName(String provStateName) {
		this.provStateName = provStateName;
	}

}