package by.epam.lab.entity;

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
		USER, MANAGER;
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
