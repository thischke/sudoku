package de.thischke.sudoku.strategy;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import de.thischke.sudoku.model.Board;

public class NakedSingleTest {

	@Test
	public void resolveTest() {
		Board board = new Board();
		
		board.setFieldValue(0,4,1);
		board.setFieldValue(1,4,2);
		board.setFieldValue(3,2,3);
		board.setFieldValue(3,3,4);
		board.setFieldValue(3,6,8);
		board.setFieldValue(4,3,5);
		board.setFieldValue(5,3,6);
		board.setFieldValue(6,4,7);

		ClearPossibilites clearPossibilities = new ClearPossibilites();
		NakedSingle nakedSingle = new NakedSingle();
		
		clearPossibilities.resolver(board);
		assertThat(board.getFieldPossibilites(3, 4).size(), is(1));

		assertThat(nakedSingle.resolver(board), is(Boolean.TRUE));
		assertThat(board.getValue(3, 4), is(9));
	}
}
