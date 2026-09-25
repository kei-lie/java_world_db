package operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteOperation {
	static Scanner scan = new Scanner(System.in);
	
	public void delete (Connection con, String table) {
		try {
			switch(table) {
			case "city" -> deleteCity(con);
			case "country" -> deleteCountry(con);
		//	case "countryLanguage" -> deleteCLanguage(con);
			
			default -> System.out.println("Neatbalstīta tabulla: "+table);
			}
		}catch(SQLException e) {
			System.out.println("DELETE kļūda: "+e.getMessage());
		}
	}
	
	private void deleteCity(Connection con) throws SQLException{
		System.out.println("Norādi pilsētas ID, kuru vēlies dzēst");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM city WHERE ID = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā dzēstas "+ rows + " ieraksti");
		}
	}
	
	private void deleteCountry(Connection con) throws SQLException{
		System.out.println("Norādi valsts kodu, kuru vēlies dzēst");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM country WHERE Code = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā dzēstas "+ rows + " ieraksti");
		}
	}
}
