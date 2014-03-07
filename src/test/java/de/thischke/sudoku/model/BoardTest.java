package de.thischke.sudoku.model;

import java.util.List;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import de.thischke.sudoku.strategy.ClearPossibilites;

public class BoardTest {

	@Test
	public void getFieldsToInspectRowTest() {
		Board board = new Board();
		
		board.setFieldValue(0,0,1);
		board.setFieldValue(0,2,2);
		board.setFieldValue(0,3,3);
		board.setFieldValue(0,6,4);

		ClearPossibilites clearPossibilites = new ClearPossibilites();
		clearPossibilites.resolver(board);
		
		List<FieldToInspect> getFieldsToInspect = board.getFieldsToInspect(0, 2, BoardInspectStrategy.ROW);
		
		assertThat(getFieldsToInspect.size(), is(9));
		assertThat(getFieldsToInspect.get(0).getPossibilities().size(), is(0));
		assertThat(getFieldsToInspect.get(0).getSelf(), is(Boolean.FALSE));
		assertThat(getFieldsToInspect.get(1).getPossibilities().size(), is(5));
		assertThat(getFieldsToInspect.get(1).getSelf(), is(Boolean.FALSE));
		assertThat(getFieldsToInspect.get(2).getSelf(), is(Boolean.TRUE));
	}
	
	@Test
	public void getFieldsToInspectColumnTest() {
		Board board = new Board();
		
		board.setFieldValue(0,1,1);
		board.setFieldValue(2,1,2);
		board.setFieldValue(4,1,3);
		
		ClearPossibilites clearPossibilites = new ClearPossibilites();
		clearPossibilites.resolver(board);
		
		List<FieldToInspect> getFieldsToInspect = board.getFieldsToInspect(0, 1, BoardInspectStrategy.COLUMN);
		
		assertThat(getFieldsToInspect.size(), is(9));
		assertThat(getFieldsToInspect.get(0).getPossibilities().size(), is(0));
		assertThat(getFieldsToInspect.get(0).getSelf(), is(Boolean.TRUE));
		assertThat(getFieldsToInspect.get(1).getPossibilities().size(), is(6));
		assertThat(getFieldsToInspect.get(1).getSelf(), is(Boolean.FALSE));
	}
	
	@Test
	public void getFieldsToInspectSquareTest() {
		Board board = new Board();
		
		board.setFieldValue(0,1,1);
		board.setFieldValue(1,0,2);
		board.setFieldValue(2,2,3);
		
		ClearPossibilites clearPossibilites = new ClearPossibilites();
		clearPossibilites.resolver(board);
		
		List<FieldToInspect> getFieldsToInspect = board.getFieldsToInspect(1, 1, BoardInspectStrategy.SQUARE);
		
		assertThat(getFieldsToInspect.size(), is(9));
		assertThat(getFieldsToInspect.get(0).getPossibilities().size(), is(6));
		assertThat(getFieldsToInspect.get(0).getSelf(), is(Boolean.FALSE));
		assertThat(getFieldsToInspect.get(1).getPossibilities().size(), is(0));
		assertThat(getFieldsToInspect.get(1).getSelf(), is(Boolean.FALSE));
		assertThat(getFieldsToInspect.get(4).getSelf(), is(Boolean.TRUE));
	}
	
}
