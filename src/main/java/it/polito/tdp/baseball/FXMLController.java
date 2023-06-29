package it.polito.tdp.baseball;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.baseball.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnesse;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnDreamTeam;

    @FXML
    private Button btnGradoMassimo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtYear;

    @FXML
    void doCalcolaConnesse(ActionEvent event) {
    	
    	txtResult.appendText(Integer.toString(this.model.componentiConnesse()) + "\n");

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	String s = txtSalary.getText();
    	String a = txtYear.getText();
    	double salario;
    	int anno;
    	
    	if (s.equals("") || a.equals("")) {
    		txtResult.appendText("Perfavore, inserire un salario e un anno");
    		return; 
    		}
    		
    		try {
    			salario = Double.parseDouble(s);
    			anno = Integer.parseInt(a);
    		} catch (NumberFormatException e) {
    			e.printStackTrace();
        		return;
    		}
    		
    		if ( anno < 1871 || anno > 2019) {
    			txtResult.appendText("Perfavore, inserire un anno compreso "
    					+ "tra il 1871 e il 2019");
        		return; 
    	}
    		
    		this.model.creaGrafo(anno, salario);
    		btnGradoMassimo.setDisable(false);
    		btnConnesse.setDisable(false);
    		txtResult.appendText("Grafo creato!\n");
    		txtResult.appendText("#VETICI: " + this.model.numeroVertici() + "\n");
    		txtResult.appendText("#ARCHI: " + this.model.numeroArchi() + "\n");

    }

    @FXML
    void doDreamTeam(ActionEvent event) {

    }

    @FXML
    void doGradoMassimo(ActionEvent event) {
    	
    	txtResult.appendText(this.model.gradoMassimo() + "\n");

    }

    @FXML
    void initialize() {
        assert btnConnesse != null : "fx:id=\"btnConnesse\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGradoMassimo != null : "fx:id=\"btnGradoMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
