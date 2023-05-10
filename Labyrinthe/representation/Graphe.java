package representation;

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
        
        
        
        this.listeSommet = listeSommets;
        this.listeArcs = listeArcs;
    }
    
	private boolean estValide(int[] listeSommets, int[][] listeArcs) {
        boolean valide;
        
        if (listeSommets == null && listeArcs == null) {
            return false;
        }
        valide = false;
        for (int i = 0; i < listeSommets.length; i++ ){
            for (int j = 0; j < listeArcs.length; j++) {
                if (listeArcs[j][0] == listeSommets[i] || listeArcs[j][1] == listeSommets[i]) {
                     valide = true;
                 }   
            }
            if (!valide){
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