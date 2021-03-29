package view;

import java.io.IOException;

import control.PlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerWindow extends Stage {

	// UI Elements
	private Scene scene;
	private PlayerController control;

	public PlayerWindow() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerWindow.fxml"));
			Parent parent = loader.load();

			scene = new Scene(parent, 477, 422);
			this.setScene(scene);

			control = new PlayerController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
