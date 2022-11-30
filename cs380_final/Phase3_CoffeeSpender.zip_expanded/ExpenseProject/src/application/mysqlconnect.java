package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mysqlconnect {
	Connection connection = null;
	public static Connection ConnectDb() {
		
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registeredusers", "root","cs380");
			JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	
		
	}
	public static ObservableList<users> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from registration");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new users(Integer.parseInt(rs.getString("id")),rs.getString("email"), rs.getString("password") ));               
            }
        } catch (Exception e) {
        }
        return list;
    }
	
	

}
