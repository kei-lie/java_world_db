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
			case "countryLanguage" -> insertCLanguage(con);
			//Pašiem jāpievieno country un countrylanguage
			default -> System.out.println("Neatbalstīta tabulla: "+table);
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
	
	private void insertCLanguage(Connection con) throws SQLException {
		//Trūkst ievades datu pārbaude
		System.out.println("Ievadi valsts kodu (3 simboli):");
		String countrycode = scan.nextLine();
		System.out.println("Ievadi valsts valodu:");
		String language = scan.nextLine();
		System.out.println("Vai ir oficiāla(T/F:");
		String isOfficial = scan.nextLine();
		System.out.println("Norādi procentus:");
		int percentage = scan.nextInt();
		scan.nextLine();
		
		String sql = "INSERT INTO countrylanguage (CountryCode, Language, isOfficial, percentage) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, countrycode);
			ps.setString(2, language);
			ps.setString(3, isOfficial);
			ps.setInt(4, percentage);
			int rows = ps.executeUpdate();
			System.out.println("CountryLanguage tabulā ievietotas: "+rows+" rindas");
		}
	}
}
