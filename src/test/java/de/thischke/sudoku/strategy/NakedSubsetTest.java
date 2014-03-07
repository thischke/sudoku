package de.thischke.sudoku.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import de.thischke.sudoku.model.Board;
import de.thischke.sudoku.model.BoardInspectStrategy;

public class NakedSubsetTest {

	@Test
	public void nakedSubsetRowTest() {
		Board board = new Board();
		
		board.setFieldPossibilites(0, 0, new ArrayList<Integer>(Arrays.asList(1, 3, 6, 9)));
		board.setFieldPossibilites(0, 1, new ArrayList<Integer>(Arrays.asList(1, 5)));
		board.setFieldValue(0, 2, 4);
		board.setFieldPossibilites(0, 3, new ArrayList<Integer>(Arrays.asList(3, 6, 9)));
		board.setFieldValue(0, 4, 8);
		board.setFieldValue(0, 5, 7);
		board.setFieldPossibilites(0, 6, new ArrayList<Integer>(Arrays.asList(1, 5)));
		board.setFieldPossibilites(0, 7, new ArrayList<Integer>(Arrays.asList(1, 6)));
		board.setFieldValue(0, 8, 2);
		
		NakedSubset nakedSubset = new NakedSubset();
		nakedSubset.setBoardInspect(EnumSet.of(BoardInspectStrategy.ROW));
		
		assertThat(nakedSubset.resolver(board), is(Boolean.TRUE));
		
		assertThat(board.getFieldPossibilites(0, 0).size(), is(3));
		assertThat(board.getFieldPossibilites(0, 0), is(Arrays.asList(3, 6, 9)));
		assertThat(board.getValue(0, 2), is(4));
		assertThat(board.getFieldPossibilites(0, 3), is(Arrays.asList(3, 6, 9)));
		assertThat(board.getFieldPossibilites(0, 6), is(Arrays.asList(1, 5)));
		assertThat(board.getFieldPossibilites(0, 7), is(Arrays.asList(6)));
	}
	
	@Test
	public void nakedSubsetColumnTest() {
		Board board = new Board();
		
		board.setFieldPossibilites(0, 1, new ArrayList<Integer>(Arrays.asList(1, 3, 6, 9)));
		board.setFieldPossibilites(1, 1, new ArrayList<Integer>(Arrays.asList(1, 5)));
		board.setFieldValue(2, 1, 4);
		board.setFieldPossibilites(3, 1, new ArrayList<Integer>(Arrays.asList(3, 6, 9)));
		board.setFieldValue(4, 1, 8);
		board.setFieldValue(5, 1, 7);
		board.setFieldPossibilites(6, 1, new ArrayList<Integer>(Arrays.asList(1, 5)));
		board.setFieldPossibilites(7, 1, new ArrayList<Integer>(Arrays.asList(1, 6)));
		board.setFieldValue(8, 1, 2);
		
		NakedSubset nakedSubset = new NakedSubset();
		nakedSubset.setBoardInspect(EnumSet.of(BoardInspectStrategy.COLUMN));
		
		assertThat(nakedSubset.resolver(board), is(Boolean.TRUE));
		
		assertThat(board.getFieldPossibilites(0, 1).size(), is(3));
		assertThat(board.getFieldPossibilites(0, 1), is(Arrays.asList(3, 6, 9)));
		assertThat(board.getValue(2, 1), is(4));
		assertThat(board.getFieldPossibilites(3, 1), is(Arrays.asList(3, 6, 9)));
		assertThat(board.getFieldPossibilites(6, 1), is(Arrays.asList(1, 5)));
		assertThat(board.getFieldPossibilites(7, 1), is(Arrays.asList(6)));
	}
	
	@Test
	public void nakedSubsetSquareTest() {
		Board board = new Board();
		
		board.setFieldPossibilites(0, 0, new ArrayList<Integer>(Arrays.asList(1, 3, 6, 9)));
		board.setFieldPossibilites(0, 1, new ArrayList<Integer>(Arrays.asList(1, 5)));
		board.setFieldValue(0, 2, 4);
		board.setFieldPossibilites(1, 0, new ArrayList<Integer>(Arrays.asList(3, 6, 9)));
		board.setFieldValue(1, 1, 8);
		board.setFieldValue(1, 2, 7);
		board.setFieldPossibilites(2, 0, new ArrayList<Integer>(Arrays.asList(1, 5)));
		board.setFieldPossibilites(2, 1, new ArrayList<Integer>(Arrays.asList(1, 6)));
		board.setFieldValue(2, 2, 2);
		
		NakedSubset nakedSubset = new NakedSubset();
		nakedSubset.setBoardInspect(EnumSet.of(BoardInspectStrategy.SQUARE));
		
		assertThat(nakedSubset.resolver(board), is(Boolean.TRUE));
		
		assertThat(board.getFieldPossibilites(0, 0).size(), is(3));
		assertThat(board.getFieldPossibilites(0, 0), is(Arrays.asList(3, 6, 9)));
		assertThat(board.getValue(0, 2), is(4));
		assertThat(board.getFieldPossibilites(1, 0), is(Arrays.asList(3, 6, 9)));
		assertThat(board.getFieldPossibilites(2, 0), is(Arrays.asList(1, 5)));
		assertThat(board.getFieldPossibilites(2, 1), is(Arrays.asList(6)));
	}
}
