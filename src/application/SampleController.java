package application;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SampleController {

    @FXML
    private Button closebtn;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    public TextField username;
    public void close() {
    	System.exit(0);
    	
    }
    private double x=0;
    private double y=0;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
  
    public void login() {
    	
    	String sql ="Select * From admin WHERE username = ? and password =?";
    	connect= database.getconnection();
    	try {
    		Alert alert;
     		prepare=connect.prepareStatement(sql);
    		prepare.setString(1, username.getText());
    		prepare.setString(2, password.getText());
    		result = prepare.executeQuery();
    		
        	if(username.getText().isEmpty() || password.getText().isEmpty()) {
        		alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Error Message");
        		alert.setHeaderText(null);
        		alert.setContentText("please enter the field blank");
        		alert.showAndWait();
        	}
        	else {
        		if(result.next()) {
        			alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Correct Information");
            		alert.setHeaderText(null);
            		alert.setContentText("Sucessfully Registration");
            		alert.showAndWait();
            		loginbtn.getScene().getWindow().hide();
        			getData.username=username.getText();
        			Parent r = (Parent)FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        			Scene scene = new Scene(r,1100,600);
        			Stage stage =new Stage();
        			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        			stage.setScene(scene);
        			
        			r.setOnMousePressed((MouseEvent e) -> {
        			    // Your code to handle the mouse press event goes here.
        			    x=e.getSceneX();
        			    y=e.getSceneY();
        			});
        			
        			r.setOnMouseDragged((MouseEvent e) -> {
        			    // Your code to handle the mouse press event goes here.
        			   stage.setX(e.getScreenX()-x);
        			   stage.setY(e.getScreenY()-y);
        			   r.setOpacity(0.7);
        			});
        			r.setOnMouseReleased((MouseEvent e)->{
        				r.setOpacity(1);
        			});
        			stage.initStyle(StageStyle.TRANSPARENT);
        			stage.show();
        			
        		}
        		else {
        			alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error Message");
            		alert.setHeaderText(null);
            		alert.setContentText("Username or Password Incorect");
            		alert.showAndWait();
        		}
        	}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		
    	}
    }
    
    
    
    
    
    
    
}
