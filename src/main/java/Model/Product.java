package Model;

public class Product {
	private int PID, SID, PPrice;
	private String Image, PName, PCategory, PDesc;

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public int getPPrice() {
		return PPrice;
	}

	public void setPPrice(int pPrice) {
		PPrice = pPrice;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}

	public String getPCategory() {
		return PCategory;
	}

	public void setPCategory(String pCategory) {
		PCategory = pCategory;
	}

	public String getPDesc() {
		return PDesc;
	}

	public void setPDesc(String pDesc) {
		PDesc = pDesc;
	}

	@Override
	public String toString() {
		return "Product [PID=" + PID + ", SID=" + SID + ", PPrice=" + PPrice + ", Image=" + Image + ", PName=" + PName
				+ ", PCategory=" + PCategory + ", PDesc=" + PDesc + "]";
	}

}