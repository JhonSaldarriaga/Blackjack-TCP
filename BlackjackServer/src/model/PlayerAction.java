package model;

public class PlayerAction {

	public static final String TYPE_CLASS = "PlayerAction";
	public String type = TYPE_CLASS;
	
	public static final String STAND = "Stand";
	public static final String TAKE_CARD = "Take card";
	public static final String PLAY_AGAIN = "Play again";//NEW
	
	private String action;
	
	public PlayerAction() {}
	
	public PlayerAction(String action) {
		this.action=action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
