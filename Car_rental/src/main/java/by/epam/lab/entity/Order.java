package by.epam.lab.entity;

import java.util.Date;
import java.util.Objects;

public class Order {
	private int userId;
	private int carId;
	private Date rentalStartDate;
	private Date rentalEndDate;
	private int price;
	private boolean isAccepted;
	private String refusalReason;
	private boolean isPaid;
	private boolean isReturned;
	private boolean isDamaged;
	private int repairAmount;

	public Order() {
	}

	public Order(int userId, int carId, Date rentalStartDate, Date rentalEndDate, int price, boolean isAccepted,
			String refusalReason, boolean isPaid, boolean isReturned, boolean isDamaged, int repairAmount) {
		super();
		this.userId = userId;
		this.carId = carId;
		this.rentalStartDate = rentalStartDate;
		this.rentalEndDate = rentalEndDate;
		this.price = price;
		this.isAccepted = isAccepted;
		this.refusalReason = refusalReason;
		this.isPaid = isPaid;
		this.isReturned = isReturned;
		this.isDamaged = isDamaged;
		this.repairAmount = repairAmount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public Date getRentalStartDate() {
		return rentalStartDate;
	}

	public void setRentalStartDate(Date rentalStartDate) {
		this.rentalStartDate = rentalStartDate;
	}

	public Date getRentalEndDate() {
		return rentalEndDate;
	}

	public void setRentalEndDate(Date rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getRefusalReason() {
		return refusalReason;
	}

	public void setRefusalReason(String refusalReason) {
		this.refusalReason = refusalReason;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public boolean isDamaged() {
		return isDamaged;
	}

	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}

	public int getRepairAmount() {
		return repairAmount;
	}

	public void setRepairAmount(int repairAmount) {
		this.repairAmount = repairAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carId, isAccepted, isDamaged, isPaid, isReturned, price, refusalReason, rentalEndDate,
				rentalStartDate, repairAmount, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return carId == other.carId && isAccepted == other.isAccepted && isDamaged == other.isDamaged
				&& isPaid == other.isPaid && isReturned == other.isReturned && price == other.price
				&& Objects.equals(refusalReason, other.refusalReason)
				&& Objects.equals(rentalEndDate, other.rentalEndDate)
				&& Objects.equals(rentalStartDate, other.rentalStartDate) && repairAmount == other.repairAmount
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", carId=" + carId + ", rentalStartDate=" + rentalStartDate
				+ ", rentalEndDate=" + rentalEndDate + ", price=" + price + ", isAccepted=" + isAccepted
				+ ", refusalReason=" + refusalReason + ", isPaid=" + isPaid + ", isReturned=" + isReturned
				+ ", isDamaged=" + isDamaged + ", repairAmount=" + repairAmount + "]";
	}
}
