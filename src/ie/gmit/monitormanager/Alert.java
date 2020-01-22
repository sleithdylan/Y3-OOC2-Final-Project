package ie.gmit.monitormanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {
	public static void display(String title, String message) {
		// Creates alertStage
		Stage alertStage = new Stage();
		// Block user interaction with other windows
		alertStage.initModality(Modality.APPLICATION_MODAL);
		// Sets title of stage
		alertStage.setTitle(title);
		
		// Alert label
		Label alert = new Label();
		alert.setText(message);
		
		// Close button
		Button closeBtn = new Button("OK");
		closeBtn.setPadding(new Insets(5, 10, 5, 10));
		closeBtn.setMaxWidth(Double.MAX_VALUE);
		closeBtn.setOnAction(e -> alertStage.close());
		
		// Alert View - Creates VBox
		VBox alertLayout = new VBox(10);
		alertLayout.setSpacing(15);
		alertLayout.setPadding(new Insets(50));
		alertLayout.setAlignment(Pos.CENTER);
		
		alertLayout.getChildren().addAll(alert, closeBtn);
		Scene scene = new Scene(alertLayout);
		// Sets scene as alertScene
		alertStage.setScene(scene);
		// Shoes stage and waits to be closed
		alertStage.showAndWait();
		
	}
}
