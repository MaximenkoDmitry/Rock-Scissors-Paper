public class Winner {
	private final int indexMoveOfUser;
	private final int indexMoveOfComputer;
	private final int movesLength;

	public Winner(int indexMoveOfComputer, int indexMoveOfUser, int movesLength) {
		this.indexMoveOfUser = indexMoveOfUser;
		this.indexMoveOfComputer = indexMoveOfComputer;
		this.movesLength = movesLength;
	}

	public String findWinner(){
		int halfSize = movesLength/2;
		if(indexMoveOfUser == indexMoveOfComputer) return "DRAW";
		else{
			if(indexMoveOfUser < indexMoveOfComputer){
				if((indexMoveOfComputer-indexMoveOfUser)>halfSize) return "USER WIN";
				else return "PC WIN";
			}
			else{
				if((indexMoveOfUser-indexMoveOfComputer)>halfSize) return "PC WIN";
				else return "USER WIN";
			}
		}
	}
}
