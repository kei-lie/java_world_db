package operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateOperation {
	static Scanner scan = new Scanner(System.in);
	
	public void update(Connection con, String table) {
		try {
			switch(table){
			case "city" -> updateCity(con);
			//Pašiem jāuztaisa country and countrylanguage
			
			default -> System.out.println("Neatbalstīta tabulla: "+table);
			}
			
		}catch(SQLException e) {
			System.out.println("UPDATE kļūda: " + e.getMessage());
		}
	}
	
	private void updateCity(Connection con) throws SQLException{
		System.out.println("Kuru pilsētu labot? Norādi ID:");
		int ID = scan.nextInt();
		scan.nextLine();
		System.out.println("Norādi pilsētas nosaukumu:");
		String name = scan.nextLine();
		System.out.println("Ievadi valsts kodu (3 simboli):");
		String countrycode = scan.nextLine();
		System.out.println("Norādi apgabalu:");
		String district = scan.nextLine();
		System.out.println("Norādi iedzīvotāju skaitu:");
		int population = scan.nextInt();
		scan.nextLine();
		
		String sql = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, countrycode);
			ps.setString(3, district);
			ps.setInt(4, population);
			ps.setInt(5, ID);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā atjaunots: "+rows+" rindas");
		}
	}
}
