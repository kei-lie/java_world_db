package operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOperation {
	static Scanner scan = new Scanner(System.in);
	public void insert (Connection con, String table) {
		try {
			switch(table) {
			case "city" -> insertCity(con);
			//case "country" -> insertCountry(con);
			//case "countryLanguage" -> insertCountryLanguage(con);
			//Pašiem jāpievieno country un countrylanguage
			}
		}catch(SQLException e){
			System.out.println("INSERT kļūda: " + e.getMessage());
		}
	}
	
	private void insertCity(Connection con) throws SQLException {
		//Trūkst ievades datu pārbaude
		System.out.println("Ievadi pilsētas nosaukumu:");
		String name = scan.nextLine();
		System.out.println("Ievadi valsts kodu (3 simboli):");
		String countrycode = scan.nextLine();
		System.out.println("Norādi apgabalu:");
		String district = scan.nextLine();
		System.out.println("Norādi iedzīvotāju skaitu:");
		int population = scan.nextInt();
		scan.nextLine();
		
		String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, countrycode);
			ps.setString(3, district);
			ps.setInt(4, population);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā ievietotas: "+rows+" rindas");
		}
	}
}
