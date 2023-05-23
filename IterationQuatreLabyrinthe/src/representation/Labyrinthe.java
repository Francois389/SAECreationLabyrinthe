package representation;

/**
 * 
 * Classe représentant des Graphes étant 
 * une liste de Sommets et 
 * une liste de liste de deux sommets représentant les arcs
 * @author Quentin Costes
 * @author François de Saint Palais
 *
 */
public class Labyrinthe {

    /** liste de tous le sommets */
    private int largeur;
    
    /** liste de tous le sommets */
    private int hauteur;
    
     	/** Affichage d'une case par defaut */
 	private static final String CASE = 
 								     """
 								     +-------+
 								     |	   	 |
 								     |	%d   |
 								     |		 |
 								     +-------+
 								   	 """;	    
    /**
     * Créer un graphe composé 
     * @param listeSommets
     * @param listeArcs
     */
	public Labyrinthe(int largeur, int hauteur) {
        super();
		this.largeur = largeur;
		this.hauteur = hauteur;
    }
    


    
    /** 
     * permet d'ajouter une arrete au graphe
     * @param extreemitée 1 de l'arrete
     * @param extremitée 2 de l'arrete
     * @throws IllegalArgumentException si les sommet ne sont pas dans le graphe
     * 									ou si l'arrete existe deja
     */
    public void ajouterArrete(Sommet sommet1, Sommet sommet2) {
        boolean sommet1Valide,
        		sommet2Valide;
        
        sommet1Valide = false;
        sommet2Valide = false;
        for (int i = 0; i < listeSommet.length; i++) {
            if (sommet1.equals(listeSommet[i])) {
                sommet1Valide = true;
            } else if (sommet2.equals(listeSommet[i])) {
                sommet2Valide = true;
            }        
        }
        
        if (!sommet1Valide || !sommet2Valide) {
            throw new IllegalArgumentException("les sommets données ne sont pas dans le graphe");
        }; 
        
        Sommet[] listTemp1 = {sommet1, sommet2};
        Sommet[] listTemp2 = {sommet2, sommet1};
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i] == listTemp1 || listeArcs[i] == listTemp2) {
                throw new IllegalArgumentException("l'arc existe deja");
            }
        }

   		Sommet listeArcs2[][] = new Sommet[this.listeArcs.length + 1][1];
   		
   		for (int i = 0; i < this.listeArcs.length; i++) {
            listeArcs2[i] = this.listeArcs[i];
        }
       	
       	Sommet[] listTemp = {sommet1, sommet2};
        listeArcs2[listeArcs2.length - 1] = listTemp; 			
		this.listeArcs = listeArcs2;
		      
    }
    
    /**
     * permet de creer une grille carre de 0 (salle), et de -1 (murs)
     * @r
     */
    private Sommet[][] creerGrille(int largeur, int hauteur) {
        
        Sommet[][] grilleRetour = new int[largeur][hauteur];
        
        for (int j = 0; j < hauteur; j++) {
            if (j%2 == 0) {
				for (int i = 0; i < largeur; i++) {
    	        	grilleRetour[j][i] = -1;
	        	}
            } else {
                for (int i = 0; i < largeur; i++) {
                    if (i%2 == 0) {
                        grilleRetour[j][i] = -1;
                    } else {
                        grilleRetour[j][i] = 0;
                    }
                }
            }
        }
        
        
        return grilleRetour; // stub
    }
    
    
    
    
    	@Override
	public String toString() {
        String affichage;
        affichage = "";
        for (int i = 0 ; i < this.largeur ; i++ ) {
            affichage += CASE ;
        }
		return affichage;
	}
    
    
    
}