package de.thischke.sudoku.strategy;

import de.thischke.sudoku.model.Board;

public interface Strategy {

	public String getName();
	public Boolean resolver(Board board);
}
