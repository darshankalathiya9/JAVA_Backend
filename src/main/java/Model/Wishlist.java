package Model;

public class Wishlist {
	private int WID, PID, CustomerID;

	public int getWID() {
		return WID;
	}

	public void setWID(int wID) {
		WID = wID;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	@Override
	public String toString() {
		return "Wishlist [WID=" + WID + ", PID=" + PID + ", CustomerID=" + CustomerID + "]";
	}

}
