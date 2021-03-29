package control;

import comm.Receptor.OnMessageListener;
import comm.TCPConnection;
import comm.TCPConnection.OnConnectionListener;
import model.Game;
import model.Game.EndGameListener;

public class DealerController implements OnMessageListener, OnConnectionListener, EndGameListener{
	
	private TCPConnection connection;
	private Game game;
	public String player1ID;
	public String player2ID;
	
	public DealerController() {}
	
	public void init() {
		game = new Game();
		game.setEndGameListener(this);
		connection = TCPConnection.getInstance();
		connection.setPuerto(5000);
		connection.setConnectionListener(this);
		connection.setMessageListener(this);
		connection.start();
	}
	
	
	@Override
	public void onConnection(String id) {
		if(player1ID==null)player1ID=id;
		else if(player2ID==null)player2ID=id;
		System.out.println("Se ha conectado un jugador con la ID: " + id);
	}

	@Override
	public void receiveTurnAction(String turnAction) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receivePlayersDeckOfCards(String playersDeckOfCards) {
		// TODO Auto-generated method stub
	}

	@Override
	public void endGame() {
		
	}
}
