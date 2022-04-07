package by.epam.lab.entity;

import java.util.Objects;

public class User extends Entity {

	private static final long serialVersionUID = 1L;

	private Role role;
	private String name;
	private String secondName;
	private String email;
	private String phone;
	private String passportNumber;
	private String passportIdentificationNumber;

	public enum Role {
		USER, MANAGER, GUEST;
	}

	public User() {
		super();
	}

	public User(int id, Role role, String name, String secondName, String email, String phone, String passportNumber,
			String passportIdentificationNumber) {
		super(id);
		this.role = role;
		this.name = name;
		this.secondName = secondName;
		this.email = email;
		this.phone = phone;
		this.passportNumber = passportNumber;
		this.passportIdentificationNumber = passportIdentificationNumber;
	}

	public boolean isPassportDataExists() {
		return passportNumber != null && passportNumber != "" && passportIdentificationNumber != null
				&& passportIdentificationNumber != "";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportIdentificationNumber() {
		return passportIdentificationNumber;
	}

	public void setPassportIdentificationNumber(String passportIdentificationNumber) {
		this.passportIdentificationNumber = passportIdentificationNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(email, name, passportIdentificationNumber, passportNumber, phone, role, secondName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(passportIdentificationNumber, other.passportIdentificationNumber)
				&& Objects.equals(passportNumber, other.passportNumber) && Objects.equals(phone, other.phone)
				&& role == other.role && Objects.equals(secondName, other.secondName);
	}

	@Override
	public String toString() {
		return "User [role=" + role + ", name=" + name + ", secondName=" + secondName + ", email=" + email + ", phone="
				+ phone + ", passportNumber=" + passportNumber + ", passportIdentificationNumber="
				+ passportIdentificationNumber + "]";
	}

	public static class Builder {
		private User user;

		public Builder() {
			user = new User();
		}

		public Builder setUserID(int userId) {
			user.setId(userId);
			return this;
		}

		public Builder setName(String name) {
			user.name = name;
			return this;
		}

		public Builder setSecondName(String secondName) {
			user.secondName = secondName;
			return this;
		}

		public Builder setEmail(String email) {
			user.email = email;
			return this;
		}

		public Builder setPhone(String phone) {
			user.phone = phone;
			return this;
		}

		public Builder setRole(Role role) {
			user.role = role;
			return this;
		}

		public Builder setPassportNumber(String passportNumber) {
			user.passportNumber = passportNumber;
			return this;
		}

		public Builder setPassportIdentificationNumber(String passportIdentificationNumber) {
			user.passportIdentificationNumber = passportIdentificationNumber;
			return this;
		}

		public User build() {
			return user;
		}
	}
}
