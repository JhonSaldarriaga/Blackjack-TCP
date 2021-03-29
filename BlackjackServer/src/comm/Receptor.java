package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import model.Generic;
import model.PlayersDeckOfCards;
import model.TurnAction;

public class Receptor extends Thread{
	
	
	private InputStream is; 
	public OnMessageListener messageListener;
	
	
	public Receptor(InputStream is) {
		this.is = is;
	}
	
	@Override
	public void run() {
		try {
		
			BufferedReader breader = new BufferedReader(new InputStreamReader(this.is));
			
			while(true) {	
				String msg = breader.readLine();
				Gson gson = new Gson();
				Generic g = gson.fromJson(msg, Generic.class);
				
				switch(g.type) {
				case TurnAction.TYPE_CLASS:
					messageListener.receiveTurnAction(msg);
					break;
				case PlayersDeckOfCards.TYPE_CLASS:
					messageListener.receivePlayersDeckOfCards(msg);
					break;
				default:System.out.println("Se envió una clase que no se debería enviar o hubo un error con gson");
				};
			}
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void setListener(OnMessageListener messageListener) {
		this.messageListener = messageListener;
	}
	
	
	public interface OnMessageListener{
		public void receiveTurnAction(String turnAction); 
		public void receivePlayersDeckOfCards(String playersDeckOfCards);
	}
	

}
