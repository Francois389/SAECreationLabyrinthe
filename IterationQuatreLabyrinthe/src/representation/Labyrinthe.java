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
    private Sommet[] listeSommet;
    
    /** liste de tous le sommets */
    private Sommet[][] listeArcs;
        
    /**
     * Créer un graphe composé 
     * @param listeSommets
     * @param listeArcs
     */
	public Labyrinthe(Sommet[] listeSommets, Sommet[][] listeArcs) {
        super();
        //TODO 
        this.listeSommet = listeSommets;
        this.listeArcs = listeArcs;            
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
          
        // TODO verifier que l'arrete n'existe pas deja
   		Sommet listeArcs2[][] = new Sommet[this.listeArcs.length + 1][1];
   		
   		for (int i = 0; i < this.listeArcs.length; i++) {
            listeArcs2[i] = this.listeArcs[i];
        }
       	
       	Sommet[] listTemp = {sommet1, sommet2};
        listeArcs2[listeArcs2.length - 1] = listTemp; 			
		this.listeArcs = listeArcs2;
		      
    }
    
}