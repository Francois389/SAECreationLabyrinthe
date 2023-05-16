package representation;

import outils.OutilsListe;
//import representation.Sommet;

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
        
        try {
            if (estValide(listeSommets,listeArcs)) {
                this.listeSommet = listeSommets;
                this.listeArcs = listeArcs;            
            }     
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Erreur - Paramètres du constructeur invalide.\n\tRaison : "
                    + e.getMessage());
        }
    }
    
	/**
	 * Fonction qui vérifie la validité du couple sommet - arcs, 
	 * pour créer un graphe valide.
	 * @param listeSommets
	 * @param listeArcs
	 * @return
	 */
	protected static boolean estValide(Sommet[] listeSommets, Sommet[][] listeArcs) 
	throws IllegalArgumentException{
        
        /* Dans notre situation, un graphe doit forcement avoir un arc et un sommet*/
        if (   listeSommets == null  || listeArcs == null 
            || listeArcs.length == 0 || listeArcs[0].length == 0 
            || listeSommets.length == 0) {
            throw new IllegalArgumentException("Une des liste est vide");
        }
        
        /* Vérifie que tous les sommets des arcs existent */
        for (int j = 0; j < listeArcs.length; j++) {
            if (   !OutilsListe.contient(listeSommets, listeArcs[j][0]) 
                || !OutilsListe.contient(listeSommets, listeArcs[j][1])) {
                throw new IllegalArgumentException("Un arcs pointe un sommet inexistant");
            }
        }
        
        /* Le graphe n'a pas de sommet en double */
        for (int i = 0; i < listeSommets.length; i++) {
            for (int j = 0; j < listeSommets.length; j++) {
                if (i != j && listeSommets[i].equals(listeSommets[j])) {
                    throw new IllegalArgumentException("Le graphe contient de fois même sommet");
                }
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
     * dans les deux sens.
     * @param sommet1
     * @param sommet2
     * @return true si un arc existe entre les deux sommets, false sinon
     */
    public boolean sontRelies (Sommet sommet1, Sommet sommet2) {
//        for (int i = 0; i < listeArcs.length; i++) {
//         	if (   listeArcs[i][0].equals(sommet1) && listeArcs[i][1].equals(sommet2)
//         	    || listeArcs[i][0].equals(sommet2) && listeArcs[i][1].equals(sommet1) ) {
//            	return true;
//            }  
//        }
        return    existeArcEntre(sommet1, sommet2) 
               || existeArcEntre(sommet2, sommet1);
    }
    
    public boolean existeArcEntre(Sommet sommet1, Sommet sommet2) {
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i][0].equals(sommet1) && listeArcs[i][1].equals(sommet2)) {
                return true;
            }  
        }
        return false;
    }
    
    
    /**
     *@return une matrice booléenne 
     */
    public boolean[][] toMatriceAdjacence() {
        boolean[][] matrice = new boolean[this.listeSommet.length][this.listeSommet.length];
        for (int i = 0; i < listeSommet.length; i++) {
            for (int j = 0; j < listeSommet.length; j++) {
                matrice[i][j] = existeArcEntre(listeSommet[i],listeSommet[j]);
            }
        }
        
        return matrice;
    }
    
    
    public boolean isReflexif() {
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i][0] == (listeArcs[i][1])) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean contienBoucle() {
        boolean[][] matrice = this.toMatriceAdjacence();
        boolean continuer;
        
        continuer = false;
        do {
            for (int i = 0; i < matrice.length; i++) {
         		
             	if (ligneVide(i) || colonneVide(i)) {
                     supLigneColonne(i);
                 }  
        	}
        } while (continuer);
        
        return continuer;
    }
    
    @Override
    public String toString() {
        
        
        String toString;
        toString = "Sommets : \n" ;
        for (int i = 0 ;  i < getNbSommets() ; i++){
            toString += listeSommet[i].toString();
        }
        toString += "\nArcs :";
        for (int i = 0 ; i < getNbArretes() ; i++){
            toString += "(" + "(" + listeArcs[i][0].getPosX() + ", " + listeArcs[i][0].getPosY() + "),"
                            + "(" + listeArcs[i][1].getPosX() + ", " + listeArcs[i][1].getPosY() + "))";
        }
        return toString ; 
    }
}



























