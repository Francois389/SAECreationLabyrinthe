/**
 * Labyrinthe.java                       16 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package representation;


/**
 * Permet de stocker un labyrinthe qui est sous type de Graphe
 * @author Costes Quentin
 * @author Descriaud Lucas
 *
 */
public class Labyrinthe extends Graphe{
 
 	/** Sommet etant le début du labyrinthe */
 	private Sommet debut;
 	
 	/** Sommet etant la sortie du labyrinthe*/
 	private Sommet fin; 
 	
 	/** Graphe avec lequel on veut creer le labyrinthe*/
 	private Graphe graphe;
 	
 	/**
 	 * Constructeur de la classe
 	 * Les type de graphe suivant ne peuvent pas etre des labyrinthe
 	 * <ul>
 	 * 	<li> Graphe reflexif ou comportant des boucles </li>
 	 *	<li> Graphe avec des circuits </li>
 	 *	<li> Graphhe comportant des sous graphes 
 	 *		 ou des sommets isolés </li>
 	 * <ul>
 	 * @param graphe representant le labyrinthe
 	 * @param debut sommet du debut labyrinthe
 	 * @param fin sommet du graphe etant la fin du labyrinthe
 	 * @throws IllegalArgumentException
	 */ 	
	public Labyrinthe(Sommet[] lSommet, Sommet[][] lArcs, Sommet debut, Sommet fin) {
		super(lSommet,lArcs);
  		
  		this.graphe = new Graphe(lSommet, lArcs);
  		this.debut = debut;
  		this.fin = fin; 
  		
 	}

}
