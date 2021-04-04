package model;

public class Status {
	public static final String TYPE_CLASS = "Status";
	public String type = TYPE_CLASS;
	
	public static final String WAIT_OPPONNENT = "Waiting opponent...";//NEW
	public static final String YOUR_TURN = "It's your turn.";
	public static final String OPPONENT_TURN = "It's your opponent's turn.";
	public static final String STAND = "You stood up, waiting for the game to end.";
	public static final String WINNER = "You win the game!";
	public static final String LOSER = "You lose the game :(";
	public static final String TIE = "it was a tie!";
	public static final String OPPONENT_SCORE = "The opponent's score was: ";
	public static final String OWN_SCORE = "The own score was: ";
	public static final String WAIT_PLAYAGAIN = "Waiting for opponent's response";//NEW
	
	private String msg;
	private int ownScore;
	private int opponentScore;
	
	public Status() {}
	
	public Status(String msg) {
		this.msg=msg;
	}
	
	public Status(String msg, int ownScore, int opponentScore) {
		this.msg=msg;
		this.ownScore=ownScore;
		this.opponentScore=opponentScore;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getOwnScore() {
		return ownScore;
	}
	public void setOwnScore(int ownScore) {
		this.ownScore = ownScore;
	}
	public int getOpponentScore() {
		return opponentScore;
	}
	public void setOpponentScore(int opponentScore) {
		this.opponentScore = opponentScore;
	}
}
