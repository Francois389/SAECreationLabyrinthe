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
 	private Sommet sommetDebut;
 	
 	/** Sommet etant la sortie du labyrinthe*/
 	private Sommet sommetFin; 
 	
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
  		
  		try {
			if (estValide(lSommet, lArcs, debut, fin)){
				graphe = new Graphe(lSommet, lArcs);
  				sommetDebut = debut;
  				sommetFin = fin;
			}
			
		 } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Erreur - ParamÃ¨tres du constructeur invalide. \n\tRaison :"
                    + e.getMessage());
        }
 	}
 	
 	
 	/**
	 * Fonction qui vÃ©rifie la validitÃ© du couple sommet - arcs, 
	 * pour crÃ©er un graphe valide.
	 * @param listeSommets
	 * @param listeArcs
	 * @throws IllegalArgumentException
	 * <ul>
 	 * 	<li> Graphe reflexif ou comportant des boucles </li>
 	 *	<li> Graphe avec des circuits </li>
 	 *	<li> Graphhe comportant des sous graphes 
 	 *		 ou des sommets isolés </li>
 	 * <ul> 
	 * @return true si un labyrinthe peut etre construit.
	 */
	private static boolean estValide(Sommet[] listeSommets, Sommet[][] listeArcs, Sommet debut, Sommet fin){
		
		/* On vrifie l'irreflexivitÃ©e du graphe */
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i][0] == (listeArcs[i][1])) {
                throw new IllegalArgumentException("Le graphe n'est pas irreflexif");
            }
        }
        
        return true;
		
	}
}
