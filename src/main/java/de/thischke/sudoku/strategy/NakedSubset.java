package de.thischke.sudoku.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.thischke.sudoku.model.Board;
import de.thischke.sudoku.model.BoardInspectStrategy;
import de.thischke.sudoku.model.FieldToInspect;

@Component
@Order(value = 3)
public class NakedSubset implements Strategy {

	private static final String STRATEGY_NAME = "NakedSubset";

	Set<BoardInspectStrategy> boardInspect = EnumSet.of(BoardInspectStrategy.ROW, BoardInspectStrategy.COLUMN, BoardInspectStrategy.SQUARE);

	// TODO: find triple
	
	public Boolean resolver(Board board) {
		Boolean found = Boolean.FALSE;

		for (int i = 0; i < 9; i++) {

			if (boardInspect.contains(BoardInspectStrategy.ROW)) {
				List<FieldToInspect> fieldsToInspect = board.getFieldsToInspect(i, 0, BoardInspectStrategy.ROW);
				if (findNakedSubset(board, fieldsToInspect)) {
					found = Boolean.TRUE;
				}
			}

			if (boardInspect.contains(BoardInspectStrategy.COLUMN)) {
				List<FieldToInspect> fieldsToInspect = board.getFieldsToInspect(0, i, BoardInspectStrategy.COLUMN);
				if (findNakedSubset(board, fieldsToInspect)) {
					found = Boolean.TRUE;
				}
			}

			if (boardInspect.contains(BoardInspectStrategy.SQUARE)) {
				List<FieldToInspect> fieldsToInspect = board.getFieldsToInspect(0, i, BoardInspectStrategy.SQUARE);
				if (findNakedSubset(board, fieldsToInspect)) {
					found = Boolean.TRUE;
				}
			}

		}

		return found;
	}

	private boolean findNakedSubset(Board board, List<FieldToInspect> fieldsToInspect) {
		Boolean found = Boolean.FALSE;
		Map<String, List<FieldToInspect>> fieldWithTwoPossibilities = findFieldWithTwoPossibilities(board, fieldsToInspect);

		for (String key : fieldWithTwoPossibilities.keySet()) {
			if (fieldWithTwoPossibilities.get(key).size() == 2) {
				clearPossibilitiesInOtherFields(board, fieldsToInspect, fieldWithTwoPossibilities.get(key));
				found = Boolean.TRUE;
			}
		}

		return found;
	}

	private void clearPossibilitiesInOtherFields(Board board, List<FieldToInspect> fieldsToInspect, List<FieldToInspect> fieldWithTwoPossibilities) {
		FieldToInspect field_1 = fieldWithTwoPossibilities.get(0);
		FieldToInspect field_2 = fieldWithTwoPossibilities.get(1);

		Integer value_1 = field_1.getPossibilities().get(0);
		Integer value_2 = field_2.getPossibilities().get(1);

		Integer x_1 = field_1.getX();
		Integer y_1 = field_1.getY();
		Integer x_2 = field_2.getX();
		Integer y_2 = field_2.getY();

		for (FieldToInspect fieldToInspect : fieldsToInspect) {
			if (!((fieldToInspect.getX() == x_1 && fieldToInspect.getY() == y_1) || (fieldToInspect.getX() == x_2 && fieldToInspect.getY() == y_2))) {
				if (board.hasPossibility(fieldToInspect.getX(), fieldToInspect.getY(), value_1)) {
					board.clearPossibility(fieldToInspect.getX(), fieldToInspect.getY(), value_1);
				}
				if (board.hasPossibility(fieldToInspect.getX(), fieldToInspect.getY(), value_2)) {
					board.clearPossibility(fieldToInspect.getX(), fieldToInspect.getY(), value_2);
				}
			}
		}
	}

	private HashMap<String, List<FieldToInspect>> findFieldWithTwoPossibilities(Board board, List<FieldToInspect> fieldsToInspect) {
		
		HashMap<String, List<FieldToInspect>> fieldWithTwoPossibilities = new HashMap<String, List<FieldToInspect>>();
		
		for (FieldToInspect fieldToInspect : fieldsToInspect) {
			if (board.getValue(fieldToInspect.getX(), fieldToInspect.getY()) == null) {
				if (board.getFieldPossibilites(fieldToInspect.getX(), fieldToInspect.getY()).size() == 2) {
					List<Integer> fieldPossibilities = board.getFieldPossibilites(fieldToInspect.getX(), fieldToInspect.getY());
					Collections.sort(fieldPossibilities);
					String key = fieldPossibilities.get(0) + "_" + fieldPossibilities.get(1);
					
					List<FieldToInspect> listFieldToInspect;
					if (fieldWithTwoPossibilities.containsKey(key)) {
						listFieldToInspect = fieldWithTwoPossibilities.get(key);
					} else {
						listFieldToInspect = new ArrayList<FieldToInspect>();
					}
		
					listFieldToInspect.add(fieldToInspect);
					fieldWithTwoPossibilities.put(key, listFieldToInspect);
				}
			}
		}
		
		return fieldWithTwoPossibilities;
	}

	public void setBoardInspect(Set<BoardInspectStrategy> boardInspect) {
		this.boardInspect = boardInspect;
	}

	public String getName() {
		return STRATEGY_NAME;
	}

}
