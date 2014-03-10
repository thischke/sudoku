package de.thischke.sudoku.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import de.thischke.sudoku.model.Board;
import de.thischke.sudoku.model.Field;
import de.thischke.sudoku.strategy.ClearPossibilites;
import de.thischke.sudoku.strategy.Strategy;

@SuppressWarnings("restriction")
@ManagedBean(name = "board")
@SessionScoped
public class BoardBean implements Serializable {

	private static final long serialVersionUID = 134569483576947560L;

	private String title;
	private Board board;
	private List<ArrayList<Field>> fields;
	
	private static List<Strategy> strategies;

	@PostConstruct
	public void init() {
		this.board = new Board();
		this.title = "Sudoku";
		
		fields = new ArrayList<ArrayList<Field>>();
		for (int y = 0; y < 9; y++) {
			ArrayList<Field> columns = new ArrayList<Field>();
			for (int x = 0; x < 9; x++) {
				columns.add(this.board.getField(y, x));
			}
			this.fields.add(columns);
		}
	}
	
	@Autowired
	public void setStrategies(List<Strategy> strategies) {
		BoardBean.strategies = strategies;
	}

	public List<ArrayList<Field>> getFields() {
		return this.fields;
	}

	public String getTitle() {
		return this.title;
	}

	public void updateField(ActionEvent actionEvent) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		Integer x = Integer.valueOf(params.get("x"));
		Integer y = Integer.valueOf(params.get("y"));
		Integer wert = Integer.valueOf(params.get("wert"));
		board.setFieldValue(x, y, wert);

		Strategy strategy = new ClearPossibilites();
		strategy.resolver(board);
	}

	public void resolve(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("", "resolve board"));
		
//		for(Strategy strategy: strategies) {
//			context.addMessage(null, new FacesMessage("", "found strategy: " + strategy.getName()));
//		}
		
		Boolean found = Boolean.TRUE;
		while (found) {
			found = Boolean.FALSE;
			
			for(Strategy strategy: strategies) {
				if(strategy.resolver(board)) {
					found = Boolean.TRUE;
				}
			}
		}
		
		displayBoard();
		
		context.addMessage(null, new FacesMessage("", "found strategy ... ended " ));
	}

	private void displayBoard() {
		RequestContext currentInstance = RequestContext.getCurrentInstance();
		for (int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				currentInstance.update("form:field_" + x + "_" + y);
			}
		}
	}
}
