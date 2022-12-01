package DatabaseTest;

import java.sql.*;
import java.util.Scanner;

public class termProject {
	
	public static void printMenu() {
		System.out.println("Example: ");
		System.out.println("------------------------------------------------------");
		System.out.println("1. connection                  2. find ****      ");
		System.out.println("1. connection                  2. find ****      ");
		System.out.println("1. connection                  2. find ****      ");
		System.out.println("------------------------------------------------------");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement stmt = null;
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			con=DriverManager.getConnection(
//						"jdbc:mysql://192.168.217.3:3308/madang",
//						"jjeong","jongjh@");
//			stmt=con.createStatement();
////			stmt.executeUpdate("DELETE FROM Book WHERE bookid ='11'");
//			
////			ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
////
////			while(rs.next())
////				System.out.println(rs.getInt(1)+" "+rs.getString(2)+
////							" "+rs.getString(3));
////			con.close();
//			}catch(Exception e){ System.out.println(e);}
//		
//		try {
//			stmt.executeUpdate("DELETE FROM Book WHERE bookid ='11'");
//			ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
//
//			while(rs.next())
//				System.out.println(rs.getInt(1)+" "+rs.getString(2)+
//							" "+rs.getString(3));
//			con.close();
//		} catch (Exception e) {
//			 System.out.println(e);
//		}
		
		int num = 0;
		boolean key = true;
		while (key) {
			printMenu();
			num = sc.nextInt();
			switch (num) {
			case 1: // connection
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://192.168.217.3:3308/madang", "jjeong", "jongjh@");
					stmt = con.createStatement();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;

			case 5:
				break;

			case 6:

				break;

			case 99:
				try {con.close();} 
				catch (Exception e) {System.out.println(e);}
				key = false;
				break;

			default:
				System.out.println("invalid value");
				break;

			}

		}
		
		System.out.println("나왔다");

	}
	
	

}
