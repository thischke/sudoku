package de.thischke.sudoku.strategy;

import java.util.EnumSet;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import de.thischke.sudoku.model.Board;
import de.thischke.sudoku.model.BoardInspectStrategy;

public class HiddenSingleTest {

	@Test
	public void resolveRowTest() {
		Board board = new Board();
		
		board.setFieldValue(1,3,1);
		board.setFieldValue(2,6,1);
		board.setFieldValue(3,1,1);
		board.setFieldValue(4,4,1);
		board.setFieldValue(5,7,1);
		board.setFieldValue(6,2,1);
		board.setFieldValue(7,5,1);
		board.setFieldValue(8,8,1);

		ClearPossibilites clearPossibilities = new ClearPossibilites();
		clearPossibilities.resolver(board);

		HiddenSingle hiddenSingle = new HiddenSingle();
		hiddenSingle.setBoardInspect(EnumSet.of(BoardInspectStrategy.ROW));
		assertThat(hiddenSingle.resolver(board), is(Boolean.TRUE));
		assertThat(board.getValue(0, 0), is(1));
		
		assertThat(clearPossibilities.resolver(board), is(Boolean.FALSE));
		assertThat(hiddenSingle.resolver(board), is(Boolean.FALSE));
	}
	
	@Test
	public void resolveColumnTest() {
		Board board = new Board();
		
		board.setFieldValue(1,3,1);
		board.setFieldValue(2,6,1);
		board.setFieldValue(3,1,1);
		board.setFieldValue(4,4,1);
		board.setFieldValue(5,7,1);
		board.setFieldValue(6,2,1);
		board.setFieldValue(7,5,1);
		board.setFieldValue(8,8,1);

		ClearPossibilites clearPossibilities = new ClearPossibilites();
		clearPossibilities.resolver(board);

		HiddenSingle hiddenSingle = new HiddenSingle();
		hiddenSingle.setBoardInspect(EnumSet.of(BoardInspectStrategy.COLUMN));
		assertThat(hiddenSingle.resolver(board), is(Boolean.TRUE));
		assertThat(board.getValue(0, 0), is(1));
		
		assertThat(clearPossibilities.resolver(board), is(Boolean.FALSE));
		assertThat(hiddenSingle.resolver(board), is(Boolean.FALSE));
	}
	
	@Test
	public void resolveSquareTest() {
		Board board = new Board();
		
		board.setFieldValue(1,3,1);
		board.setFieldValue(2,6,1);
		board.setFieldValue(3,1,1);
		board.setFieldValue(4,4,1);
		board.setFieldValue(5,7,1);
		board.setFieldValue(6,2,1);
		board.setFieldValue(7,5,1);
		board.setFieldValue(8,8,1);

		ClearPossibilites clearPossibilities = new ClearPossibilites();
		clearPossibilities.resolver(board);

		HiddenSingle hiddenSingle = new HiddenSingle();
		hiddenSingle.setBoardInspect(EnumSet.of(BoardInspectStrategy.SQUARE));
		assertThat(hiddenSingle.resolver(board), is(Boolean.TRUE));
		assertThat(board.getValue(0, 0), is(1));
		
		assertThat(clearPossibilities.resolver(board), is(Boolean.FALSE));
		assertThat(hiddenSingle.resolver(board), is(Boolean.FALSE));
	}
}
