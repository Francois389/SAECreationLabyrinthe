package representation;

import java.util.Iterator;

//import  representation.Sommet;

/**
 * 
 * //TODO Commenter la responsabilités de la classe Graphe
 * @author Quentin Costes
 * @author François de Saint Palais
 *
 */
public class Graphe {

    //TODO des que la classe Sommet est fini changer les tableau de int en tableau de Sommet
    /** liste de tous le sommets */
    private int[] listeSommet;
    
    /** liste de tous le sommets */
    private int[][] listeArcs;
    
    /**
     * Créer un graphe composé 
     * @param listeSommets
     * @param listeArcs
     */
	public Graphe(int[] listeSommets, int[][] listeArcs) {
        super();
        
        if (!estValide(listeSommets,listeArcs)) {
            throw new IllegalArgumentException("Erreur - Paramétre du constructeur invalide");
        }     
        // else
        this.listeSommet = listeSommets;
        this.listeArcs = listeArcs;
    }
    
	/**
	 * Fonction qui vérifie la validité du couple sommet - arcs, 
	 * pour créer un graphe valide.
	 * @param listeSommets
	 * @param listeArcs
	 * @return
	 */
	private boolean estValide(int[] listeSommets, int[][] listeArcs) {
        boolean estValide;
        
        /* Un graphe doit forcement avoir un arc et un sommet dans notre situation */
        if (   listeSommets == null  || listeArcs == null 
            || listeArcs.length == 0 || listeArcs[0].length == 0 
            || listeSommets.length == 0) {
            return false;
        }
        
        estValide = true;
        /* Vérifie que tous les sommets des arcs existent */
        for (int j = 0; j < listeArcs.length; j++) {
            if (contient(listeSommets, listeArcs[j][0]) && contient(listeSommets,listeArcs[j][1])) {
                estValide = true && estValide;
            }
        }
        
        /* On vérifie l'irreflexivitée du graphe */
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i][0] == listeArcs[i][1]) {
                return false;
            }
        }
        return true;
    }

	
	/**
	 * Renvoie true si element est dans liste.
	 * False sinon.
	 * @param liste
	 * @param element
	 * @return
	 */
	private static boolean contient(int[] liste, int element) {
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] == element) {
                return true;
            }
        }
        return false; //STUB
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