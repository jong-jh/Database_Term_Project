/********************************************************************************************************/
/* 학번: 2018038051   이름: 정종현  학과: 소프트웨어학과               											*/
/* 내용: Menu 기반 밴드부 데이터베이스 접속 및 요청 수행 프로그램           											*/
/*  메뉴 내용 																								*/
/*	1. jdbc를 통한 데이터베이스 연결																			*/
/*	2. 모든 멤버의 정보를 확인할 수 있다.																			*/
/*	3. 멤버 정보를 입력할 수 있으며	팀 정보도 입력을 한다																*/
/*	4. 입력받은 멤버의 정보를 삭제할 수 있다.																		*/
/*	5. 계좌의 남은 잔액을 확인할 수 있다. 입금내역에서 출금내역을 뺀 결과값으로 확인한다										*/
/*	6. 각 멤버가 담당하고 있는 곡을 확인한다. 학번, 팀이름, 곡제목이 출력된다											  	*/
/*	7. 노래를 입력받는다. Song entity에 값을 입력받으며, 각 팀의 부원들도 그 곡을 연주하기 때문에 Play entity 에 정볼르 입력해준다	*/
/********************************************************************************************************/

package DatabaseTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class termProject {
	
	public static void printMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("1. connection                  2. show all members' Info");
		System.out.println("3. insert Member               4. delete Member   ");
		System.out.println("5. account balance check       6. show each member's song");
		System.out.println("7. insert Song                    ");
		System.out.println("                              99. quit");
		System.out.println("-----------------------------------------------------------");
		System.out.printf("번호를 입력하세요 : ");
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement stmt = null;
		
		int num = 0;
		boolean key = true;
		while (key) {
			printMenu();
			num = sc.nextInt();
			sc.nextLine();
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
				
				System.out.println("팀을 지정해주세요");
				String tName, position;
				System.out.printf("팀이름을 입력하세요: ");
				tName = sc.nextLine();
				System.out.printf("맡은 포지션을 입력하세요: ");
				position = sc.nextLine();
				try {
					stmt.executeUpdate("INSERT INTO BelongTo VALUES("+id+",\'"+tName+"\',\'"+position+"\')");
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
				try {
					ResultSet rs=stmt.executeQuery("select ( select sum(amount) from Account where type = \'deposit\' ) - (select sum(amount) from Account where type = \'withdraw\')");
					System.out.printf("잔액 : ");
					while(rs.next())
						System.out.println(rs.getInt(1));
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;

			case 6:
				
				System.out.printf("id를 입력하세요: ");
				id = sc.nextInt();
				sc.nextLine();
				
				try {
					ResultSet rs=stmt.executeQuery("select Play.mid, Song.tname, Song.title from Play, Song where Play.title = Song.title AND Play.mid ="+id);
					while(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
				
			case 7:
				System.out.println("노래 추가하기");
				String singer, title, genre;
				System.out.printf("가수를 입력하세요: ");
				singer = sc.nextLine();
				System.out.printf("곡 제목을 입력하세요: ");
				title = sc.nextLine();
				System.out.printf("장르를 입력하세요: ");
				genre = sc.nextLine();
				System.out.printf("담당 팀을 입력하세요: ");
				tName = sc.nextLine();
				try {
					stmt.executeUpdate("INSERT INTO Song VALUES(\'"+singer+"\',\'"+title+"\',\'"+genre+"\',\'"+tName+"\')");
					
					ResultSet rs=stmt.executeQuery("SELECT mid FROM BelongTo where tname = "+ "\'"+tName+"\'");
					
					ArrayList<Integer> ids = new ArrayList();
					while(rs.next()) {
//						System.out.println(rs.getInt(1));
//						stmt.executeUpdate("INSERT INTO Play VALUES("+rs.getInt(1)+",\'"+singer+"\',\'"+title+"\')");
						ids.add(rs.getInt(1));
						}
					for(int i=0;i<ids.size();i++) {
						stmt.executeUpdate("INSERT INTO Play VALUES("+ids.get(i)+",\'"+singer+"\',\'"+title+"\')");
					}
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
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
