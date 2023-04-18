package representation;

import  representation.Sommet;

public class Graphe {

    /** liste de tous le sommets */
    private Sommet[] listeSommet;
    
    /** liste de tous le sommets */
    private Sommet[][] listeArcs;
    
    
	public Graphe(Sommet[] listeSommets, Sommet[][] listeArcs) {
        super();
        this.listeSommet = listeSommets;
        this.listeArcs = listeArcs;
    }
    
	/**
	 * renvoie le nombre d'arcs du graphe
	 * @return int nombre d'arcs
	 */
    public int getNbArretes() {
        return this.listeArcs.length;
    }
    
    /**
     * Renvoie le nombre de sommets du graphe
     * @return int nombre de sommet
     */
    public int getNbSommets() {
        return this.listeSommet.length;
    }
}