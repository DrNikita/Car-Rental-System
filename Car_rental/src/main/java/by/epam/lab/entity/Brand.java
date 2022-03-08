package by.epam.lab.entity;

import java.util.Objects;

public class Brand extends Entity {
	private String brand;
	private String model;

	public Brand() {
	}

	public Brand(int id, String brand, String model) {
		super(id);
		this.brand = brand;
		this.model = model;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(brand, model);
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
		Brand other = (Brand) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(model, other.model);
	}

	@Override
	public String toString() {
		return "Brand [brand=" + brand + ", model=" + model + "]";
	}

	public static class Builder {
		private Brand brand;

		public Builder() {
			brand = new Brand();
		}

		public Builder setBrandId(int id) {
			brand.setId(id);
			return this;
		}

		public Builder setBrand(String brandName) {
			brand.setBrand(brandName);
			return this;
		}

		public Builder setModel(String model) {
			brand.setModel(model);
			return this;
		}

		public Brand build() {
			return brand;
		}
	}
}
