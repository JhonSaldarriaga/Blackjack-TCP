package control;

import com.google.gson.Gson;

import comm.Receptor.OnMessageListener;
import comm.TCPConnection;
import comm.TCPConnection.OnConnectionListener;
import javafx.application.Platform;
import model.Card;
import model.PlayerAction;
import model.PlayersDeckOfCards;
import model.Status;
import view.PlayerWindow;

public class PlayerController implements OnMessageListener, OnConnectionListener{
	
	private PlayerWindow view;
	private TCPConnection connection;
	private PlayersDeckOfCards game;
	
	public PlayerController(PlayerWindow view) {
		this.view = view;
		init();
	}
	
	public void init() {
		game = new PlayersDeckOfCards();
		connection = TCPConnection.getInstance();
		connection.setPuerto(5000);
		connection.setIp("127.0.0.1");
		connection.setConnectionListener(this);
		connection.setListenerOfMessages(this);
		
		view.getTakeCard().setOnAction(
				event ->{
					view.disableButtons(true);
					Gson gson = new Gson();
					String json = gson.toJson(new PlayerAction(PlayerAction.TAKE_CARD));
					connection.getEmisor().sendMessage(json);
				}
		);
		
		view.getStand().setOnAction(
				event ->{
					view.disableButtons(true);
					Gson gson = new Gson();
					String json = gson.toJson(new PlayerAction(PlayerAction.STAND));
					connection.getEmisor().sendMessage(json);
				}
		);
		
		connection.start();
	}

	public void cardSpaceFull() {
		view.disableButtons(true);
		Gson gson = new Gson();
		String json = gson.toJson(new PlayerAction(PlayerAction.STAND));
		connection.getEmisor().sendMessage(json);
	}
	
	@Override
	public void onConnection() {
		System.out.println("Me encuentro conectado en el servidor.");
	}

	@Override
	public void receiveCard(String card) {
		Gson gson = new Gson();
		Card c = gson.fromJson(card, Card.class);
		game.addCard(c);
		Platform.runLater(	
				()->{
					view.setCard(c);
				}
		);
		if(game.amountCards()==PlayersDeckOfCards.MAX_CARDS)cardSpaceFull();
	}

	@Override
	public void receiveStatus(String status) {
		Gson gson = new Gson();
		Status s = gson.fromJson(status, Status.class);
		switch(s.getMsg()) {
		case Status.STAND:
			Platform.runLater(	
					()->{
						view.setStatus(Status.STAND, true);
					}
			);
			String json = gson.toJson(game);
			connection.getEmisor().sendMessage(json);
			break;
		case Status.OPPONENT_TURN:
			Platform.runLater(	
					()->{
						view.setStatus(Status.OPPONENT_TURN, true);
					}
			);
			break;
		case Status.YOUR_TURN:
			Platform.runLater(	
					()->{
						view.setStatus(Status.YOUR_TURN, true);
						view.disableButtons(false);
					}
			);
			break;
		case Status.WINNER:
			Platform.runLater(	
					()->{
						view.setStatus(Status.WINNER, true);
						view.setStatus(Status.OWN_SCORE + s.getOwnScore() + " \\ " 
						+ Status.OPPONENT_SCORE + s.getOpponentScore(), false);
					}
			);
			break;
		case Status.LOSER:
			Platform.runLater(	
					()->{
						view.setStatus(Status.LOSER, true);
						view.setStatus(Status.OWN_SCORE + s.getOwnScore() + " \\ " 
						+ Status.OPPONENT_SCORE + s.getOpponentScore(), false);
					}
			);
			break;
		case Status.TIE:
			Platform.runLater(	
					()->{
						view.setStatus(Status.TIE, true);
						view.setStatus(Status.OWN_SCORE + s.getOwnScore() + " \\ " 
						+ Status.OPPONENT_SCORE + s.getOpponentScore(), false);
					}
			);
			break;
		default:
		}
	}
}
