package HotelMang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DbOperations {
	private static String host="jdbc:mysql://localhost:3306/hotel";
	private static String uName="root";
	private static String uPass="Navu@06";
	
	private static Connection toCreateConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection(host, uName, uPass);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void toAddHotelData(HotelManagement obj) {
		try {
		Connection con=toCreateConnection();
		String query="Insert into hotelmanagement(hotelName,hotelLocation,noOfRooms,costPerRoom) values (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,obj.getHotelName());
			stmt.setString(2,obj.getHotelLocation());
			stmt.setInt(3, obj.getNoOfRooms());
			stmt.setFloat(4, obj.getCostPerRoom());
			stmt.executeUpdate();
			con.close();
			System.out.println("Thank you- Hotel Id created successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public static HashMap<Integer,HotelManagement> toDisplayHotelId(){
		try {
			Connection con=toCreateConnection();
			String query="Select * from hotelmanagement";
			PreparedStatement stmt = con.prepareStatement(query);
			
			ResultSet rowData=stmt.executeQuery();
			HashMap<Integer,HotelManagement> hotelData=new HashMap<>();
			
			while(rowData.next()) {
				HotelManagement obj=new HotelManagement();
				obj.setHotelId(rowData.getInt(1));
				obj.setHotelName(rowData.getString(2));
				obj.setHotelLocation(rowData.getString(3));
				obj.setNoOfRooms(rowData.getInt(4));
				obj.setCostPerRoom(rowData.getFloat(5));
				hotelData.put(obj.getHotelId(), obj);
			}
			con.close();
			return hotelData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void toRemoveAHoteldata(int id) {
		// TODO Auto-generated method stub
		try {
			Connection con = toCreateConnection();
//		String query = "Select * from students where ";
			PreparedStatement stmt;
			stmt = con.prepareStatement("Select * from hotelmanagement where hotelId=?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			if(rowData.next()){
	          stmt=con.prepareStatement("Delete from hotelmanagement where hotelId=?");
	          stmt.setInt(1, id);
	          stmt.executeUpdate();
	          System.out.println("Hotel id is removed successfully\n");
	        
	    }else{
	        System.err.println("Hotel id does not exists !!!");
	    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean hotelExits(int id) {
		try {
			Connection con = toCreateConnection();
//			String query = "Select * from students where ";
				PreparedStatement stmt=con.prepareStatement("Select * from hotelmanagement where hotelId=?");
				
				stmt.setInt(1, id);
				ResultSet rowData = stmt.executeQuery();
				return rowData.next();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void toUpdateHotelName(String name,int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt=con.prepareStatement("Update hotelmanagement set hotelName=? where hotelId=?");
			stmt.setString(1,name);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void toUpdateHotelLocation(String location,int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt=con.prepareStatement("Update hotelmanagement set hotelLocation=? where hotelId=?");
			stmt.setString(1,location);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void toUpdateHotelNoOfRooms(int noOfRooms,int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt=con.prepareStatement("Update hotelmanagement set noOfRooms=? where hotelId=?");
			stmt.setInt(1,noOfRooms);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void toUpdateHotelCostOfRooms(float costPerRoom,int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt=con.prepareStatement("Update hotelmanagement set costPerRoom=? where hotelId=?");
			stmt.setFloat(1,costPerRoom);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static HotelManagement toGetHotelData(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt;
			stmt = con.prepareStatement("Select * from hotelmanagement where hotelId=?");
			stmt.setInt(1,id);
			ResultSet rowData=stmt.executeQuery();
			rowData.next();
			HotelManagement obj=new HotelManagement();
			obj.setHotelId(rowData.getInt(1));
			obj.setHotelName(rowData.getString(2));
			obj.setHotelLocation(rowData.getString(3));
			obj.setNoOfRooms(rowData.getInt(4));
			obj.setCostPerRoom(rowData.getFloat(5));
			con.close();
			return obj;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
