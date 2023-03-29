package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import Model.Cart;

public class CartDao {
	public static void InsertCart(Cart c) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "insert into cart (CustomerID, PID, PName, PPrice, PQuantity, TotalPrice, Payment_Status) values (?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, c.getCustomerID());
			pst.setInt(2, c.getPID());
			pst.setString(3, c.getPName());
			pst.setInt(4, c.getPPrice());
			pst.setInt(5, c.getPQuantity());
			pst.setInt(6, c.getTotalPrice());
			pst.setString(7, c.getPayment_Status());

			pst.executeUpdate();
			System.out.println("Product Add into Cart Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Cart> getCartByCustomerID(int ID) {
		List<Cart> list = new ArrayList<Cart>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from cart where CustomerID=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, ID);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Cart c = new Cart();
				c.setCID(rs.getInt("CID"));
				c.setCustomerID(rs.getInt("CustomerID"));
				c.setPID(rs.getInt("PID"));
				c.setPName(rs.getString("PName"));
				c.setPPrice(rs.getInt("PPrice"));
				c.setPQuantity(rs.getInt("PQuantity"));
				c.setTotalPrice(rs.getInt("TotalPrice"));
				c.setPayment_Status(rs.getString("Payment_Status"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Cart getCartByCID(int CID) {
		Cart c = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from cart where CID=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, CID);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				c = new Cart();
				c.setCID(rs.getInt("CID"));
				c.setCustomerID(rs.getInt("CustomerID"));
				c.setPID(rs.getInt("PID"));
				c.setPName(rs.getString("PName"));
				c.setPPrice(rs.getInt("PPrice"));
				c.setPQuantity(rs.getInt("PQuantity"));
				c.setTotalPrice(rs.getInt("TotalPrice"));
				c.setPayment_Status(rs.getString("Payment_Status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void updateCart(Cart c) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "update cart set PQuantity = ?, TotalPrice=? where CID=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, c.getPQuantity());
			pst.setInt(2, c.getTotalPrice());
			pst.setInt(3, c.getCID());
			
			pst.executeUpdate();
			System.out.println("Cart Updated");
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}