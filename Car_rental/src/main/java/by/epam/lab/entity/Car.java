package by.epam.lab.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Car {

	private int id;
	private String brand;
	private String model;
	private int price;
	private String imageLink;
	private Map<Date, Date> rentalDates;
	private String description;

	public Car() {
		this.rentalDates = new HashMap<>();
	}

	public Car(int id, String brand, String model, int price, String imageLink, String description) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.imageLink = imageLink;
		this.rentalDates = new HashMap<>();
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public Map<Date, Date> getRentalDates() {
		return rentalDates;
	}

	public void setRentalDates(Map<Date, Date> rentalDates) {
		this.rentalDates = rentalDates;
	}

	public void addRentalDates(Date startDate, Date endDate) {
		this.rentalDates.put(startDate, endDate);
	}

	public void removeDates(Date startDate) {
		this.rentalDates.remove(startDate);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addDate(Date startDate, Date endDate) {
		this.rentalDates.put(startDate, endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, description, id, imageLink, model, price, rentalDates);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(imageLink, other.imageLink) && Objects.equals(model, other.model)
				&& price == other.price && Objects.equals(rentalDates, other.rentalDates);
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price + ", imageLink="
				+ imageLink + ", rentalDates=" + rentalDates + ", description=" + description + "]";
	}

	public static class Builder {
		private Car car;

		public Builder() {
			this.car = new Car();
		}

		public Builder setCarId(int id) {
			car.id = id;
			return this;
		}

		public Builder setBrand(String brand) {
			car.brand = brand;
			return this;
		}

		public Builder setModel(String model) {
			car.model = model;
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

		public Builder setDescription(String description) {
			car.description = description;
			return this;
		}

		public Car build() {
			return car;
		}
	}
}
