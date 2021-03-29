package control;

import comm.Receptor.OnMessageListener;
import comm.TCPConnection;
import comm.TCPConnection.OnConnectionListener;
import javafx.application.Platform;

public class DealerController implements OnMessageListener, OnConnectionListener{
	
	private TCPConnection connection;
	
	public DealerController() {}
	
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
