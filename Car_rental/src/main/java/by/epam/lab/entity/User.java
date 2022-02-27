package by.epam.lab.entity;

import java.util.Objects;

public class User {

	private int id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private Role role;
	private String passportNumber;
	private String passportIdentificationNumber;

	private User() {
	}

	public enum Role {
		GUEST, USER, ADMIN;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPassportDataExists() {
		return passportNumber != null && this.passportIdentificationNumber != null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, passportIdentificationNumber, passportNumber, phone, role, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(passportIdentificationNumber, other.passportIdentificationNumber)
				&& Objects.equals(passportNumber, other.passportNumber) && Objects.equals(phone, other.phone)
				&& role == other.role && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", passportNumber=" + passportNumber + ", passportIdentificationNumber="
				+ passportIdentificationNumber + "]";
	}

	public static class Builder {
		private User user;

		public Builder() {
			user = new User();
		}

		public Builder setUserID(int userId) {
			user.id = userId;
			return this;
		}

		public Builder setName(String name) {
			user.name = name;
			return this;
		}

		public Builder setSurname(String surname) {
			user.surname = surname;
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
