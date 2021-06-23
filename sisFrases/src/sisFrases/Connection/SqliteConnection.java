package sisFrases.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class SqliteConnection {
	Connection conn = null;
	static String locationBd = "jdbc:sqlite:C:\\Workspaces\\bdFrases\\bd\\frases.db";
	public static Connection dbConnector() {
		try {
			Connection conn = DriverManager.getConnection(locationBd);
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "ERRO AO CONECTAR O BD");
			return null;
		}
	}

}

