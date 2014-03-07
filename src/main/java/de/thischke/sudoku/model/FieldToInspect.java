package de.thischke.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class FieldToInspect {
	
	private Field field;
	private int x;
	private int y;
	private Boolean self = Boolean.FALSE;
	private List<Integer> possibilities = new ArrayList<Integer>();
	
	public List<Integer> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(List<Integer> possibilities) {
		this.possibilities = possibilities;
	}

	public Field getField() {
		return field;
	}
	
	public void setField(Field field) {
		this.field = field;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Boolean getSelf() {
		return self;
	}
	
	public void setSelf(Boolean self) {
		this.self = self;
	}
}
