package control;

import com.google.gson.Gson;

import comm.Receptor.OnMessageListener;
import comm.TCPConnection;
import comm.TCPConnection.OnConnectionListener;
import model.DealersDeckOfCards;
import model.Game;
import model.PlayersDeckOfCards;
import model.Game.EndGameListener;
import model.Game.ResetGameListener;
import model.Status;
import model.PlayerAction;

public class DealerController implements OnMessageListener, OnConnectionListener, EndGameListener, ResetGameListener{
	
	private TCPConnection connection;
	private Game game;
	private DealersDeckOfCards deckOfCards;
	public String player1ID = null;
	public String player2ID = null;
	
	public DealerController() {}
	
	public void init() {
		deckOfCards = DealersDeckOfCards.getInstance();
		connection = TCPConnection.getInstance();
		connection.setPuerto(5000);
		connection.setConnectionListener(this);
		connection.setMessageListener(this);
		connection.start();
	}
	
	private void startGame() {
		game = new Game();
		game.setEndGameListener(this);
		game.setResetGameListener(this);
		Gson gson = new Gson();
		connection.sendDirectMessage(player1ID, gson.toJson(deckOfCards.generateRandomCard()));
		connection.sendDirectMessage(player1ID, gson.toJson(deckOfCards.generateRandomCard()));
		connection.sendDirectMessage(player2ID, gson.toJson(deckOfCards.generateRandomCard()));
		connection.sendDirectMessage(player2ID, gson.toJson(deckOfCards.generateRandomCard()));
		connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.YOUR_TURN)));
		connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.OPPONENT_TURN)));
	}
	
	@Override
	public void onConnection(String id) {
		if(player1ID==null)player1ID=id;
		else if(player2ID==null) {
			player2ID=id;
			startGame();
		}
		System.out.println("Se ha conectado un jugador con la ID: " + id);
	}

	@Override
	public void receivePlayerAction(String playerAction, String id) {
		Gson gson = new Gson();
		if(id.equals(player1ID)) {//PLAYER ONE
			PlayerAction t = gson.fromJson(playerAction, PlayerAction.class);
			//SWITCH SENTENCE
			switch(t.getAction()) {
			//CASE STAND
			case PlayerAction.STAND:
				connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.STAND)));
				if(game.getPlayerTwo()==null)connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.YOUR_TURN)));
				break;
			//CASE TAKE CARD
			case PlayerAction.TAKE_CARD:
				connection.sendDirectMessage(player1ID, gson.toJson(deckOfCards.generateRandomCard()));
				connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.OPPONENT_TURN)));
				if(game.getPlayerTwo()!=null) {
					new Thread(
							()->{
								try {
									Thread.sleep(3000);
									if(game.getPlayerOne()==null)
										connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.YOUR_TURN)));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
					).start();
				}else connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.YOUR_TURN)));
				break;
			//CASE PLAY AGAIN	
			case PlayerAction.PLAY_AGAIN:
				connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.WAIT_PLAYAGAIN)));
				game.playerOnePlayAgain();
				break;
			//DEFAULT
			default: System.out.println("Se ha enviado una accion de turno invalida.");
			}
		}else if(id.equals(player2ID)) {//PLAYER TWO
			PlayerAction t = gson.fromJson(playerAction, PlayerAction.class);
			//SWITCH SENTENCE
			switch(t.getAction()) {
			//CASE STAND
			case PlayerAction.STAND:
				connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.STAND)));
				if(game.getPlayerOne()==null)connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.YOUR_TURN)));
				break;
			//CASE TAKE CARD
			case PlayerAction.TAKE_CARD:
				connection.sendDirectMessage(player2ID, gson.toJson(deckOfCards.generateRandomCard()));
				connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.OPPONENT_TURN)));
				if(game.getPlayerOne()!=null) {
					new Thread(
							()->{
								try {
									Thread.sleep(3000);
									if(game.getPlayerTwo()==null)
										connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.YOUR_TURN)));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
					).start();
				}else connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.YOUR_TURN)));
				break;
			//CASE PLAY AGAIN
			case PlayerAction.PLAY_AGAIN:
				connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.WAIT_PLAYAGAIN)));
				game.playerTwoPlayAgain();
				break;
			//DEFAULT
			default: System.out.println("Se ha enviado una accion de turno invalida.");
			}
		}
	}

	@Override
	public void receivePlayersDeckOfCards(String playersDeckOfCards, String id) {
		Gson gson = new Gson();
		PlayersDeckOfCards pdoc = gson.fromJson(playersDeckOfCards, PlayersDeckOfCards.class);
		if(id.equals(player1ID)) game.setPlayerOne(pdoc);
		else if(id.equals(player2ID)) game.setPlayerTwo(pdoc);
	}

	@Override
	public void endGame() {
		Gson gson = new Gson();
		if(game.getWinner()==1) {
			connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.WINNER, game.getPlayerOne().countValue(), game.getPlayerTwo().countValue())));
			connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.LOSER, game.getPlayerTwo().countValue(), game.getPlayerOne().countValue())));
		}else if(game.getWinner()==2) {
			connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.WINNER, game.getPlayerTwo().countValue(), game.getPlayerOne().countValue())));
			connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.LOSER, game.getPlayerOne().countValue(), game.getPlayerTwo().countValue())));
		}else if(game.getWinner()==-1) {
			connection.sendDirectMessage(player2ID, gson.toJson(new Status(Status.TIE, game.getPlayerTwo().countValue(), game.getPlayerOne().countValue())));
			connection.sendDirectMessage(player1ID, gson.toJson(new Status(Status.TIE, game.getPlayerOne().countValue(), game.getPlayerTwo().countValue())));
		}
	}

	@Override
	public void resetGame() {
		startGame();
	}
}
