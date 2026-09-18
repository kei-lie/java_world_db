package operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectOperation {
	public void select(Connection con, String tableOrView) {
		String sql = "SELECT * FROM " + tableOrView;
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)){
			
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();
			int colWidth = 30;
			
			for(int i = 1; i <= colCount; i++) {
				System.out.printf("%-" + colWidth +"s", meta.getColumnName(i));
			}
			
			System.out.println();
			System.out.println("_".repeat(colCount * colWidth));
			
			//int rowIndex = 0;
			while(rs.next()) {
				//rowIndex++;
				
				for(int i=1; i<=colCount; i++) {
					String value = rs.getString(i);
					
					if(value == null) value = "NULL";
					
					else if(value.length() > colWidth - 5)
						value = value.substring(0, colWidth - 5) + "...";
					
					String formattedValue = String.format("%-" + colWidth + "s", value);
					System.out.print(formattedValue);
					
				}
				System.out.println();
			}
			
		}catch(SQLException e) {
			System.out.println("SELECT kļūda: " + e.getMessage());
		}
	}
}
