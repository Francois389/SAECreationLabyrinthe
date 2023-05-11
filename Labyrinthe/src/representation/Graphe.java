package representation;

import outils.OutilsListe;
import  representation.Sommet;

/**
 * 
 * Classe représentant des Graphes étant 
 * une liste de Sommets et 
 * une liste de liste de deux sommets représentant les arcs
 * @author Quentin Costes
 * @author François de Saint Palais
 *
 */
public class Graphe {

    //TODO des que la classe Sommet est fini changer les tableau de int en tableau de Sommet
    /** liste de tous le sommets */
    private Sommet[] listeSommet;
    
    /** liste de tous le sommets */
    private Sommet[][] listeArcs;
    
    /**
     * Créer un graphe composé 
     * @param listeSommets
     * @param listeArcs
     */
	public Graphe(Sommet[] listeSommets, Sommet[][] listeArcs) {
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
	private boolean estValide(Sommet[] listeSommets, Sommet[][] listeArcs) {
        
        /* Un graphe doit forcement avoir un arc et un sommet dans notre situation */
        if (   listeSommets == null  || listeArcs == null 
            || listeArcs.length == 0 || listeArcs[0].length == 0 
            || listeSommets.length == 0) {
            return false;
        }
        
        /* Vérifie que tous les sommets des arcs existent */
        for (int j = 0; j < listeArcs.length; j++) {
            if (   !OutilsListe.contient(listeSommets, listeArcs[j][0]) 
                || !OutilsListe.contient(listeSommets, listeArcs[j][1])) {
                return false;
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
	 * renvoie le nombre d'arcs du graphe
	 * @return int nombre d'arcs
	 */
    public int getNbArretes() {
        return listeArcs.length;
    }
    
    /**
     * Renvoie le nombre de sommets du graphe
     * @return int nombre de sommet
     */
    public int getNbSommets() {
        return listeSommet.length;
    }
    
    /**
     * Permet de verifier la présence d'un arc entre deux sommets
     * @param sommet1
     * @param sommet2
     * @return true si un arc existe entre les deux sommets, false sinon
     */
    public boolean sontRelies (Sommet sommet1, Sommet sommet2) {
        
        for (int i = 0; i < listeArcs.length; i++) {
         	if (   listeArcs[i][0].equals(sommet1) && listeArcs[i][1].equals(sommet2)
         	    || listeArcs[i][0].equals(sommet2) && listeArcs[i][1].equals(sommet1) ) {
            	return true;
            }  
        }
        
        return false;
        
    }
}