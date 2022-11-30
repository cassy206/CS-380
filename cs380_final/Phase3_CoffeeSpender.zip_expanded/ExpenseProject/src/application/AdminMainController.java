package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class AdminMainController implements Initializable  {

    @FXML
    private TableColumn<users, String> col_email;

    @FXML
    private TableColumn<users, Integer> col_id;

    @FXML
    private TableColumn<users, String> col_password;
    
    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TableView<users> table_users;
    
    ObservableList<users> listM;
    ObservableList<users> dataList;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void Add_users (){    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into registration (email,password)values(?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_email.getText());
            pst.setString(2, txt_password.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add success");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
    	col_email.setCellValueFactory(new PropertyValueFactory<users, String>("email"));
    	col_password.setCellValueFactory(new PropertyValueFactory<users, String>("password"));
    	
    	listM = mysqlconnect.getDatausers();
    	table_users.setItems(listM);
    // Code Source in description
    } 

}
