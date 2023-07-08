package it.polito.tdp.baseball.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.baseball.db.BaseballDAO;

public class Model {
	
	private Graph<People, DefaultEdge> grafo;
	private BaseballDAO dao;
	private Map<String, People> peopleIdMap;
	
	public Model() {
		this.dao = new BaseballDAO();
		peopleIdMap = new HashMap<>();
		for (People p : this.dao.readAllPlayers()) {
			peopleIdMap.put(p.getPlayerID(), p);
		}
	}
	
	public void creaGrafo(int anno, double salario) {
		grafo = new SimpleGraph<People, DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, this.dao.giocatoriSalarioAnno(anno, salario));
		for (Adiacenza a : this.dao.getAdiacenze(anno, salario) ) {
			Graphs.addEdgeWithVertices(this.grafo, peopleIdMap.get(a.getP1()), peopleIdMap.get(a.getP2()));
			People p = peopleIdMap.get(a.getP1());
		}
		
	}
	
	public String gradoMassimo() {
		
		int gradoMassimo = 0;
		People giocatoreGradoMassimo = null;
		for (People giocatore : this.grafo.vertexSet() ) {
			giocatore.setGrado(this.grafo.edgesOf(giocatore).size());
			if (giocatore.getGrado() > gradoMassimo) {
				gradoMassimo = giocatore.getGrado();
				giocatoreGradoMassimo = peopleIdMap.get(giocatore.getPlayerID());
			}
			
			
		}
		
		return giocatoreGradoMassimo.toString() + ", Grado: " + gradoMassimo;
		
	}
	
	public boolean grafoCreato() {
		if (this.grafo.vertexSet().isEmpty()) {
			if (this.grafo.edgeSet().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public int componentiConnesse() {
		
		ConnectivityInspector<People, DefaultEdge> inspector = 
				new ConnectivityInspector<People, DefaultEdge>(this.grafo);
		return inspector.connectedSets().size();

		
	}
	
	
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
    public int numeroArchi() {
		return this.grafo.edgeSet().size();
	}

}

