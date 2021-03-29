package model;

public class TurnAction {

	public static final String TYPE_CLASS = "TurnAction";
	public String type = TYPE_CLASS;
	
	public static final String STAND = "Stand";
	public static final String TAKE_CARD = "Take card";
	
	private String action;
	
	public TurnAction() {}
	
	public TurnAction(String action) {
		this.action=action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
