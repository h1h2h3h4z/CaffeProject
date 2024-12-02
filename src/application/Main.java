package application;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	 private double x=0;
	 private double y=0;
	@Override
	
	public void start(Stage primaryStage) {
		
		try {
			Parent r = (Parent) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(r, 650, 440);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			// hide close and extand
			r.setOnMousePressed((MouseEvent e) -> {
			    // Your code to handle the mouse press event goes here.
			    x=e.getSceneX();
			    y=e.getSceneY();
			     
			});
			
			r.setOnMouseDragged((MouseEvent e) -> {
			    // Your code to handle the mouse press event goes here.
				primaryStage.setX(e.getScreenX()-x);
				primaryStage.setY(e.getScreenY()-y);
				r.setOpacity(0.7);
				
				
				
			});
			r.setOnMouseReleased((MouseEvent e) -> {
				r.setOpacity(1);
			});
			primaryStage.initStyle(StageStyle.TRANSPARENT);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
