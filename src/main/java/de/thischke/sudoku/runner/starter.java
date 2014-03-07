package de.thischke.sudoku.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.thischke.sudoku.model.Board;
import de.thischke.sudoku.strategy.Strategy;

public class starter {

	private static List<Strategy> strategies;

	public static void main(String[] args) {
		
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		
		Board board = new Board();
		
		board.setFieldValue(0,0,4);
		board.setFieldValue(0,1,7);
		board.setFieldValue(1,0,6);
		board.setFieldValue(1,1,2);
		board.setFieldValue(1,2,3);

		board.setFieldValue(0,5,6);
		board.setFieldValue(1,3,8);
		board.setFieldValue(1,4,5);
		board.setFieldValue(1,5,1);
		board.setFieldValue(2,3,4);

		board.setFieldValue(0,7,8);
		board.setFieldValue(1,8,7);
		board.setFieldValue(2,6,1);
		board.setFieldValue(2,8,1);

		board.setFieldValue(3,1,6);
		board.setFieldValue(3,2,2);
		board.setFieldValue(4,2,8);
		board.setFieldValue(5,0,5);

		board.setFieldValue(3,4,9);
		board.setFieldValue(3,5,7);
		board.setFieldValue(4,4,4);
		board.setFieldValue(5,3,1);
		board.setFieldValue(5,5,8);

		board.setFieldValue(4,7,9);
		board.setFieldValue(4,8,3);
		board.setFieldValue(5,6,7);
		board.setFieldValue(5,7,2);
		board.setFieldValue(5,8,6);

		board.setFieldValue(6,1,8);
		board.setFieldValue(7,2,7);
		board.setFieldValue(8,0,2);
		board.setFieldValue(8,1,3);

		board.setFieldValue(6,4,6);
		board.setFieldValue(7,3,2);
		board.setFieldValue(7,5,5);
		board.setFieldValue(8,4,8);

		board.setFieldValue(6,7,7);
		board.setFieldValue(6,8,2);
		board.setFieldValue(7,6,6);
		board.setFieldValue(7,7,3);
		board.setFieldValue(8,6,1);
		board.setFieldValue(8,8,9);
		board.display();

		
		for(Strategy strategy: strategies) {
			System.out.println("found strategy: " + strategy.getName());
		}
		
		Boolean found = Boolean.TRUE;
		while (found) {
			found = Boolean.FALSE;
			
			for(Strategy strategy: strategies) {
				if(strategy.resolver(board)) {
					found = Boolean.TRUE;
				}
			}
		}
		
		board.display();
	}
	

	@Autowired
	public void setStrategies(List<Strategy> strategies) {
		starter.strategies = strategies;
	}
}
