package DatabaseTest;

import java.sql.*;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(
						"jdbc:mysql://192.168.217.3:3308/madang",
						"jjeong","jongjh@");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM Book WHERE bookid ='11'");
			
			ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
			
			
			
			while(rs.next())
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+
							" "+rs.getString(3));
			con.close();
			}catch(Exception e){ System.out.println(e);} 

	}

}
