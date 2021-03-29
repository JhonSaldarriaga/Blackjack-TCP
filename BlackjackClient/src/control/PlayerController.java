package control;

import comm.TCPConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.PlayerWindow;

public class PlayerController{
	
	private PlayerWindow view;
	private TCPConnection connection;
	
	public PlayerController(PlayerWindow view) {
		this.view = view;
		init();
	}
	
	public void init() {
		
	}

	

}
