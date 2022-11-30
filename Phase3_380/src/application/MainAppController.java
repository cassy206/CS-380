package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MainAppController implements Initializable {
	
	@FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private AnchorPane pane_login;

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_email_up;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_password_up;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void LoginpaneShow() {
    	pane_login.setVisible(true);
    	pane_signup.setVisible(false);
    }
    
    public void SignuppaneShow() {
    	pane_signup.setVisible(true);
    	pane_login.setVisible(false);
    }
	
    @FXML
    private void Login (ActionEvent event) throws Exception{
    	
    	if (txt_email.getText().equals("1admin@gmail.com") && txt_password.getText().equals("cs380")) {
    		
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
    		Scene scene = new Scene(root, 700, 500);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		} else {
    	conn = mysqlconnect.ConnectDb();
    	String sql = "Select * from registration where email = ? and password = ? ";
    	try {
    		pst = conn.prepareStatement(sql);
    		pst.setString(1,txt_email.getText());
    		pst.setString(2, txt_password.getText());
    		rs = pst.executeQuery();
    		if(rs.next ()) {
    			
    			JOptionPane.showMessageDialog(null, "Username and Password is Correct");
    			
    			btn_login.getScene().getWindow().hide();
    			Parent root = FXMLLoader.load(getClass().getResource("ClientMain.fxml"));
    			Stage mainStage = new Stage();
    			Scene scene = new Scene(root);
    			mainStage.setScene(scene);
    			mainStage.show();
    			
    			
    		}else
    			JOptionPane.showMessageDialog(null, "Invalid Username or Password");
    	}catch (Exception e) {
    			JOptionPane.showMessageDialog(null, e);
    		}
    	}
    	
}
    
    public void add_usrs(ActionEvent event) {
    	
    	conn = mysqlconnect.ConnectDb();
    	String sql = "insert into registration (email,password) values (?,?)";
    	try {
    		pst = conn.prepareStatement(sql);
    		pst.setString(1,txt_email_up.getText());
    		pst.setString(2,txt_password_up.getText());
    		pst.execute();
    		
    		JOptionPane.showMessageDialog(null, "Saved");
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    	
    	
    }
    	
    
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
}
}
