package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import Model.Wishlist;

public class WishlistDao {
	public static void InsertWishList(Wishlist w) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "insert into wishlist (PID,CustomerID) values (?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, w.getPID());
			pst.setInt(2, w.getCustomerID());

			pst.executeUpdate();
			System.out.println("Product Add into Wishlist Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Wishlist> getWishlistByCustomerID(int ID) {
		List<Wishlist> list = new ArrayList<Wishlist>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from wishlist where CustomerID=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, ID);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Wishlist w = new Wishlist();
				w.setWID(rs.getInt("WID"));
				w.setPID(rs.getInt("PID"));
				w.setCustomerID(rs.getInt("CustomerID"));
				list.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void removeFromWishlist(int WID) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "delete from wishlist where WID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, WID);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkProductExist(int CustomerID, int PID) {
		boolean flag = false;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from wishlist where CustomerID = ? and PID=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, CustomerID);
			pst.setInt(2, PID);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
