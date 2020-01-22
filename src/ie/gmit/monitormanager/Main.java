package ie.gmit.monitormanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {

	Scene primaryScene, addItemsScene, deleteItemsScene;
	private static final long serialVersionUID = 1L; // Used for serialization
	MonitorManager mm = new MonitorManager(); // Used for managing students

	public static final String Style = "Style.css";

	public void start(Stage primaryStage) {

		// Creates BorderPane
		BorderPane root = new BorderPane();

		// File Menu - "_" Alt + F to open menu shortcut
		Menu fileMenu = new Menu("_File");

		// Menu Items
		MenuItem newFile = new MenuItem("Quit");
		newFile.setOnAction(e -> Platform.exit());
		fileMenu.getItems().add(newFile);

		// Menu Bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);

		// Sets title of stage
		primaryStage.setTitle("Computer Monitor Manager");

		// Creates GridPane
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(false); // Change to true to show grid lines
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setVgap(15);
		grid.setHgap(20);

		// Add components to root (BorderPane)
		root.setTop(menuBar);
		root.setCenter(grid);

		// Column and Row Constraints
		ColumnConstraints col1 = new ColumnConstraints(300, 300, Double.MAX_VALUE);
		ColumnConstraints col2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		ColumnConstraints col3 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints rowEmpty = new RowConstraints();

		// Makes columns grow horizontal
		col1.setHgrow(Priority.ALWAYS);
		col2.setHgrow(Priority.ALWAYS);
		col3.setHgrow(Priority.ALWAYS);

		// Adds Column and Row Constraints
		grid.getColumnConstraints().add(0, col1);
		grid.getColumnConstraints().add(1, col2);
		grid.getColumnConstraints().add(2, col3);
		grid.getRowConstraints().add(0, rowEmpty);
		grid.getRowConstraints().add(1, rowEmpty);
		grid.getRowConstraints().add(2, rowEmpty);
		grid.getRowConstraints().add(3, rowEmpty);
		grid.getRowConstraints().add(4, rowEmpty);
		grid.getRowConstraints().add(5, rowEmpty);
		grid.getRowConstraints().add(6, rowEmpty);
		grid.getRowConstraints().add(7, rowEmpty);
		grid.getRowConstraints().add(8, rowEmpty);
		grid.getRowConstraints().add(9, rowEmpty);
		grid.getRowConstraints().add(10, rowEmpty);
		grid.getRowConstraints().add(11, rowEmpty);

		// Log label
		Label log = new Label("Log");
		GridPane.setConstraints(log, 0, 4);

		// Log/Output text area
		TextArea logOutput = new TextArea();
		logOutput.setEditable(false);
		logOutput.setWrapText(true);
		GridPane.setConstraints(logOutput, 0, 5);
		GridPane.setRowSpan(logOutput, 7);

		// Settings label
		Label settings = new Label("Settings");
		GridPane.setConstraints(settings, 0, 0);
		GridPane.setColumnSpan(settings, 2);

		// Load Database button
		Button loadDatabaseBtn = new Button("Load Database");
		loadDatabaseBtn.setPadding(new Insets(5, 10, 5, 10));
		loadDatabaseBtn.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(loadDatabaseBtn, 0, 1);
		GridPane.setColumnSpan(loadDatabaseBtn, 1);

		// Load Database input field
		TextField loadDatabaseItemInput = new TextField();
		loadDatabaseItemInput.setPromptText("Enter Database Path");
		loadDatabaseItemInput.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(loadDatabaseItemInput, 0, 3);
		GridPane.setColumnSpan(loadDatabaseItemInput, 1);
		loadDatabaseBtn.setOnAction(e -> {

			try {
				File monitorDB = new File(loadDatabaseItemInput.getText());
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(monitorDB));
				mm = (MonitorManager) in.readObject();
				in.close();
				logOutput.setText("Successfully loaded Monitors from Database!");
			} catch (Exception exception) {
				exception.printStackTrace();
				logOutput.setText("ERROR: Failed to load Monitors Database!");
			}

		});

		// Orders label
		Label orders = new Label("Orders");
		GridPane.setConstraints(orders, 1, 0);

		/* Add Items Scene */

		// Add Item button
		Button addItemBtn = new Button("Add Item");
		addItemBtn.setPadding(new Insets(5, 10, 5, 10));
		addItemBtn.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(addItemBtn, 1, 1);
		GridPane.setColumnSpan(addItemBtn, 1);
		// Changes from primaryScene to addItemsScene
		addItemBtn.setOnAction(e -> primaryStage.setScene(addItemsScene));

		// Go Back button
		Button goBackBtn = new Button("Go Back");
		goBackBtn.setPadding(new Insets(5, 10, 5, 10));
		goBackBtn.setMaxWidth(Double.MAX_VALUE);
		// Changes from addItemsScene to primaryScene
		goBackBtn.setOnAction(e -> primaryStage.setScene(primaryScene));

		// Add New Item input field
		TextField addModelMake = new TextField();
		addModelMake.setPromptText("Enter Model Make");
		addModelMake.setMaxWidth(Double.MAX_VALUE);

		// Add New Item input field
		TextField addModelNumber = new TextField();
		addModelNumber.setPromptText("Enter Model #");
		addModelNumber.setMaxWidth(Double.MAX_VALUE);

		// Add Items View - Creates VBox
		VBox addItemLayout = new VBox(10);
		addItemLayout.setSpacing(15);
		addItemLayout.setPadding(new Insets(200));
		addItemLayout.setAlignment(Pos.CENTER);

		// Add Item View button
		Button addItemViewBtn = new Button("Add Item");
		addItemViewBtn.setPadding(new Insets(5, 10, 5, 10));
		addItemViewBtn.setMaxWidth(Double.MAX_VALUE);
		addItemViewBtn.setOnAction(e -> {
			if (addModelMake.getText().trim().equals("") || addModelNumber.getText().trim().equals("")) {
				// If empty output Invalid
				logOutput.setText("You entered an invalid item..");
				Alert.display("Alert!", "You entered an invalid item..");
			} else {
				Monitor monitor = new Monitor(addModelMake.getText(), addModelNumber.getText());
				mm.addMonitor(monitor); // Add student to student list
				logOutput.setText(
						addModelMake.getText() + " " + addModelNumber.getText() + " has been successfully added!");
				Alert.display("Alert!",
						addModelMake.getText() + " " + addModelNumber.getText() + " has been successfully added!");
				addModelMake.clear();
				addModelNumber.clear();
			}
		});

		addItemLayout.getChildren().addAll(goBackBtn, addModelMake, addModelNumber, addItemViewBtn);
		addItemsScene = new Scene(addItemLayout, 800, 500);

		addItemsScene.getStylesheets().add(Style);

		/* Delete Items Scene */

		// Delete Item button
		Button deleteItemBtn = new Button("Delete Item");
		deleteItemBtn.setPadding(new Insets(5, 10, 5, 10));
		deleteItemBtn.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(deleteItemBtn, 2, 1);
		GridPane.setColumnSpan(deleteItemBtn, 1);
		// Changes from primaryScene to addItemsScene
		deleteItemBtn.setOnAction(e -> primaryStage.setScene(deleteItemsScene));

		// Go Back button
		Button goBackBtn2 = new Button("Go Back");
		goBackBtn2.setPadding(new Insets(5, 10, 5, 10));
		goBackBtn2.setMaxWidth(Double.MAX_VALUE);
		// Changes from addItemsScene to primaryScene
		goBackBtn2.setOnAction(e -> primaryStage.setScene(primaryScene));

		// Delete New Item input field
		TextField deleteModelMake = new TextField();
		deleteModelMake.setPromptText("Enter Model Make");
		deleteModelMake.setMaxWidth(Double.MAX_VALUE);

		// Delete New Item input field
		TextField deleteModelNumber = new TextField();
		deleteModelNumber.setPromptText("Enter Model #");
		deleteModelNumber.setMaxWidth(Double.MAX_VALUE);

		// Delete Items View - Creates VBox
		VBox deleteItemLayout = new VBox(10);
		deleteItemLayout.setSpacing(15);
		deleteItemLayout.setPadding(new Insets(200));
		deleteItemLayout.setAlignment(Pos.CENTER);

		// Delete Item View button
		Button deleteItemViewBtn = new Button("Delete Item");
		deleteItemViewBtn.setPadding(new Insets(5, 10, 5, 10));
		deleteItemViewBtn.setMaxWidth(Double.MAX_VALUE);
		deleteItemViewBtn.setOnAction(e -> {

			if (deleteModelMake.getText().trim().equals("") || deleteModelNumber.getText().trim().equals("")) {
				// If empty output Invalid
				logOutput.setText("You entered an invalid item..");
				Alert.display("Alert!", "You entered an invalid item..");
			} else {
				mm.deleteModelByMake(deleteModelMake.getText());
				mm.deleteModelByMake(deleteModelNumber.getText());
				logOutput.setText(deleteModelMake.getText() + " " + deleteModelNumber.getText()
						+ " has been successfully deleted!");
				Alert.display("Alert!", deleteModelMake.getText() + " " + deleteModelNumber.getText()
						+ " has been successfully deleted!");
				deleteModelMake.clear();
				deleteModelNumber.clear();

			}
		});

		deleteItemLayout.getChildren().addAll(goBackBtn2, deleteModelMake, deleteModelNumber, deleteItemViewBtn);
		deleteItemsScene = new Scene(deleteItemLayout, 800, 500);

		deleteItemsScene.getStylesheets().add(Style);

		// Search label
		Label search = new Label("Search Item");
		GridPane.setConstraints(search, 1, 4);

		// Search input field
		TextField searchInput = new TextField();
		searchInput.setPromptText("Find item by Name/Make");
		searchInput.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(searchInput, 1, 5);
		GridPane.setColumnSpan(searchInput, 2);

		// Find Item button
		Button findItemBtn = new Button("Find Item");
		findItemBtn.setPadding(new Insets(5, 10, 5, 10));
		findItemBtn.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(findItemBtn, 1, 6);
		GridPane.setColumnSpan(findItemBtn, 2);
		findItemBtn.setOnAction(e -> {
			Monitor monitorObj = mm.findModelByMake(searchInput.getText());
			logOutput.setText("Monitor: " + monitorObj.getModelMake() + " " + monitorObj.getModelNumber());
		});

		// Show Total Items label
		Label showTotalItems = new Label("Show Total Items");
		GridPane.setConstraints(showTotalItems, 1, 7);

		// Show Total Items input field
		TextField showTotalItemsInput = new TextField();
		showTotalItemsInput.setPromptText("Show Total Number of Items");
		showTotalItemsInput.setMaxWidth(Double.MAX_VALUE);
		showTotalItemsInput.setEditable(false);
		GridPane.setConstraints(showTotalItemsInput, 1, 8);
		GridPane.setColumnSpan(showTotalItemsInput, 2);

		// Show Total Items button
		Button showTotalItemsBtn = new Button("Show Items");
		showTotalItemsBtn.setPadding(new Insets(5, 10, 5, 10));
		showTotalItemsBtn.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(showTotalItemsBtn, 1, 9);
		GridPane.setColumnSpan(loadDatabaseBtn, 1);
		GridPane.setColumnSpan(showTotalItemsBtn, 2);
		// On Click, Show Number of Monitors
		showTotalItemsBtn.setOnAction(e -> {
			showTotalItemsInput.setText(Integer.toString(mm.findTotalMonitors()));
		});

		// Save label
		Label save = new Label("Save");
		GridPane.setConstraints(save, 1, 10);

		// Save to Database button
		Button saveDatabaseBtn = new Button("Save to Database");
		saveDatabaseBtn.setPadding(new Insets(5, 10, 5, 10));
		saveDatabaseBtn.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(saveDatabaseBtn, 1, 11);
		GridPane.setColumnSpan(saveDatabaseBtn, 2);
		saveDatabaseBtn.setOnAction(e -> {
			if (mm.findTotalMonitors() > 0) {
				try {
					File monitorDB = new File("./resources/monitorsDB.ser");
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(monitorDB));
					out.writeObject(mm);
					out.close();
					logOutput.setText("Monitor Database Saved!");
				} catch (Exception exception) {
					exception.printStackTrace();
					logOutput.setText("ERROR: Failed to save Monitors Database!");
				}
			} else {
				logOutput.setText("No Monitors in List to save!");
			}
		});

		// Adds items to grid
		grid.getChildren().addAll(settings, loadDatabaseBtn, loadDatabaseItemInput, log, logOutput, orders, addItemBtn,
				deleteItemBtn, search, searchInput, findItemBtn, showTotalItems, showTotalItemsInput, showTotalItemsBtn,
				save, saveDatabaseBtn);

		// Creates primaryScene
		primaryScene = new Scene(root, 800, 500);

		primaryScene.getStylesheets().add(Style);

		// Sets scene as primaryScene
		primaryStage.setScene(primaryScene);
		// Shows window
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
