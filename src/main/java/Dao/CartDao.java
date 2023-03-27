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
			String sql = "insert into cart (CustomerID, PID, PName, PPrice, PQuantity, Payment_Status) values (?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, c.getCustomerID());
			pst.setInt(2, c.getPID());
			pst.setString(3, c.getPName());
			pst.setInt(4, c.getPPrice());
			pst.setInt(5, c.getPQuantity());
			pst.setString(6, c.getPayment_Status());
			
			pst.executeUpdate();
			System.out.println("Product Add into Cart Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Cart> getCartByCustomerID(int ID){
		List<Cart> list = new ArrayList<Cart>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from cart where CustomerID=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, ID);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Cart c = new Cart();
				
				
				
				
				
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}