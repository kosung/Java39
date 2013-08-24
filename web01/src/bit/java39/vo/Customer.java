package bit.java39.vo;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String 	id;
	protected String 	password;
	protected String 	email;
	protected String 	name;
	protected String 	mobile;
	protected String 	detailAddress;
	protected int 		addressNo;
	
	public String getId() {
		return id;
	}
	public Customer setId(String id) {
		this.id = id;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Customer setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Customer setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getName() {
		return name;
	}
	public Customer setName(String name) {
		this.name = name;
		return this;
	}
	public String getMobile() {
		return mobile;
	}
	public Customer setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public Customer setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
		return this;
	}
	public int getAddressNo() {
		return addressNo;
	}
	public Customer setAddressNo(int addressNo) {
		this.addressNo = addressNo;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addressNo;
		result = prime * result
				+ ((detailAddress == null) ? 0 : detailAddress.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (addressNo != other.addressNo)
			return false;
		if (detailAddress == null) {
			if (other.detailAddress != null)
				return false;
		} else if (!detailAddress.equals(other.detailAddress))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
	
}
