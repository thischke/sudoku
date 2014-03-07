package de.thischke.sudoku.strategy;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.thischke.sudoku.model.Board;
import de.thischke.sudoku.model.BoardInspectStrategy;
import de.thischke.sudoku.model.FieldToInspect;

@Component
@Order(value = 3)
public class HiddenSingle implements Strategy {

	private static final String STRATEGY_NAME = "HiddenSingle";

	private Set<BoardInspectStrategy> boardInspect = EnumSet.of(
			BoardInspectStrategy.ROW, BoardInspectStrategy.COLUMN,
			BoardInspectStrategy.SQUARE);
	private List<FieldToInspect> fieldsToInspect;

	public Boolean resolver(Board board) {
		Boolean found = Boolean.FALSE;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board.getValue(i, j) == null) {

					if (boardInspect.contains(BoardInspectStrategy.ROW)) {
						fieldsToInspect = board.getFieldsToInspect(i, j, BoardInspectStrategy.ROW);
						if (findHiddenSingle(i, j, board, fieldsToInspect)) {
							found = Boolean.TRUE;
						}
						break;
					}

					if (boardInspect.contains(BoardInspectStrategy.COLUMN)) {
						fieldsToInspect = board.getFieldsToInspect(i, j, BoardInspectStrategy.COLUMN);
						if (findHiddenSingle(i, j, board, fieldsToInspect)) {
							found = Boolean.TRUE;
						}
						break;
					}

					if (boardInspect.contains(BoardInspectStrategy.SQUARE)) {
						fieldsToInspect = board.getFieldsToInspect(i, j, BoardInspectStrategy.SQUARE);
						if (findHiddenSingle(i, j, board, fieldsToInspect)) {
							found = Boolean.TRUE;
						}
						break;
					}
				}
			}
		}

		return found;
	}

	private boolean findHiddenSingle(int i, int j, Board board,
			List<FieldToInspect> fieldsToInspect) {
		Boolean found = Boolean.FALSE;

		for (int wert = 1; wert < 10; wert++) {
			if (board.hasPossibility(i, j, wert)) {
				Boolean anotherPossibilty = Boolean.FALSE;
				
				for(FieldToInspect fieldToInspect: fieldsToInspect) {
					if (fieldToInspect.getSelf()) {
						continue;
					}

					if (board.hasPossibility(fieldToInspect.getX(), fieldToInspect.getY(), wert)) {
						anotherPossibilty = Boolean.TRUE;
					}
				}
				
				if (!anotherPossibilty) {
					found = Boolean.TRUE;
					anotherPossibilty = Boolean.FALSE;
					board.setFieldValue(i, j, wert);
				}
			}
		}

		return found;
	}

	public void setBoardInspect(Set<BoardInspectStrategy> boardInspect) {
		this.boardInspect = boardInspect;
	}

	public String getName() {
		return STRATEGY_NAME;
	}

}
