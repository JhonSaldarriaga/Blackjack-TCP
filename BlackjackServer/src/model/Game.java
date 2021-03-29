package model;

public class Game {

	private EndGameListener endGameListener;
	private final int BLACK_JACK=21;
	private PlayersDeckOfCards playerOne;
	private PlayersDeckOfCards playerTwo;
	
	public void setPlayerOne(PlayersDeckOfCards playerOne) {
		this.playerOne = playerOne;
		if(playerTwo!=null)endGameListener.endGame();
	}

	public void setPlayerTwo(PlayersDeckOfCards playerTwo) {
		this.playerTwo = playerTwo;
		if(playerOne!=null)endGameListener.endGame();
	}
	
	public PlayersDeckOfCards getPlayerOne() {
		return playerOne;
	}
	
	public PlayersDeckOfCards getPlayerTwo() {
		return playerTwo;
	}

	public int getWinner() {
		int valuePlayerOne = playerOne.countValue();
		int valuePlayerTwo = playerTwo.countValue();
		if((valuePlayerOne==valuePlayerTwo) || (valuePlayerOne>BLACK_JACK && valuePlayerTwo>BLACK_JACK)) {
			return -1;//TIE
		}else if(valuePlayerOne<=BLACK_JACK && valuePlayerTwo<=BLACK_JACK) {
			return (valuePlayerOne>valuePlayerTwo)?1:2;
		}else if(valuePlayerOne>BLACK_JACK)
			return 2;
		else
			return 1;
	}
	
	public void setEndGameListener(EndGameListener endGameListener) {
		this.endGameListener = endGameListener;
	}

	public interface EndGameListener{
		public void endGame();
	}
}
