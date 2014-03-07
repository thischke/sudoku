package de.thischke.sudoku.strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.thischke.sudoku.model.Board;

@Component
@Order(value=2)
public class NakedSingle implements Strategy {

	private static final String STRATEGY_NAME = "NakedSingle";

	public Boolean resolver(Board board) {
		Boolean found = Boolean.FALSE;

		board.clearPossibility(0, 0, 1);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				
				if (board.getFieldPossibilites(i, j).size() == 1) {
					int value = board.getFieldPossibilites(i, j).get(0);
					
					found = Boolean.TRUE;
					board.setFieldValue(i, j, value);
				}
			}
		}

		return found;
	}

	public String getName() {
		return STRATEGY_NAME;
	}

}
