package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Entities.Emoticons;
import db.DB;

public class Program {

	public static void main(String[] args) {
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
		d = cal.getTime();
		System.out.println("|------------------------------|");
		System.out.println("|                              |");
		System.out.println("|          NordesTeam          |");
		System.out.println("|                              |");
		System.out.println("|------------------------------|");
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement st = null;
		System.out.print("Enter your name: ");
		String empName = sc.nextLine();
		System.out.print("Enter your message: ");
		String ans = sc.nextLine();
		Emoticons emoticons = new Emoticons(ans, empName);
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
						"INSERT INTO EMPLOYEES "
					+ 	"(NAME_EMP, MESSAGE,STATUS_MSG, DATE_MSG) VALUES "
					+ "(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, empName);
			st.setString(2, ans);
			st.setString(3, emoticons.checkEmoticons(ans));
			st.setDate(4, new java.sql.Date(d.getTime()));
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0 && emoticons.therIsNoEmote(ans) == false) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
					System.out.println(emoticons.checkEmoticons(ans));
				} 
			} else {
				System.out.println("No row affected");
			}
		} catch (SQLException  e) {
			e.printStackTrace();
			System.out.println("Roolback executed");
			DB.executedRollback();
			
		} finally {
			sc.close();
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
