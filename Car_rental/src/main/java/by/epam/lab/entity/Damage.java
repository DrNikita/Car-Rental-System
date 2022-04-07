package by.epam.lab.entity;

import java.util.Objects;

public class Damage extends Entity {

	private static final long serialVersionUID = 1L;

	private User user;
	private Car car;
	private String description;
	private int price;
	private boolean isPaid;

	public Damage() {
		super();
	}

	public Damage(int id, User user, Car car, String description, int price, boolean isPaid) {
		super(id);
		this.user = user;
		this.car = car;
		this.description = description;
		this.price = price;
		this.isPaid = isPaid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(car, description, isPaid, price, user);
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
		Damage other = (Damage) obj;
		return Objects.equals(car, other.car) && Objects.equals(description, other.description)
				&& isPaid == other.isPaid && price == other.price && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Damage [user=" + user + ", car=" + car + ", description=" + description + ", price=" + price
				+ ", isPaid=" + isPaid + "]";
	}

	public static class Builder {
		private Damage damage;

		public Builder() {
			this.damage = new Damage();
		}

		public Builder setDamageId(int id) {
			damage.setId(id);
			return this;
		}

		public Builder setUser(User user) {
			damage.user = user;
			return this;
		}

		public Builder setCar(Car car) {
			damage.car = car;
			return this;
		}

		public Builder setDescriprion(String description) {
			damage.description = description;
			return this;
		}

		public Builder setPrice(int price) {
			damage.price = price;
			return this;
		}

		public Builder setIsPaid(boolean status) {
			damage.isPaid = status;
			return this;
		}

		public Damage build() {
			return damage;
		}
	}
}
