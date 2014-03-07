package de.thischke.sudoku.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.nullValue;


import de.thischke.sudoku.model.Field;

public class FieldTest {

	@Test
	public void createFieldTest() {
		Field field = new Field();
	
		assertThat(field.getValue(), is(nullValue()));
		assertThat(field.getPossibilities().size(), is(9));
		
		assertThat(field.getPossibilities().get(0), is(1));
		assertThat(field.getPossibilities().get(1), is(2));
		assertThat(field.getPossibilities().get(2), is(3));
		assertThat(field.getPossibilities().get(3), is(4));
		assertThat(field.getPossibilities().get(4), is(5));
		assertThat(field.getPossibilities().get(5), is(6));
		assertThat(field.getPossibilities().get(6), is(7));
		assertThat(field.getPossibilities().get(7), is(8));
		assertThat(field.getPossibilities().get(8), is(9));
	}
	
	@Test
	public void setFieldTest() {
		Field field = new Field();
		field.setValue(4);
		
		assertThat(field.getValue(), is(4));
		assertThat(field.getPossibilities().size(), is(0));
	}
	
	@Test
	public void clearPossibilitiesTest() {
		Field field = new Field();
		field.clearPossibilities();
		
		assertThat(field.getPossibilities().size(), is(0));
	}
	
	@Test
	public void clearPossibility() {
		Field field = new Field();
		
		field.clearPossibility(3);
		assertThat(field.getPossibilities().size(), is(8));
		assertThat(field.getPossibilities().get(0), is(1));
		assertThat(field.getPossibilities().get(1), is(2));
		assertThat(field.getPossibilities().get(2), is(4));
		assertThat(field.getPossibilities().get(3), is(5));
		assertThat(field.getPossibilities().get(4), is(6));
		assertThat(field.getPossibilities().get(5), is(7));
		assertThat(field.getPossibilities().get(6), is(8));
		assertThat(field.getPossibilities().get(7), is(9));
		
		field.clearPossibility(5);
		assertThat(field.getPossibilities().size(), is(7));
		assertThat(field.getPossibilities().get(0), is(1));
		assertThat(field.getPossibilities().get(1), is(2));
		assertThat(field.getPossibilities().get(2), is(4));
		assertThat(field.getPossibilities().get(3), is(6));
		assertThat(field.getPossibilities().get(4), is(7));
		assertThat(field.getPossibilities().get(5), is(8));
		assertThat(field.getPossibilities().get(6), is(9));
	}
	
	@Test
	public void clearPossibilityWithNonExistingValue() {
		Field field = new Field();
		
		field.clearPossibility(3);
		assertThat(field.getPossibilities().size(), is(8));
		assertThat(field.getPossibilities().get(0), is(1));
		assertThat(field.getPossibilities().get(1), is(2));
		assertThat(field.getPossibilities().get(2), is(4));
		assertThat(field.getPossibilities().get(3), is(5));
		assertThat(field.getPossibilities().get(4), is(6));
		assertThat(field.getPossibilities().get(5), is(7));
		assertThat(field.getPossibilities().get(6), is(8));
		assertThat(field.getPossibilities().get(7), is(9));
		
		field.clearPossibility(3);
		assertThat(field.getPossibilities().size(), is(8));
		assertThat(field.getPossibilities().get(0), is(1));
		assertThat(field.getPossibilities().get(1), is(2));
		assertThat(field.getPossibilities().get(2), is(4));
		assertThat(field.getPossibilities().get(3), is(5));
		assertThat(field.getPossibilities().get(4), is(6));
		assertThat(field.getPossibilities().get(5), is(7));
		assertThat(field.getPossibilities().get(6), is(8));
		assertThat(field.getPossibilities().get(7), is(9));
	}
}
