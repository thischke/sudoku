package de.thischke.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private Field[][] board = new Field[9][9];

	public Board() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = new Field();
			}
		}
	}

	public void setFieldValue(int i, int j, int k) {
		board[i][j].setValue(k);
	}

	public Field getField(int i, int j) {
		return board[i][j];
	}

	public Integer getValue(int i, int j) {
		return board[i][j].getValue();
	}

	public void clearPossibility(int i, int j, Integer value) {
		board[i][j].clearPossibility(value);
	}

	public boolean hasPossibility(int i, int j, Integer value) {
		return board[i][j].hasPossibility(value);
	}

	public List<Integer> getFieldPossibilites(int i, int j) {
		return board[i][j].getPossibilities();
	}

	public void setFieldPossibilites(int i, int j, List<Integer> possibilities) {
		board[i][j].setPossibilities(possibilities);
	}

	public List<FieldToInspect> getFieldsToInspect(int x, int y, BoardInspectStrategy boardInspectStrategy) {
		List<FieldToInspect> fieldsToInspect = new ArrayList<FieldToInspect>();

		if (boardInspectStrategy == BoardInspectStrategy.ROW) {
			getRowFields(x, y, fieldsToInspect);
		}

		if (boardInspectStrategy == BoardInspectStrategy.COLUMN) {
			getColumnsFields(x, y, fieldsToInspect);
		}

		if (boardInspectStrategy == BoardInspectStrategy.SQUARE) {
			getSquareFields(x, y, fieldsToInspect);
		}

		return fieldsToInspect;
	}

	private void getRowFields(int x, int y, List<FieldToInspect> fieldsToInspect) {
		for (int i = 0; i < 9; i++) {
			FieldToInspect fieldToInspect = new FieldToInspectBuilder().
					withX(x).
					withY(i).
					withField(board[x][i]).
					withPossibilities(board[x][i].getPossibilities()).
					build();
					
			if (i == y) {
				fieldToInspect.setSelf(Boolean.TRUE);
			}

			fieldsToInspect.add(fieldToInspect);
		}
	}

	private void getColumnsFields(int x, int y, List<FieldToInspect> fieldsToInspect) {
		for (int i = 0; i < 9; i++) {
			FieldToInspect fieldToInspect = new FieldToInspectBuilder().
					withX(i).
					withY(y).
					withField(board[i][y]).
					withPossibilities(board[i][y].getPossibilities()).
					build();

			if (i == x) {
				fieldToInspect.setSelf(Boolean.TRUE);
			}

			fieldsToInspect.add(fieldToInspect);
		}

	}

	private void getSquareFields(int x, int y, List<FieldToInspect> fieldsToInspect) {

		Integer start_x = Integer.valueOf(x / 3);
		Integer start_y = Integer.valueOf(y / 3);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int pos_x = start_x * 3 + i;
				int pos_y = start_y * 3 + j;

				FieldToInspect fieldToInspect = new FieldToInspectBuilder().
						withX(pos_x).
						withY(pos_y).
						withField(board[pos_x][pos_y]).
						withPossibilities(board[pos_x][pos_y].getPossibilities()).
						build();

				if (x == pos_x && y == pos_y) {
					fieldToInspect.setSelf(Boolean.TRUE);
				}

				fieldsToInspect.add(fieldToInspect);
			}
		}
	}

	public Boolean isResolved() {
		Boolean resolved = Boolean.TRUE;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getValue() == null) {
					resolved = Boolean.FALSE;
				}
			}
		}

		return resolved;
	}

	public void display() {
		System.out.println("-------------------------------");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getValue() == null) {
					System.out.print(" - ");
				} else {
					System.out.print(" " + board[i][j].getValue() + " ");
				}

				if ((j % 3) == 2) {
					System.out.print("|");
				}
			}
			System.out.println();

			if ((i % 3) == 2) {
				System.out.println("-------------------------------");
			}
		}
	}

}
