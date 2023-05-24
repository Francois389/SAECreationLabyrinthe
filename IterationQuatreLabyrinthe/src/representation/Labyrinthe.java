package representation;

import java.lang.Math;

import org.hamcrest.Condition.Step;

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
    
	/** */
	private Sommet[][] listeSommet;  
	
	/** lsite des arcs du graphe */
	private Sommet[][] listeArcs;
    
 	/** Affichage du haut d'une case */
 	private static final String HAUT_CASE = "+-----+";	 
 	
 	/** Affichage des bord d'une case*/
 	private static final String BORD_CASE = "|     |";
 	
 	private static final int HAUTEUR_CASE = 3;
    /**
     * Créer un graphe composé 
     * @param largeur
     * @param hauteur
     * @throws IllegalArgumentException
     */
	public Labyrinthe(int hauteur, int largeur) {
        super();
        if (!(largeur > 0 && hauteur > 0)) {
			throw new IllegalArgumentException("largeur ou hauteur invalide");
        }
    	this.largeur = largeur;
		this.hauteur = hauteur;
		genererLabirynthe(largeur, hauteur);
		
    }
    
    
    /** 
     * modifie la grille de sommets
     */
    private void genererLabirynthe(int largeur, int hauteur) {
  		listeSommet = creerGrille(largeur, hauteur);
  		setMarqueSommet();
    }

	/**
     * permet de creer une grille carre de 0 (salle), et de -1 (murs)
     * @return grilleRetour liste de liste de sommets
     */
    private Sommet[][] creerGrille(int largeur, int hauteur) {
        
        Sommet[][] grilleRetour = new Sommet[hauteur][largeur];
        
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                grilleRetour[j][i] = new Sommet(j, i);
            }
        }
        return grilleRetour; // stub
    }
    
    /**
     * modifie les sommets de la grille
     */
    private void setMarqueSommet() {
        int marque;
        
        marque = 0;
    	for (int i = 0; i < listeSommet.length; i++) {
            for (int j = 0; j < listeSommet[0].length; j++) {
                marque++;
                Sommet s = listeSommet[i][j];
            	s.setMarque(marque);
            }
        }  
    }
    



    
    /** 
     * permet d'ajouter une arrete au graphe
     * @param extreemitée 1 de l'arrete
     * @param extremitée 2 de l'arrete
     * @throws IllegalArgumentException si les sommet ne sont pas dans le graphe
     * 									ou si l'arrete existe deja
     */
    private void ajouterArrete(Sommet sommet1, Sommet sommet2) {
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
     * Algorithme pour créer un labyrinthe avec un chemin
     */
    private void percerLesMurs() {
        Sommet sommetTest;
        do {
			int indiceXSommetRandom = (int) Math.random() * listeSommet.length;
			int indiceYSommetRandom = (int) Math.random() * listeSommet[0].length;
			Sommet sommetChoisie = listeSommet[indiceXSommetRandom][indiceYSommetRandom];
			for (int i = 0; i < listeSommet.length; i++) {
	            for (int j = 0; j < listeSommet[0].length; j++) {
                    sommetTest = listeSommet[i][j];
	                if (   sommetAdjacent(sommetChoisie, sommetTest) 
	                    && sommetChoisie.getMarque()!= sommetTest.getMarque()) {
						ajouterArrete(sommetTest, sommetChoisie);
						fusionnerMarques(sommetChoisie, sommetTest);
					}
	            }
	        }
		} while (ontLesMemeMarques());
    }
    
	private boolean ontLesMemeMarques() {
		int marque = listeSommet[0][0].getMarque();
		for (int i = 0; i < listeSommet.length; i++) {
	            for (int j = 0; j < listeSommet[0].length; j++) {
                    if (listeSommet[i][j].getMarque() != marque) {
						return false;
					}
	            }
	        }
		return true;
	}
	
	private boolean sommetAdjacent(Sommet sChoisie, Sommet sTeste) {
        return 
           (sChoisie.getPosX() == sTeste.getPosX() 
            && (sChoisie.getPosY()-1 == sTeste.getPosY() 
                || sChoisie.getPosY()+1 == sTeste.getPosY()
                )
           ) 
		|| (sChoisie.getPosY() == sTeste.getPosY() 
		    && (   sChoisie.getPosX()-1 == sTeste.getPosX() 
		        || sChoisie.getPosX()+1 == sTeste.getPosX()
		        )
		    );
    }
	
    /** 
     * fussionne les marques de tous les sommets
     * @param s1
     * @param s2
     */
    public void fusionnerMarques(Sommet s1, Sommet s2) {
        int marqueEcrasante = s1.getMarque();
        int marqueCreasee = s2.getMarque();
        
        // TODO parcourir les sommets et voir si ils ont la marque de l'ecrase, si oui le changer
        
        for (Sommet[] ligneSommet : listeSommet) {
            for (Sommet sommet : ligneSommet) {
                if (sommet.getMarque() == marqueEcrasee) {
                    sommet.setMarque(marqueEcransante);
                }
            }
        }
        
        
    } 
    
    
    
    @Override
	public String toString() {
        String affichage;
        affichage = "";
        
        for (int hauteur = 0 ; hauteur < this.hauteur ; hauteur++){ 
        	for (int j = 0 ; j < this.largeur ; j++ ) {
                affichage += HAUT_CASE;
                
            }    
		    for (int i = 0; i < HAUTEUR_CASE ; i++) {
	            affichage += "\n";		
	           	for (int j = 0; j < this.largeur; j++) {
	           		affichage += BORD_CASE; 
				}
	        }
	        affichage += "\n";
	        for (int j = 0 ; j < this.largeur ; j++ ) {
	            affichage += HAUT_CASE;
	            
	        }
	        affichage += "\n";
	    }
        // TODO peter le mur en affichage
		return affichage;
	} 
}