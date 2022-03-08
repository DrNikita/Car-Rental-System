package by.epam.lab.entity;

import java.util.Objects;

public class Car extends Entity {

	private static final long serialVersionUID = 1L;

	private Brand brand;
	private int yearOfIssue;
	private int price;
	private String imageLink;

	public Car() {
		super();
	}

	public Car(int id, Brand brand, int yearOfIssue, int price, String imageLink) {
		super(id);
		this.brand = brand;
		this.yearOfIssue = yearOfIssue;
		this.price = price;
		this.imageLink = imageLink;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public int getYearOfIssue() {
		return yearOfIssue;
	}

	public void setYearOfIssue(int yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(brand, imageLink, price, yearOfIssue);
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
		Car other = (Car) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(imageLink, other.imageLink) && price == other.price
				&& yearOfIssue == other.yearOfIssue;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", yearOfIssue=" + yearOfIssue + ", price=" + price + ", imageLink=" + imageLink
				+ "]";
	}

	public static class Builder {
		private Car car;

		public Builder() {
			this.car = new Car();
		}

		public Builder setCarId(int id) {
			this.car.setId(id);
			return this;
		}

		public Builder setBrand(Brand brand) {
			car.brand = brand;
			return this;
		}

		public Builder setYearOfIssue(int year) {
			car.yearOfIssue = year;
			return this;
		}

		public Builder setPrice(int price) {
			car.price = price;
			return this;
		}

		public Builder setImageLink(String link) {
			car.imageLink = link;
			return this;
		}

		public Car build() {
			return car;
		}
	}
}
