package DatabaseTest;

import java.sql.*;
import java.util.Scanner;

public class termProject {
	
	public static void printMenu() {
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("1. connection                  2. show all members' Info");
		System.out.println("3. insert Member               4. delete Member   ");
		System.out.println("5. connection                  6. find ****      ");
		System.out.println("                              99. quit");
		System.out.println("------------------------------------------------------");
		System.out.printf("번호를 입력하세요 : ");
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
		
		int num = 0;
		boolean key = true;
		while (key) {
			printMenu();
			num = sc.nextInt();
			switch (num) {
			case 1: // connection
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://192.168.217.3:3308/banddb", "jjeong", "jongjh@");
					stmt = con.createStatement();
					
					System.out.println("연결 성공!!");
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 2:
				try {
				System.out.println();
				ResultSet rs=stmt.executeQuery("SELECT * FROM Member");
					
								while(rs.next())
									System.out.println(rs.getInt(1)+" "+rs.getString(2)+
												" "+rs.getString(3)+" "+rs.getString(4)+
												" "+rs.getString(5));
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
			case 3:
				int id;
				String name, sex, date, address;
				
				System.out.printf("id를 입력하세요: ");
				id = sc.nextInt();
				sc.nextLine();
				System.out.printf("이름을 입력하세요: ");
				name = sc.nextLine();
				System.out.printf("성별을 입력하세요: ");
				sex = sc.nextLine();
				System.out.printf("생년월일을 입력하세요: ");
				date = sc.nextLine();
				System.out.printf("주소를 입력하세요: ");
				address = sc.nextLine();
				
				
				try {
					stmt.executeUpdate("INSERT INTO Member VALUES("+id+",\'"+name+"\',\'"+sex+"\',\'"+date+"\',\'"+address+"\')");
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case 4:
				System.out.printf("삭제할 id를 입력하세요: ");
				id = sc.nextInt(); sc.nextLine();
				
				try {
					stmt.executeUpdate("DELETE FROM Member WHERE id ="+id);
				
				} catch (Exception e) {
					System.out.println(e);
				}
				
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
