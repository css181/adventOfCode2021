package day4;


import org.junit.jupiter.api.Test;


public class BingoPart1Test {

	Bingo bingo = new Bingo();
	
	
	@Test
	void do_all_balls_until_someone_wins_test() throws Exception {
		do {
			bingo.callABall();
		}while(!bingo.isWinner && Bingo.curBallCallIndex<90);
		System.out.println("Needed to call [" + (Bingo.curBallCallIndex) + "] balls.");
		System.out.println("Total of all UNcalled on board 49: " + bingo.boards.get(49).getSumOfAll_UNCalledNumbers());
		bingo.printAllBoards();
	}
	
	@Test
	void get_last_winner_card() throws Exception {
		do {
			bingo.callABall();
		}while(bingo.getBoardIndexesThatHaveNotWonYet().size()!=0 && Bingo.curBallCallIndex<98);
		System.out.println("Needed to call [" + (Bingo.curBallCallIndex) + "] balls.");
		System.out.println("Last ball called was: " + bingo.ballCalls.get(Bingo.curBallCallIndex-1));
		System.out.println("Last board to win was {" + bingo.indexOfLastWinningBoard + "}");
		bingo.printAllBoards();
		System.out.println("Boards that have still not won:");
		System.out.println(bingo.getBoardIndexesThatHaveNotWonYet());
		System.out.println("Total of all UNcalled on board 30: " + bingo.boards.get(30).getSumOfAll_UNCalledNumbers());
	}
}
