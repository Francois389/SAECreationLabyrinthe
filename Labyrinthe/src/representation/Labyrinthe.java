/**
 * Labyrinthe.java                       16 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package representation;


/**
 * Permet de stocker un labyrinthe qui est sous type de Graphe
 * @author Costes Quentin
 * @author de Saint Palais François
 * @author Descriaud Lucas
 *
 */
public class Labyrinthe extends Graphe{
 
 	/** Sommet etant le début du labyrinthe */
 	private Sommet sommetDebut;
 	
 	/** Sommet etant la sortie du labyrinthe*/
 	private Sommet sommetFin; 
 	
 	/** Graphe avec lequel on veut creer le labyrinthe*/
 	private Graphe graphe;
 	
 	/**
 	 * Constructeur de la classe
 	 * Les type de graphe suivant ne peuvent pas être des labyrinthe
 	 * <ul>
 	 * 	<li> Graphe réflexif ou comportant des boucles </li>
 	 *	<li> Graphe avec des circuits </li>
 	 *	<li> Graphe comportant des sous graphes 
 	 *		 ou des sommets isolés </li>
 	 * </ul>
 	 * @param graphe représentant le labyrinthe
 	 * @param debut sommet du début labyrinthe
 	 * @param fin sommet du graphe étant la fin du labyrinthe
     * @throws IllegalArgumentException 
	 */ 	
	public Labyrinthe(Sommet[] lSommet, Sommet[][] lArcs, Sommet debut, Sommet fin) {
		super(lSommet,lArcs);
  		
  		try {
			if (estValide(lSommet, lArcs, debut, fin)){
				graphe = new Graphe(lSommet, lArcs);
  				sommetDebut = debut;
  				sommetFin = fin;
			}
			
		 } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Erreur - Paramètres du constructeur invalide. \n\tRaison :"
                    + e.getMessage());
        }
 	}
 	
 	
 	/**
	 * Fonction qui vérifie la validitÃ© du couple sommet - arcs, 
	 * pour créer un graphe valide.
	 * @param listeSommets
	 * @param listeArcs
	 * @throws IllegalArgumentException
	 * <ul>
 	 * 	<li> Graphe réflexif ou comportant des boucles </li>
 	 *	<li> Graphe avec des circuits </li>
 	 *	<li> Graphe comportant des sous graphes 
 	 *		 ou des sommets isolés </li>
 	 * <ul> 
	 * @return true si un labyrinthe peut être construit.
	 */
	private static boolean estValide(Sommet[] listeSommets, Sommet[][] listeArcs, Sommet debut, Sommet fin){
		Graphe g = new Graphe(listeSommets, listeArcs);
		
		/* On vérifie l'irreflexivitée du graphe */
        if (!g.estIrreflexif()) {
			throw new IllegalArgumentException("Le graphe n'est pas irréflexif");
		}
        
        /* Vérifie qu'il n'y ai pas de Circuit */
        if (g.contienCircuit()) {
			throw new IllegalArgumentException("Le graphe contient des circuits");
		} else {
		    System.out.println("qvusodu");
		    System.out.println(g);
		}
        
        //TODO Vérifier que le graphe est connexe.
        
        
        return true;
		
	}
}
