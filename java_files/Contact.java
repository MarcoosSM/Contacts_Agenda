package entities;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Contact implements Serializable {

	// attributes
	private String name;
	private String surnames;
	private String alias;
	private ArrayList<String> email;
	private String address;
	private String phone;
	private ArrayList<String> mobilePhone;

	// builders
	public Contact() {
	}

	public Contact(String name, String surnames, String alias, ArrayList<String> email, String address, String phone,
			ArrayList<String> mobilePhone) {
		this.name = name;
		this.surnames = surnames;
		this.alias = alias;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<String> getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(ArrayList<String> mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Override
	public String toString() {
		return "Contact (Name=" + name + ", surnames=" + surnames + ", alias=" + alias + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", mobilePhone=" + mobilePhone + ")\n";
	}

}
