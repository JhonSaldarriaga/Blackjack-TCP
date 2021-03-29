package control;

import comm.Receptor.OnMessageListener;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;

import comm.TCPConnection;
import comm.TCPConnection.OnConnectionListener;
import javafx.application.Platform;
import view.DealerWindow;

public class DealerController implements OnMessageListener, OnConnectionListener{
	
	private DealerWindow view;
	private TCPConnection connection;
	
	public DealerController(DealerWindow view) {
		this.view = view;
		init();
	}
	
	public void init() {
		connection = TCPConnection.getInstance();
		connection.setPuerto(5000);
		connection.start();
		connection.setConnectionListener(this);
		connection.setMessageListener(this);
	}
	
	
	@Override
	public void onConnection(String id) {
			Platform.runLater(	
				()->{
					
				}
				
			);
		
	}

	@Override
	public void OnMessage(String msg) {
		
		
	}

	

}
