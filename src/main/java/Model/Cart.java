package Model;

public class Cart {
	private int CustomerID, PID, CID, PQuantity, PPrice, TotalPrice;
	private String PName, Payment_Status;

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public int getPQuantity() {
		return PQuantity;
	}

	public void setPQuantity(int pQuantity) {
		PQuantity = pQuantity;
	}

	public int getPPrice() {
		return PPrice;
	}

	public void setPPrice(int pPrice) {
		PPrice = pPrice;
	}

	public int getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}

	public String getPayment_Status() {
		return Payment_Status;
	}

	public void setPayment_Status(String payment_Status) {
		Payment_Status = payment_Status;
	}

	@Override
	public String toString() {
		return "Cart [CustomerID=" + CustomerID + ", PID=" + PID + ", CID=" + CID + ", PQuantity=" + PQuantity
				+ ", PPrice=" + PPrice + ", TotalPrice=" + TotalPrice + ", PName=" + PName + ", Payment_Status="
				+ Payment_Status + "]";
	}

}
