package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import model.Card;
import model.Generic;
import model.Status;

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
				case Card.TYPE_CLASS:
					messageListener.receiveCard(msg);
					break;
				case Status.TYPE_CLASS:
					messageListener.receiveStatus(msg);
					break;
				default:System.out.println("Se envi� una clase que no se deber�a enviar o hubo un error con gson");
				}
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
		public void receiveCard(String card); 
		public void receiveStatus(String status);
	}

}
