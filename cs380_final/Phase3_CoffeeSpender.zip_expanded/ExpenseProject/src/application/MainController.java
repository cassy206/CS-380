package application;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
//import javafx.scene.control.Button;

public class MainController implements Initializable{
	

	
	@FXML
	private Label sID;
	@FXML
	private TextField unID;
	@FXML
	private TextField passID;
	@FXML 
	private Label mainBalance;
	@FXML 
	private Label mainAccountName;
	@FXML
	private TextField amountToAdd;
	@FXML
	private TextField amountToSubtract;
	@FXML
	private Label depositLabel;
	@FXML
	private Label withdrawLabel;
	@FXML
	private ComboBox<String> accountType;
	@FXML 
	private Label accountCreation;
	@FXML
	private TextField newAccountName;
	@FXML
	private TextField newAccountAmount;
	@FXML
	private TableView<Fund> tableView;
	@FXML
	private TableColumn<Fund, String> fundNameColumn;
	@FXML
	private TableColumn<Fund, Double> balanceColumn; 
	
	
	mainAccount myAccount = new mainAccount();
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		if (mainAccountName != null) {
			mainAccountName.setText("Main Account");
		}
		if (mainBalance != null) {
			//mainBalance.textProperty().bind(myAccount.displayBalance);
			mainBalance.setText("$"+String.format("%.2f", myAccount.balance));
			//mainBalance.textProperty().bind(Bindings.createStringBinding(() -> myAccount.balanceDisplay1));
		}
		if (accountType != null) {
			accountType.getItems().addAll("Expense Fund", "Savings");
		}
		//Initialize columns in table
		if (fundNameColumn != null) {
			fundNameColumn.setCellValueFactory(new PropertyValueFactory<Fund, String>("fundName")); 
			balanceColumn.setCellValueFactory(new PropertyValueFactory<Fund, Double>("balance")); 
			//Dummy data to make sure table works
			tableView.setItems(getFunds());
			tableView.refresh();
		}
	}
	
	public ObservableList<Fund> getFunds() {
		ObservableList<Fund> funds = FXCollections.observableArrayList();
		funds.add(new Fund("Coffee", 21.50));
		funds.add(new Fund("Electricity", 100.10));
		funds.add(new Fund("Rent", 550));
		funds.add(new Fund("Fun", 386.77));
		
		return funds;
	}
	
	//@Override
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
    			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
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
	
	
	public void addToMain(ActionEvent event) throws Exception {
		Stage depositMainStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/deposit.fxml"));
		Scene scene = new Scene(root, 300, 150);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		depositMainStage.setScene(scene);
		depositMainStage.show();
		
	}
	
	
	public void addMain(ActionEvent event) throws Exception {
		try {
			double amount = Double.parseDouble(amountToAdd.getText());
			if(amount > 0) {
				myAccount.deposit(amount);
				//mainBalance.setText("$"+String.format("%.2f", myAccount.balance));
				//System.out.println(myAccount.balance);
			}
			if(amount <= 0) {
				if (depositLabel != null) {
					depositLabel.setText("Can only add positive numbers");
				}
			}
		}
		catch (Exception e) {
			if (depositLabel != null) {
				depositLabel.setText("Enter only numbers");
			}
		}
	}
	
	
	public void withdrawMain(ActionEvent event) throws Exception {
		Stage withdrawMainStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/withdraw.fxml"));
		Scene scene = new Scene(root, 300, 150);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		withdrawMainStage.setScene(scene);
		withdrawMainStage.show();
	}
	
	
	public void newAccount(ActionEvent event) throws Exception {
		Stage newAccountStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/addAccount.fxml"));
		Scene scene = new Scene(root, 300, 300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		newAccountStage.setScene(scene);
		newAccountStage.show();
	}
	
	
	public void createUser(ActionEvent event) throws Exception {
		Stage createUserStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/createUser.fxml"));
		Scene scene = new Scene(root, 300, 300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		createUserStage.setScene(scene);
		createUserStage.show();
	}
	
	
	public void subtractMain(ActionEvent event) throws Exception {
		try {
			double amount = Double.parseDouble(amountToSubtract.getText());
			if(amount > 0) {
				myAccount.withdraw(amount);
				//mainBalance.setText("$"+String.format("%.2f", myAccount.balance));
			}
			if(amount <= 0) {
				if (withdrawLabel != null) {
					withdrawLabel.setText("Can only subtract positive numbers");
				}
			}
		}
		catch (Exception e) {
			if (withdrawLabel != null) {
				withdrawLabel.setText("Enter only numbers");
			}
		}
		
	}
	
	
	public void createAccount(ActionEvent event) throws Exception {
		try {
			double amount = Double.parseDouble(newAccountAmount.getText());
			if(accountType.getValue() == "Expense Fund") {
				expenseFund myFund = new expenseFund(newAccountName.getText(), amount);
				//System.out.println(myFund.accountName);
			}
			else if(accountType.getValue() == "Savings") {
				savingsAccount myFund = new savingsAccount(newAccountName.getText(), amount);
				//System.out.println(myFund.accountName);
			}
		}
		catch (Exception e) {
			if (accountCreation != null) {
				accountCreation.setText("Error");
			}
		}
	}
	
	
	
	
}


