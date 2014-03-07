package de.thischke.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

	private Integer value = null;
	private List<Integer> possibilities = new ArrayList<Integer>();

	public Field() {
		for (int i = 1; i < 10; i++) {
			possibilities.add(i);
		}
	}

	public Integer getValue() {
		return value;
	}

	public String getValueAsString() {
		String result;
		if (value == null) {
			result = "-";
		} else {
			result = String.valueOf(value);
		}
		return result;
	}
	
	public void setValue(Integer value) {
		this.value = value;
		clearPossibilities();
	}

	public List<Integer> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(List<Integer> possibilities) {
		this.possibilities = possibilities;
	}

	public void clearPossibility(Integer i) {
		possibilities.remove(i);
	}

	public void clearPossibilities() {
		possibilities.clear();
	}

	public boolean hasPossibility(Integer value) {
		return possibilities.contains(value);
	}
}
