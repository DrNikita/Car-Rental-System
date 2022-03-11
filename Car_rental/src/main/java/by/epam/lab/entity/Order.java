package by.epam.lab.entity;

import java.util.Date;
import java.util.Objects;

public class Order extends Entity {

	private static final long serialVersionUID = 1L;

	private User user;
	private Car car;
	private Date startDate;
	private Date endDate;
	private int price;
	private ConfirmationStatus confirmationStatus;
	private String rejectionReason;
	private boolean isPaid;

	public enum ConfirmationStatus {
		CONSIDERED, CONFIRMED, REJECTED;
	}

	public Order() {
		super();
	}

	public Order(int id, User user, Car car, Date startDate, Date endDate, int price,
			ConfirmationStatus confirmationStatus, String rejectionReason, boolean isPaid) {
		super(id);
		this.user = user;
		this.car = car;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.confirmationStatus = confirmationStatus;
		this.rejectionReason = rejectionReason;
		this.isPaid = isPaid;
	}

	public int getFullPrice() {

		long milliseconds = this.endDate.getTime() - this.startDate.getTime();
		int days = (int) (milliseconds / (24 * 60 * 60 * 1000));

		return days * this.car.getPrice();
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ConfirmationStatus getConfirmationStatus() {
		return confirmationStatus;
	}

	public void setConfirmationStatus(ConfirmationStatus confirmationStatus) {
		this.confirmationStatus = confirmationStatus;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public boolean getIsPaidStatus() {
		return isPaid;
	}

	public void setIsPaidStatus(boolean isPaid) {
		this.isPaid = isPaid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(car, confirmationStatus, endDate, isPaid, price, rejectionReason, startDate, user);
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
		Order other = (Order) obj;
		return Objects.equals(car, other.car) && confirmationStatus == other.confirmationStatus
				&& Objects.equals(endDate, other.endDate) && isPaid == other.isPaid && price == other.price
				&& Objects.equals(rejectionReason, other.rejectionReason) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Order [user=" + user + ", car=" + car + ", startDate=" + startDate + ", endDate=" + endDate + ", price="
				+ price + ", confirmationStatus=" + confirmationStatus + ", rejectionReason=" + rejectionReason
				+ ", isPaid=" + isPaid + "]";
	}

	public static class Builder {
		private Order order;

		public Builder() {
			order = new Order();
		}

		public Builder setOrderId(int id) {
			order.setId(id);
			return this;
		}

		public Builder setUser(User user) {
			order.user = user;
			return this;
		}

		public Builder setCar(Car car) {
			order.car = car;
			return this;
		}

		public Builder setStartDate(Date date) {
			order.startDate = date;
			return this;
		}

		public Builder setEndDate(Date date) {
			order.endDate = date;
			return this;
		}

		public Builder setPrice(int price) {
			order.price = price;
			return this;
		}

		public Builder setIsPaidStatus(boolean status) {
			order.isPaid = status;
			return this;
		}

		public Builder setConfirmationStatus(ConfirmationStatus status) {
			order.confirmationStatus = status;
			return this;
		}

		public Builder setRejectionReason(String rejectionReason) {
			order.rejectionReason = rejectionReason;
			return this;
		}

		public Order build() {
			return order;
		}
	}
}
