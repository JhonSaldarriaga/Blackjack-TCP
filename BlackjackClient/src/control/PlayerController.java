package control;

import comm.TCPConnection;
import comm.Receptor.OnMessageListener;
import comm.TCPConnection.OnConnectionListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.PlayerWindow;

public class PlayerController implements OnMessageListener, OnConnectionListener{
	
	private PlayerWindow view;
	private TCPConnection connection;
	
	public PlayerController(PlayerWindow view) {
		this.view = view;
		init();
	}
	
	public void init() {
		
	}

	@Override
	public void onConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveCard(String card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveStatus(String status) {
		// TODO Auto-generated method stub
		
	}
}
