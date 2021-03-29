package view;

import java.io.IOException;

import control.PlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Card;

public class PlayerWindow extends Stage {

	// UI Elements
	private Scene scene;
	private PlayerController control;
	
	private Label[] cards;
	private Label status;
	private Label status2;
	private Button stand;
	private Button takeCard;
	

	public PlayerWindow() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerWindow.fxml"));
			Parent parent = loader.load();
			scene = new Scene(parent, 477, 422);
			this.setScene(scene);

			takeCard = (Button) loader.getNamespace().get("takeCard");
			stand = (Button) loader.getNamespace().get("stand");
			status = (Label) loader.getNamespace().get("status");
			status2 = (Label) loader.getNamespace().get("status2");
			cards = new Label[5];
			cards[0] = (Label) loader.getNamespace().get("card1");
			cards[1] = (Label) loader.getNamespace().get("card2");
			cards[2] = (Label) loader.getNamespace().get("card3");
			cards[3] = (Label) loader.getNamespace().get("card4");
			cards[4] = (Label) loader.getNamespace().get("card5");
			control = new PlayerController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Button getStand() {
		return stand;
	}
	
	public Button getTakeCard() {
		return takeCard;
	}
	
	public void disableButtons(boolean disable) {
		takeCard.setDisable(disable);
		stand.setDisable(disable);
	}
	
	public void setCard(Card card) {
		for(int i=0;i<cards.length;i++) {
			if(cards[i].getText()=="") {
				cards[i].setText(card.getLogo());
				break;
			}
		}
	}
	
	public void setStatus(String status, boolean n) {
		if(n)this.status.setText(status);
		else this.status2.setText(status);
	}
}
