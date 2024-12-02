package application;

import javafx.scene.control.TextField;


import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.Statement;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class dashboardController implements Initializable {
	 @FXML
	    private TableView<foodData> AvailableFoods_tableview;

	    @FXML
	    private Button AvailableFoods_addbtn;

	    @FXML
	    private Button AvailableFoods_clearbtn;

	    @FXML
	    private TableColumn<foodData, String> AvailableFoods_col_foodid;

	    @FXML
	    private TableColumn<foodData, String> AvailableFoods_col_foodname;

	    @FXML
	    private TableColumn<foodData, String> AvailableFoods_col_foodprice;

	    @FXML
	    private TableColumn<foodData, String> AvailableFoods_col_foodstatus;

	    @FXML
	    private Button AvailableFoods_deletebtn;

	    @FXML
	    private TextField AvailableFoods_foodid;

	    @FXML
	    private TextField AvailableFoods_foodname;

	    @FXML
	    private TextField AvailableFoods_foodprice;

	    @FXML
	    private ComboBox<?> AvailableFoods_foodstatus;

	    @FXML
	    private AnchorPane AvailableFoods_form;

	    @FXML
	    private ImageView AvailableFoods_imgView;

	    @FXML
	    private Button AvailableFoods_importbtn;

	    @FXML
	    private TextField AvailableFoods_search;

	    @FXML
	    private Button AvailableFoods_updatebtn;

	    @FXML
	    private Button availblebtn_form;

	    @FXML
	    private Button closebtn;

	    @FXML
	    private Label home_availblefoods;

	    @FXML
	    private Button home_btn;

	    @FXML
	    private BarChart<?, ?> home_chart;

	    @FXML
	    private AnchorPane home_form;

	    @FXML
	    private Label home_totalcustomers;

	    @FXML
	    private Label home_totalincome;

	    @FXML
	    private Button logoutbtn;

	    @FXML
	    private Button minibtn;

	    @FXML
	    private AnchorPane purchase_form;

	    @FXML
	    private Button purchase_paybtn;
	    @FXML
	    private ComboBox<?> purchase_foodname;
	    @FXML
	    private Button purchase_addtocart;

	    @FXML
	    private Spinner<Integer> purchase_quantity_f;

	    @FXML
	    private TableColumn<customerData, String> purchase_tablefoodid;

	    @FXML
	    private TableColumn<customerData, String> purchase_tablename;

	    @FXML
	    private TableColumn<customerData, String> purchase_tableprice;

	    @FXML
	    private TableColumn<customerData, String> purchase_tablequantity;

	    @FXML
	    private TableView<customerData> purchase_tableview;

	    @FXML
	    private Label purchase_total;

	    @FXML
	    private Button purchasebtn_form;

	    @FXML
	    private ComboBox<?> purchasefood_ID;

	    @FXML
	    private Label username_form;
	    
	    @FXML
	    private AnchorPane mainform;
	    private Connection connect;
	    private PreparedStatement prepare;
	    private Statement statement;
	    private ResultSet result;
	    private Image image;
	    public void homeAF() {
	    	String sql ="SELECT COUNT(id) FROM foods";
	    	connect =database.getconnection();
	    	try {
	    		int countAF =0;
				prepare=connect.prepareStatement(sql);
				result=prepare.executeQuery();
				if(result.next()) {
					countAF=result.getInt("COUNT(id)");
				}
				home_availblefoods.setText(String.valueOf(countAF));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public void totalTI() {
	    	String sql ="SELECT SUM(total) FROM customer_info";
	    	connect=database.getconnection();
	    	double countti = 0;
	    	try {
				prepare=connect.prepareStatement(sql);
				result=prepare.executeQuery();
				if(result.next()) {
					countti=result.getInt("SUM(total)");
				}
				home_totalincome.setText("$"+String.valueOf(countti));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	   
	    public void totalc() {
	    	String sql = "SELECT COUNT(id) FROM customer_info";
	    	connect = database.getconnection();
	    	try {
	    		int countc=0;
				prepare=connect.prepareStatement(sql);
				result=prepare.executeQuery();
				if(result.next()) {
					countc=result.getInt("COUNT(id)");
				}
				home_totalcustomers.setText(String.valueOf(countc));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public void homechart() {
	        home_chart.getData().clear();
	        String sql = "SELECT date, SUM(total) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 9";
	        connect = database.getconnection();
	        try {
	            XYChart.Series series = new XYChart.Series();
	            prepare = connect.prepareStatement(sql);
	            result = prepare.executeQuery();
	            while (result.next()) {
	                series.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
	            }
	            home_chart.getData().add(series);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void avialableFoodAdd() {
	        Connection connect = null;
	        Statement statement = null;
	        ResultSet result = null;
	        PreparedStatement prepare = null;

	        try {
	            connect = database.getconnection();
	            
	            Alert alert;
	            if (AvailableFoods_foodprice.getText().isEmpty() || AvailableFoods_foodname.getText().isEmpty() || 
	                AvailableFoods_foodstatus.getSelectionModel().getSelectedItem() == null ||
	                AvailableFoods_foodid.getText().isEmpty() || getData.path == null || getData.path.isEmpty()) {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            } else {
	                // Check if FoodID already exists
	                String CheckData = "SELECT FoodID FROM foods WHERE FoodID = ?";
	                prepare = connect.prepareStatement(CheckData);
	                prepare.setString(1, AvailableFoods_foodid.getText());
	                result = prepare.executeQuery();
	                
	                if (result.next()) {
	                    alert = new Alert(AlertType.ERROR);
	                    alert.setTitle("Error Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Food ID : " + AvailableFoods_foodid.getText() + " already exists!");
	                    alert.showAndWait();
	                } else {
	                    // Insert new food item
	                    String sql = "INSERT INTO foods (FoodID, name, status, price, date, image) VALUES (?, ?, ?, ?, ?, ?)";
	                    prepare = connect.prepareStatement(sql);
	                    prepare.setString(1, AvailableFoods_foodid.getText());
	                    prepare.setString(2, AvailableFoods_foodname.getText());
	                    prepare.setString(3, (String) AvailableFoods_foodstatus.getSelectionModel().getSelectedItem());
	                    prepare.setDouble(4, Double.parseDouble(AvailableFoods_foodprice.getText()));

	                    // Use java.sql.Date to handle SQL date
	                    java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
	                    prepare.setDate(5, sqlDate);
	                    
	                    // Handle image path
	                    String uri = getData.path;
	                    uri = uri.replace("\\", "\\\\");
	                    prepare.setString(6, uri);
	                    
	                    prepare.executeUpdate();
	                    alert = new Alert(AlertType.INFORMATION);
		                alert.setTitle("Information Message");
		                alert.setHeaderText(null);
		                alert.setContentText("Successfully Added !");
		                alert.showAndWait();
	                    availbleFoodlistDatalistdata();
	                    avialblefoodsclear();
	                }
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources in the finally block
	            try {
	                if (result != null) result.close();
	                if (prepare != null) prepare.close();
	                if (connect != null) connect.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public void updateAvailableFood() {
	        String uri = getData.path;
	        if(!(uri ==null || uri=="")) {
	        	uri = uri.replace("\\", "\\\\");
	        }
	        
	        String sql = "UPDATE `foods` SET `name`=?, `status`=?, `price`=?, `image`=? WHERE `FoodID`=?";
	        Connection connect = null;
	        PreparedStatement prepare = null;
	        Alert alert;
	        
	        try {
	            // Validate input fields
	            if (AvailableFoods_foodprice.getText().isEmpty() ||
	                AvailableFoods_foodname.getText().isEmpty() ||
	                AvailableFoods_foodstatus.getSelectionModel().getSelectedItem() == null ||
	                AvailableFoods_foodid.getText().isEmpty() ||
	                uri == null || uri=="") {

	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	                return; // Exit the method if validation fails
	            }
	            
	            // Establish a connection to the database
	            connect = database.getconnection();
	            
	            // Prepare the SQL statement
	            prepare = connect.prepareStatement(sql);
	            prepare.setString(1, AvailableFoods_foodname.getText());
	            prepare.setString(2, (String) AvailableFoods_foodstatus.getSelectionModel().getSelectedItem());
	            prepare.setString(3, AvailableFoods_foodprice.getText());
	            prepare.setString(4, uri);
	            prepare.setString(5, AvailableFoods_foodid.getText());
	            
	            // Confirm with the user before updating
	            alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Confirmation Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Are you sure you want to UPDATE Food ID: " + AvailableFoods_foodid.getText() + "?");
	            Optional<ButtonType> option = alert.showAndWait();
	            
	            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
	                // Execute the update statement
	                int affectedRows = prepare.executeUpdate();
	                
	                if (affectedRows > 0) {
	                    // Successfully updated
	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Success");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Food ID: " + AvailableFoods_foodid.getText() + " updated successfully.");
	                    alert.showAndWait();
	                } else {
	                    // No rows updated, possible error
	                    alert = new Alert(AlertType.WARNING);
	                    alert.setTitle("Warning");
	                    alert.setHeaderText(null);
	                    alert.setContentText("No record found with Food ID: " + AvailableFoods_foodid.getText());
	                    alert.showAndWait();
	                }
	            }
	            
	        } catch (SQLException e) {
	            // Handle SQL exceptions
	            e.printStackTrace();
	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Database Error");
	            alert.setHeaderText(null);
	            alert.setContentText("An error occurred while updating the food information. Please try again.");
	            alert.showAndWait();
	            
	        } finally {
	            // Close resources
	            if (prepare != null) {
	                try {
	                    prepare.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (connect != null) {
	                try {
	                    connect.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        // Refresh the list data
	        availbleFoodlistDatalistdata();
	        avialblefoodsclear();
	    }

	    public void avialblefoodsclear() {
	    	AvailableFoods_foodid.setText("");
	    	AvailableFoods_foodname.setText("");
	    	AvailableFoods_foodstatus.getSelectionModel().clearSelection();
	    	AvailableFoods_foodprice.setText("");
	    	getData.path="";
	    	AvailableFoods_imgView.setImage(null);
	    	
	    }
	    public void avialableFoodsInsertimage() {
	    	FileChooser open = new FileChooser();
	    	open.setTitle("Open Image File");
	    	open.getExtensionFilters().add(new ExtensionFilter("Image Filter","*jpg","*png"));
	    	File file= open.showOpenDialog(mainform.getScene().getWindow());
	    	if (file !=null) {
	    		getData.path=file.getAbsolutePath();
	    		image =new Image(file.toURI().toString(),129,130,false,true);
	    		AvailableFoods_imgView.setImage(image);
	    	}
	    	}
	    
	    public void AvialableFoodsSelect() {
	    	
	    	foodData food = AvailableFoods_tableview.getSelectionModel().getSelectedItem();
	    	int num =AvailableFoods_tableview.getSelectionModel().getSelectedIndex();
	    	if((num-1)<-1) return;
	    	AvailableFoods_foodid.setText(String.valueOf(food.getFoodID()));
	    	AvailableFoods_foodname.setText(food.getFoodName());
	    	
	    	AvailableFoods_foodprice.setText(String.valueOf(food.getFoodPrice()));
	    	getData.path=food.getFoodImage();
	    	String uri = "file:"+food.getFoodImage();
	    	
	    	image = new Image(uri,126,145,false ,true);
	    	AvailableFoods_imgView.setImage(image);
	    }
	    public void DeleteFood() {
	    	
	    	foodData food=AvailableFoods_tableview.getSelectionModel().getSelectedItem();
	    
	    	String uri = getData.path;
	    	if(!(uri==null || uri=="")) {
	    		uri = uri.replace("\\", "\\\\");
	    	}
	        
	        Alert alert;
	    	int FoodId= food.getFoodID();
	    	String sql = "DELETE FROM foods where FoodID = ?";
	    	connect=database.getconnection();
	    	try {
	    		   if (AvailableFoods_foodprice.getText().isEmpty() ||
	   	                AvailableFoods_foodname.getText().isEmpty() ||
	   	                AvailableFoods_foodstatus.getSelectionModel().getSelectedItem() == null ||
	   	                AvailableFoods_foodid.getText().isEmpty() ||
	   	                uri == null || uri.isEmpty()) {
	    			   	
	   	                alert = new Alert(AlertType.ERROR);
	   	                alert.setTitle("Error Message");
	   	                alert.setHeaderText(null);
	   	                alert.setContentText("Please fill all blank fields");
	   	                alert.showAndWait();
	   	                return; // Exit the method if validation fails
	   	            }
	   	            
	   	            // Establish a connection to the database
	   	           
	   	            
	   	            // Prepare the SQL statement
	    		   prepare=connect.prepareStatement(sql);
					prepare.setString(1, AvailableFoods_foodid.getText());
	   	            // Confirm with the user before updating
	   	            alert = new Alert(AlertType.CONFIRMATION);
	   	            alert.setTitle("Confirmation Message");
	   	            alert.setHeaderText(null);
	   	            alert.setContentText("Are you sure you want to Delete Food ID: " + AvailableFoods_foodid.getText() + "?");
	   	            Optional<ButtonType> option = alert.showAndWait();
	   	            
	   	            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
	   	                // Execute the update statement
	   	                int affectedRows = prepare.executeUpdate();
	   	                
	   	                if (affectedRows > 0) {
	   	                    // Successfully updated
	   	                    alert = new Alert(AlertType.INFORMATION);
	   	                    alert.setTitle("Success");
	   	                    alert.setHeaderText(null);
	   	                    alert.setContentText("Food ID: " + AvailableFoods_foodid.getText() + " Deleted successfully.");
	   	                    alert.showAndWait();
	   	                } else {
	   	                    // No rows updated, possible error
	   	                    alert = new Alert(AlertType.WARNING);
	   	                    alert.setTitle("Warning");
	   	                    alert.setHeaderText(null);
	   	                    alert.setContentText("No record found with Food ID: " + AvailableFoods_foodid.getText());
	   	                    alert.showAndWait();
	   	                }
	   	            }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  availbleFoodlistDatalistdata();
		        avialblefoodsclear();
	    	
	    }
	    public ObservableList<foodData> availbleFoodlistData() {
	        ObservableList<foodData> listData = FXCollections.observableArrayList();
	        connect = database.getconnection();
	        String sql = "SELECT FoodID , name , status , price , date ,image FROM foods";
	        try {
	            prepare = connect.prepareStatement(sql);
	            result = prepare.executeQuery();
	            
	            foodData food;
	            while (result.next()) {
	            	
	                food = new foodData(
	                    result.getInt("FoodID"),
	                    result.getString("name"),
	                    result.getString("status"),
	                    result.getDouble("price"),
	                    result.getDate("date"),
	                    result.getString("image")
	                );
	                listData.add(food);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return listData;
	    }
	 
	    String ListStatus[] = {"Availble","Not Available"};
	    public void avialblefoodstatus() {
	    	List<String> lists = new ArrayList<>();
	    	for(String data : ListStatus ) {
	    		lists.add(data);
	    	}
	    	ObservableList listData  = FXCollections.observableArrayList(lists);
	    	AvailableFoods_foodstatus.setItems(listData);
	    } 
	  
	     
	    private ObservableList<foodData> availablefoodlist;
	    public void availbleFoodlistDatalistdata() {
	        availablefoodlist = availbleFoodlistData();
	        AvailableFoods_col_foodid.setCellValueFactory(new PropertyValueFactory<>("foodID"));
	        AvailableFoods_col_foodname.setCellValueFactory(new PropertyValueFactory<>("foodName"));
	        AvailableFoods_col_foodstatus.setCellValueFactory(new PropertyValueFactory<>("foodStatus"));
	        AvailableFoods_col_foodprice.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));

	        AvailableFoods_tableview.setItems(availablefoodlist);
	    }
	    public void availableFoodSearch() {
	    	
	        // Create a FilteredList from the availablefoodlist
	        FilteredList<foodData> filteredList = new FilteredList<>(availablefoodlist, e -> true);

	        // Add a listener to the search text field
	        AvailableFoods_search.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredList.setPredicate(predicateFoodData -> {
	                // If the search field is empty or null, show all items
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Convert search text to lower case for case-insensitive comparison
	                String searchKey = newValue.toLowerCase();

	                // Perform the search on different fields of foodData
	                boolean matchesID = String.valueOf(predicateFoodData.getFoodID()).toLowerCase().contains(searchKey);
	                boolean matchesName = predicateFoodData.getFoodName().toLowerCase().contains(searchKey);
	                boolean matchesStatus = predicateFoodData.getFoodStatus().toLowerCase().contains(searchKey);
	                boolean matchesPrice = String.valueOf(predicateFoodData.getFoodPrice()).toLowerCase().contains(searchKey);

	                // Return true if any of the fields match the search key
	                return matchesID || matchesName || matchesStatus || matchesPrice;
	            });
	            // Create a SortedList from the FilteredList
		        SortedList<foodData> sortedList = new SortedList<>(filteredList);

		        // Bind the SortedList comparator to the TableView comparator
		        sortedList.comparatorProperty().bind(AvailableFoods_tableview.comparatorProperty());

		        // Set the SortedList as the items for the TableView
		        AvailableFoods_tableview.setItems(sortedList);
	        });

	     
	    }


	    public void DisplayUsername() {
	    	String user =getData.username;
	    	username_form.setText(user.substring(0,1).toUpperCase()+user.substring(1));
	    }
	    public void closeform() {
	    	System.exit(0);
	    	
	    }
	    public void Switchform(ActionEvent event) {
	    	if(event.getSource()==home_btn) {
	    		purchase_form.setVisible(false);
	    		AvailableFoods_form.setVisible(false);
	    		home_form.setVisible(true);
	    		home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#d3133d,#a4262f);");
	    		availblebtn_form.setStyle("-fx-backgroundcolor:transparent");
	    		purchasebtn_form.setStyle("-fx-backgroundcolor:transparent");
	    		totalTI();
	    		totalc();
	    		homeAF();
	    		homechart();
	    	}else if(event.getSource()==availblebtn_form) {
	    		purchase_form.setVisible(false);
	    		AvailableFoods_form.setVisible(true);
	    		home_form.setVisible(false);
	    		availblebtn_form.setStyle("-fx-background-color:linear-gradient(to bottom right,#d3133d,#a4262f);");
	    		home_btn.setStyle("-fx-backgroundcolor:transparent");
	    		purchasebtn_form.setStyle("-fx-backgroundcolor:transparent");
	    		
	    		// to show upated foods
	    		availbleFoodlistDatalistdata();
	    		availableFoodSearch();
	    		avialblefoodstatus();
	    	}else if(event.getSource()==purchasebtn_form) {
	    		purchase_form.setVisible(true);
	    		AvailableFoods_form.setVisible(false);
	    		home_form.setVisible(false);
	    		
	    		purchasebtn_form.setStyle("-fx-background-color:linear-gradient(to bottom right,#d3133d,#a4262f);");
	    		home_btn.setStyle("-fx-backgroundcolor:transparent");
	    		availblebtn_form.setStyle("-fx-backgroundcolor:transparent");
	    		purchaseShowListData();
	    		purchasefoodId();
	    		purchaseFoodName();
	    		purchasespinner();
	    		purchasedisplaytotal();
	    	}
	    }
	    
	    private double x=0;
	    private double y=0;
	    public void logout() {
	    	
	    	try {
	    		Alert alert= new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("Confirmation Message");
        		alert.setHeaderText(null);
        		alert.setContentText("Are you sure want to logout ?");
        		Optional<ButtonType> option =alert.showAndWait();
        		if(option.get().equals(ButtonType.OK)) {
        			logoutbtn.getScene().getWindow().hide();
        			Parent r = (Parent)FXMLLoader.load(getClass().getResource("Sample.fxml"));
        			Scene  scene = new Scene(r);
        			Stage stage = new Stage();
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
        				stage.setOpacity(.8);
        			});
        			r.setOnMouseReleased((MouseEvent e) -> {
        				stage.setOpacity(1);
        			});
        			stage.initStyle(StageStyle.TRANSPARENT);
        			
        			stage.show();
        		}
	    	}
	    	catch(Exception e) {
	    		
	    	}
	    }
	    private SpinnerValueFactory<Integer> spinner;
	    public void purchasespinner() {
	    	spinner=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
	    	purchase_quantity_f.setValueFactory(spinner);
	    }
	    private int qty;
	    public void purchaseQuantity() {
	    	qty=purchase_quantity_f.getValue();
	    }
	    public ObservableList<customerData> purchaseListData(){
	    	purchaseCustomerId();
	    	ObservableList<customerData> listData= FXCollections.observableArrayList();
	    	String sql = "SELECT * FROM customer where customer_id='"+customerId+"'";
	    	connect=database.getconnection();
	    	try {
				prepare=connect.prepareStatement(sql);
				result=prepare.executeQuery();
				customerData customer;
				while(result.next()) {
					customer = new customerData(result.getInt("customer_id"),
							result.getInt("food_id"),result.getString("name"),
							result.getInt("quantity"),result.getDouble("price"),
							result.getDate("date"));
					listData.add(customer);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return listData;
	    }
	    public void AddToCart() {
	    	purchaseCustomerId();
	    	String sql = "INSERT INTO `customer`(`customer_id`, `food_id`, `name`, `quantity`, `price`, `date`) VALUES (?,?,?,?,?,?)";
	    	connect = database.getconnection();
	    	try {
	    		Alert alert;
	    		if(purchasefood_ID.getSelectionModel().getSelectedItem()==null || purchase_foodname.getSelectionModel().getSelectedItem() == null || qty ==0 ) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Error Message");
	    			alert.setHeaderText(null);
	    			alert.setContentText("please choose the product first ");
	    			alert.showAndWait();
	    		}
	    		else {
	    			double pricedata=0;
		    		double Totalprice;
		    		String checkprice = "SELECT name,price FROM foods "
		    				+ "where name = '"+purchase_foodname.getSelectionModel().getSelectedItem()+"'";
		    		prepare = connect.prepareStatement(checkprice);
		    		result=prepare.executeQuery();
		    		
		    		if(result.next()) {
		    			pricedata=result.getDouble("price");
		    		}
		    		
					prepare=connect.prepareStatement(sql);
					prepare.setInt(1,customerId);
					prepare.setInt(2,(Integer)purchasefood_ID.getSelectionModel().getSelectedItem());
					prepare.setString(3, (String)purchase_foodname.getSelectionModel().getSelectedItem());
					prepare.setInt(4,qty);
					Totalprice=(pricedata * qty);
					prepare.setDouble(5, Totalprice);
					Date date = new Date();
					java.sql.Date sqldate  = new java.sql.Date(date.getTime());
					prepare.setDate(6,sqldate);
	    		
					
					prepare.executeUpdate();
					purchaseShowListData();
					purchasedisplaytotal();
	    		}
	    		
	    		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public void purchasePay() {
	    	String sql ="INSERT INTO `customer_info`(`customer_id`, `total`, `date`) VALUES (?,?,?)";
	    	connect=database.getconnection();
	    	try {
	    		Alert alert;
	    		if (totalp==0) {
	    			alert= new Alert(AlertType.ERROR);
		    		alert.setHeaderText(null);
		    		alert.setTitle("Error Message");
		    		alert.setContentText("Somthing Wrong :3");
		    		alert.showAndWait();
	    		}else {
	    			alert= new Alert(AlertType.CONFIRMATION);
		    		alert.setHeaderText(null);
		    		alert.setTitle("Confirmation Message");
		    		alert.setContentText("Are you sure?");
		    		Optional<ButtonType> option = alert.showAndWait();
		    		if(option.get().equals(ButtonType.OK)) {
		    			prepare=connect.prepareStatement(sql);
						prepare.setString(1, String.valueOf(customerId));
						prepare.setString(2,String.valueOf(totalp));
						Date date = new Date();
						java.sql.Date sqldate = new java.sql.Date(date.getTime());
						prepare.setString(3, String.valueOf(sqldate));
						prepare.executeUpdate();
						alert= new Alert(AlertType.INFORMATION);
			    		alert.setHeaderText(null);
			    		alert.setTitle("Information Message");
			    		alert.setContentText("Successful ! Thank for purchase .");
			    		alert.showAndWait();
						totalp=0;
						purchaseShowListData();
		    		}
	    		
	    		}
	    		
			
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    private double totalp=0;
	    public void purchasedisplaytotal() {
	    	purchaseCustomerId();
	    	String sql="SELECT SUM(price) from customer where customer_id = '"+customerId+"' ";
	    	connect = database.getconnection();
	    	try {
				prepare = connect.prepareStatement(sql);
				result = prepare.executeQuery();
				if(result.next()) {
					totalp=result.getDouble("SUM(price)");
				}
				purchase_total.setText("$"+String.valueOf(totalp));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    public void purchasefoodId() {
	    	String sql = "SELECT status,FoodID FROM foods where status='Availble'";
	    	connect=database.getconnection();
	    	try {
	    		ObservableList listData= FXCollections.observableArrayList();
				prepare=connect.prepareStatement(sql);
				result=prepare.executeQuery();
				while(result.next()) {
					listData.add(result.getInt("FoodID"));
					
				}
				purchasefood_ID.setItems(listData);
				purchaseFoodName();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    public void purchaseFoodName() {
	    	String sql ="SELECT FoodID ,name FROM foods where FoodID='"+purchasefood_ID.getSelectionModel().getSelectedItem()+"' ";
	    	connect = database.getconnection();
	    	try {
				prepare = connect.prepareStatement(sql);
				result= prepare.executeQuery();
				ObservableList listData = FXCollections.observableArrayList();
				while(result.next()) {
					listData.add(result.getString("name"));
				}
				purchase_foodname.setItems(listData);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	    private ObservableList<customerData> purchaseListD;
	    public void purchaseShowListData() {
	    	purchaseListD=purchaseListData();
	    	purchase_tablefoodid.setCellValueFactory(new PropertyValueFactory<>("foodId"));
	    	purchase_tablename.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	purchase_tablequantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	    	purchase_tableprice.setCellValueFactory(new PropertyValueFactory<>("price"));
	    	purchase_tableview.setItems(purchaseListD);
	    }
	    private int customerId;
	    public void purchaseCustomerId() {
	    	String sql ="SELECT MAX(customer_id) from customer";
	    
	    	connect=database.getconnection();
	    	try {
				prepare=connect.prepareStatement(sql);
				result=prepare.executeQuery();
				if(result.next()) {
					customerId=result.getInt("MAX(customer_id)");
				}
				int countData=0;
				String checkInfo="SELECT MAX(customer_id) from customer_info";
				prepare=connect.prepareStatement(checkInfo);
				result = prepare.executeQuery();
				if(result.next()) {
					countData=result.getInt("MAX(customer_id)");
				}
				if(customerId==0) {
					customerId+=1;
				}
				else if(customerId==countData) {
					customerId=countData+1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public void minimize() {
	    	Stage stage =(Stage)mainform.getScene().getWindow();
	    	stage.setIconified(true);
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			DisplayUsername();
			availbleFoodlistDatalistdata();
			avialblefoodstatus();
			purchaseShowListData();
			purchasefoodId();
			purchaseFoodName();	
			purchasespinner();
			purchasedisplaytotal();
			totalTI();
			totalc();
			homeAF();
			homechart();
		}
	   
	
	    
}
