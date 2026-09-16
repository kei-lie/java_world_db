package operation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DatabaseConnection;

public class world_db {
	private static Connection con;
	private static final Scanner scan = new Scanner(System.in);
	
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	static final String GREEN = "\u001B[32m";
	static final String RED = "\u001B[31m";
	
	private static String chooseTable() {
		while(true) {
			System.out.println(CYAN+"\n--- TABULAS ---\n"+RESET
					+ "1. City\n"
					+ "2. Country\n"
					+ "3. CountryLanguage\n"
					+ "0. Atpakaļ");
			String c = scan.nextLine().trim();
			
			return switch(c) {
			case "1" -> "city";
			case "2" -> "country";
			case "3" -> "countrylanguage";
			case "0" -> "exit";
			default -> {
				System.out.println("Nepareiza izvēle.");
				yield "exit";
			}
			};
		}
	}
	
	//Vēlāk jāpievieno arī pārējie parametri InsertOperation, DeleteOperation, utt.
	private static void tableMenu(String table, SelectOperation selectOp) {
		boolean back = false;
		while(!back) {
			System.out.println("\n--- " + table.toUpperCase() + " ---\n"
					+ "1. Atlasīt (SELECT)\n"
					+ "2. Pievienot (INSERT)"
					+ "3. Atjaunināt (UPDATE)"
					+ "4. Dzēst (DELETE)\n"
					+ "0. Atpakaļ\n"
					+ "Izvēle: ");
				String c = scan.nextLine().trim();
			
			return switch(c) {
			case "1" -> System.out.println("Jātaisa select metode");
			//selectOp.select(con, table);
			//turpinājums būs pārējie case
			case "0" -> back = true;
			default -> System.out.println("Nepareiza izvēle.");
			};
		}
	}

	public static void main(String[] args) {
		try {
		con = DatabaseConnection.getConnection();
		System.out.println("Izveidots savienojums ar datu bāzi World!");
		
		SelectOperation selectOp = new SelectOperation();
		
		
		boolean running = true;
		while(running) {
			System.out.println(CYAN+"\n-----WORLD-DB-----\n"+RESET
					+ GREEN+"1. Tabulas\n"+RESET
					+ "2. Skati\n"
					+ RED+"0. Apturēt\n"+RESET
					+ CYAN+"Izvēle: "+RESET);
			String mainChoice = scan.nextLine().trim();
			
			switch(mainChoice) {
			case "1" -> {
				String table = chooseTable();
				if(!table.equals("exit")) {
					tableMenu(table, selectOp);
				}
			}
			case "2" -> 
				System.out.println("Būs skati...");
			case "0" -> running = false;
			default -> 	System.out.println(RED+"Nepareiza izvēle!"+RESET);
		}
			
	}
		con.close();
		System.out.println(GREEN+"Savienojums ar datu bāzi slēgts"+RESET);
		} catch(SQLException e) {
			System.out.println("DB kļūda: "+e.getMessage());
		}
	}

}
