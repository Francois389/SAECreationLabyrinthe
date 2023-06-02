package representation;

import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Objects;

import representation.PileContigue;
import outils.OutilsTableau;

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

     /** Affichage du haut d'une case */
     private static final String HAUT_CASE = "+-----+";
     
     /** Mur du haut vide */
     private static final String HAUT_CASE_VIDE = "+     +";     
     
     /** Affichage des bord d'une case*/
     private static final String BORD_CASE = "|";
     
     /** Chaine vide pour l'espace a l'interieur des cases */ 
     private static final String CHAINE_VIDE = "     ";
     
     /** Affichage d'un bord vide'*/
     private static final String BORD_VIDE = " ";
     
     /** indice pour la liste des voisins du sommet corrsepondant a haut */
     private static final int HAUT = 0;
 
     /** indice pour la liste des voisins du sommet corrsepondant a droite */    
     private static final int DROITE = 1;
 
     /** indice pour la liste des voisins du sommet corrsepondant a bas */    
     private static final int BAS = 2;
 
     /** indice pour la liste des voisins du sommet corrsepondant a gauche */    
     private static final int GAUCHE = 3;
     
     /** Hauteur en ligne dans la console texte */
     private static final int HAUTEUR_CASE = 3;
    
     
     private Sommet entree;
     
     private Sommet sortie;
     

    /** liste de tous le sommets */
    private int largeur;
    
    /** liste de tous le sommets */
    private int hauteur;
   
    /** liste des sommets du graphe */
    private Sommet[][] listeSommet;  
    
    /** liste des arcs du graphe */
    private Sommet[][] listeArcs;
    
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

        listeArcs = new Sommet[0][0];
        entree = new Sommet(0, 0);
        sortie = new Sommet(largeur - 1, hauteur - 1);
        genererLabirynthe();
    }
    
    /**
     * Créer un graphe composé 
     * @param largeur
     * @param hauteur
     * @throws IllegalArgumentException
     */
    public Labyrinthe(int hauteur, int largeur, Sommet entre, Sommet sortie) {
        super();
        if (!(largeur > 0 && hauteur > 0)) {
            throw new IllegalArgumentException("largeur ou hauteur invalide");
        }
        this.largeur = largeur;
        this.hauteur = hauteur;

        listeArcs = new Sommet[0][0];
        this.entree = entre;
        this.sortie = sortie;
        genererLabirynthe();
    }
    
    /**
     * Constructeur utilisé pour la construction d'un labyrinthe 
     * issu d'un fichier .json
     * TODO commenter
     * @param hauteur
     * @param largeur
     * @param listeSommet
     * @param listeArcs
     * @param entree
     * @param sortie
     */
    public Labyrinthe(int hauteur, int largeur, 
                      Sommet[][] listeSommet,
                      Sommet[][] listeArcs,
                      Sommet entree, Sommet sortie) {
        super();
        if (!(largeur > 0 && hauteur > 0)) {
            throw new IllegalArgumentException("largeur ou hauteur invalide");
        }
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.listeSommet = listeSommet;
        this.listeArcs = listeArcs;
        this.entree = entree;
        this.sortie = sortie;
    }
       
    
    
    /** 
     * Créer une grille de taille largeur x hauteur
     * Est marque les sommet avec une marque unique
     * @param largeur La largeur voulue pour la grille/ le labyrinthe
     * @param hauteur La hauteur voulue pour la grille/ le labyrinthe
     */
    private void genererLabirynthe() {
          listeSommet = creerGrille(largeur, hauteur);
          
          if (entree.equals(sortie)) {
        	  throw new IllegalArgumentException("entrée et sortie confondue");
          }      
    }


    /**
     * permet de créer une grille carre de 0 (salle), et de -1 (murs)
     * @param largeur La largeur voulue pour la grille/ le labyrinthe
     * @param hauteur La hauteur voulue pour la grille/ le labyrinthe
     * @return grilleRetour liste de liste de sommets
     */
    private Sommet[][] creerGrille(int largeur, int hauteur) {
        
        Sommet[][] grilleRetour = new Sommet[hauteur][largeur];
        
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                grilleRetour[i][j] = new Sommet(j, i);
            }
        }

        return grilleRetour; // stub
    }
   

	// TODO remettre private
    /** 
     * permet d'ajouter une arrete au graphe
     * @param sommet1 1 de l'arrete
     * @param sommet2 2 de l'arrete
     * @throws IllegalArgumentException si les sommet ne sont pas dans le graphe
     *                                     ou si l'arrete existe deja
     */
    public void ajouterArrete(Sommet sommet1, Sommet sommet2) {
        boolean sommet1Valide,
                sommet2Valide;
        
        sommet1Valide = estPresent(sommet1);
        sommet2Valide = estPresent(sommet2);
        
        if (!sommet1Valide || !sommet2Valide) {
            throw new IllegalArgumentException("les sommets données ne sont pas dans le graphe");
        }; 
        
        Sommet[] listTemp1 = {sommet1, sommet2};
        Sommet[] listTemp2 = {sommet2, sommet1};
        if (this.listeArcs != null) {
            for (int i = 0; i < listeArcs.length; i++) {
                if (listeArcs[i] == listTemp1 || listeArcs[i] == listTemp2) {
                    throw new IllegalArgumentException("l'arc existe deja");
                }
            }    
        }
        
        
        if (sommet1.getPosY() > sommet2.getPosY()) {
            sommet1.setVoisin(true, 0);
            sommet2.setVoisin(true, 2);
        } 
        if (sommet1.getPosY() < sommet2.getPosY()) {
            sommet1.setVoisin(true, 2);
            sommet2.setVoisin(true, 0);
        }
        if (sommet1.getPosX() > sommet2.getPosX()) {
            sommet1.setVoisin(true, 3);
            sommet2.setVoisin(true, 1);
        }
        if (sommet1.getPosX() < sommet2.getPosX()) {
            sommet1.setVoisin(true, 1);
            sommet2.setVoisin(true, 3);
        }
        
        if (this.listeArcs != null) {
            Sommet listeArcs2[][] = new Sommet[this.listeArcs.length + 1][1];
            for (int i = 0; i < this.listeArcs.length; i++) {
                listeArcs2[i] = this.listeArcs[i];
            }    
            
            Sommet[] listTemp = {sommet1, sommet2};
            listeArcs2[listeArcs2.length - 1] = listTemp;
            
            this.listeArcs = listeArcs2;
        } else {
            Sommet listeArcs2[][] = new Sommet[1][1];
            Sommet[] listTemp = {sommet1, sommet2};
            listeArcs2[0] = listTemp;
            
            this.listeArcs = listeArcs2;
        }     
    }
    
    /**
     * Vérifie qu'un sommet ce trouve dans un graphe
     * @return true si sommet est dans graphe false sinon
     */
    public boolean estPresent(Sommet sommet) {
        for (Sommet[] sommets : listeSommet) {
            for (Sommet sTester : sommets) {
                if (sommet.equals(sTester)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
     * Algorithme de construction du labyrinthe par chaine ascendante
     */
    public void chaineAscendante() {
        int nbArcCreer;
        nbArcCreer = 0;  

    	do {
    		int indiceXSommetRandom = (int) (Math.random() * listeSommet.length) ;
        	int indiceYSommetRandom = (int) (Math.random() * listeSommet[0].length);
            Sommet sommetChoisie = listeSommet[indiceXSommetRandom][indiceYSommetRandom];
            Sommet[] voisinsChoisi = getSommetsVoisins(sommetChoisie);

            Sommet sommetAAteindre = voisinsChoisi[new Random().nextInt(voisinsChoisi.length)];
            
           
            if (sommetChoisie.getMarque() == -1 && sommetAAteindre.getMarque() == -1) {
            	nbArcCreer++;     
            	sommetChoisie.setMarque(nbArcCreer);
            	sommetAAteindre.setMarque(nbArcCreer);
            	ajouterArrete(sommetChoisie, sommetAAteindre);
            } else if (sommetAAteindre.getMarque() != sommetChoisie.getMarque()) {
            	if (sommetChoisie.getMarque() == -1) {
            		nbArcCreer++; 
            		sommetChoisie.setMarque(sommetAAteindre.getMarque());
            		ajouterArrete(sommetChoisie, sommetAAteindre);
            	} else if (sommetAAteindre.getMarque() == -1) {
            		nbArcCreer++; 
            		sommetAAteindre.setMarque(sommetChoisie.getMarque());
            		ajouterArrete(sommetChoisie, sommetAAteindre);
            	} else {
                	nbArcCreer++;   
                	fusionnerMarques(sommetChoisie, sommetAAteindre);
                	ajouterArrete(sommetChoisie, sommetAAteindre);               
                }
            }       			            
         } while (nbArcCreer < this.hauteur * this.largeur - 1) ;
    }
    
    /** 
     * permetr de récuperer les sommet connexes a un graphe passé en paramtres
     * @param s sommet a récuperer sa connexitée
     * @return liste de sommet connexe a s
     */
    private Sommet[] getConnexitée(Sommet s) {
    	
    	ArrayList<Sommet> sommetsConnexes = new ArrayList<>();
    	 
      	for (int i = 0; i < listeSommet.length; i++) {
			for (int j = 0; j < listeSommet[i].length; j++) {
				if (listeSommet[i][j].getMarque() == s.getMarque()) {
					sommetsConnexes.add(listeSommet[i][j]);
				}
			}
		}
    	
    	Sommet[] retour = new Sommet[sommetsConnexes.size()];
    	
    	for (int i = 0; i < sommetsConnexes.size(); i++) {
			retour[i] = sommetsConnexes.get(i);
		}
    	
		return retour;    	
    }

    
    /**
     * Vérifie si tous les sommets de la grille on la même marque.
     * @return true s'ils ont tous la même et false sinon 
     */
    public boolean ontTousLaMemeMarque() {
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
    
    /**
     * @return true si les deux sommet sont adjacent dans la grille false sinon.
     */
    private static boolean sommetAdjacent(Sommet sChoisie, Sommet sTeste) {
        int sChoisiX = sChoisie.getPosX();
        int sChoisiY = sChoisie.getPosY();
        int sTesteX = sTeste.getPosX();
        int sTesteY = sTeste.getPosY() ;
        return   (sChoisiX == sTesteX && (   sChoisiY - 1 == sTesteY
                                          || sChoisiY + 1 == sTesteY)
                  ) 
              || (sChoisiY == sTesteY && (   sChoisiX - 1 == sTesteX
                                          || sChoisiX + 1 == sTesteX)
                  );
    }
    
    /** 
     * Fusionne les marques de tous les sommets connexes aux sommet s1 et s2
     * @param s1 
     * @param s2
     */
    public void fusionnerMarques(Sommet sommetEcrasant, Sommet sommetEcrase) {
        Sommet[] connexitée = getConnexitée(sommetEcrase);
        
        for (int i = 0; i < connexitée.length; i++) {
			connexitée[i].setMarque(sommetEcrasant.getMarque());
		}
    } 
    
    /**
     * @param tab un tableaud de booleen
     * @return true si tous les elements sont true
     *         false sinon
     */
    private boolean sontTousVisites(Sommet[] tab) {
        
        for (Sommet s : tab) {
            if (!s.estParcourus()) {
                return false;
            }
        }
        return true;
        
    }
    
    
    /**
     * Construction par backtracking avec une pile
     * en modifiant la liste des arcs via l'appel de la 
     * méthode ajouterArc
     */
    public void constructionBacktracking(){
        setMarqueSommet();
        
        boolean[] sommetsVisites = new boolean[largeur * hauteur];
        
        PileContigue pileSommets = new PileContigue();
        
        int indiceXSommetRandom = (int)(Math.random() * (listeSommet.length-1));
        int indiceYSommetRandom = (int)(Math.random() * (listeSommet[0].length-1)); 
        Sommet sommetCourant = listeSommet[indiceXSommetRandom][indiceYSommetRandom];
        //On marque le sommet comme parcourue
        sommetsVisites[getIndice(indiceXSommetRandom, indiceYSommetRandom)] = true;
        sommetCourant.setEstParcourus(true);
        //On empile le sommet pris au hasard
        pileSommets.empiler(sommetCourant);
        
        while (!pileSommets.estVide()) {
            Sommet[] listeVoisins = getSommetsVoisins(sommetCourant);
            
            for (int i = 0; i < listeVoisins.length; i++ ) {
                if (listeVoisins[i].estParcourus()) {
                    listeVoisins[i] = null;
                }
            }
            listeVoisins = OutilsTableau.copieSaufNull(listeVoisins);
            if (listeVoisins.length == 0) {
                pileSommets.depiler();
                if (!pileSommets.estVide()) {
                    sommetCourant = (Sommet) pileSommets.sommet();
                }
            } else {
                 boolean sommetRandomTrouve = false;
                 for (int i = 0; i < listeVoisins.length && !sommetRandomTrouve; i++) {
                     int indiceVoisinRandom = (int)(Math.random() * (listeVoisins.length));
                     Sommet voisinRandom = listeVoisins[indiceVoisinRandom];

                     if (!voisinRandom.estParcourus()) {
                         ajouterArrete(sommetCourant, voisinRandom);
                         pileSommets.empiler(voisinRandom);              
                         voisinRandom.setEstParcourus(true);
                         sommetsVisites[voisinRandom.getMarque() - 1] = true;
                         sommetCourant = (Sommet) pileSommets.sommet();
                         sommetRandomTrouve = true;
                     }
                 } 
            }
        }
    }
    
    /**
     * Mes des marques unique sur les sommets du labyrinthe
     * Les marques commencent à 0
     */
    public void setMarqueSommet() {
        int marque;
        
        marque = 0;
        for (int i = 0; i < listeSommet.length; i++) {
            for (int j = 0; j < listeSommet[0].length; j++) {
                marque++;
                Sommet s = listeSommet[i][j];
                s.setMarque(marque);
                s.setEstParcourus(false);
            }
        }  
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    private int getIndice(int x, int y) {
        return y * largeur + x + 1;
    }
    
    /**
     * 
     * @param s1
     * @param s2
     * @return
     */
    private boolean existeArete(Sommet s1, Sommet s2) {
       
        return true; // stub
    }
    
    
    /**
     * Renvoie la liste des voisins d'un sommet
     * @param sommet le sommet dont on veut avoir les voisins
     */
    private Sommet[] getSommetsVoisins(Sommet sommet) {
        int indice;
        Sommet[] retour = new Sommet[4];
        
        indice = 0;
        for (Sommet[] liste : listeSommet) {
            for (Sommet s : liste) {
                if (existeArete(sommet, s)) {
                    retour[indice] = s;
                    indice++;
                }
            }
        }
        
        if (indice != 4) {
            Sommet[] nouveauTableau = new Sommet[indice];
            for (int i = 0; i < indice; i++) {
                nouveauTableau[i] = retour[i];
            }
            retour = nouveauTableau;
        }
        return retour;
    }    
    
    
    /** 
     * effectue le parcours en main droite (profondeur)
     * a partir du sommet defini comme entree
     * @return le parcours en profondeur a partir de l'entree
     */
    public PileContigue parcoursProfondeur() {
        
        setMarqueSommet();
        
        PileContigue pileSommets = new PileContigue();
        pileSommets.empiler(entree);
        entree.setEstParcourus(true);
        
        Sommet sommetCourant = (Sommet) pileSommets.sommet();
        
        while (!sommetCourant.equals(sortie)) {
            
            Sommet[] listeVoisins = getSommetsVoisins(sommetCourant);
            
            for (int i = 0; i < listeVoisins.length; i++ ) {
                if (listeVoisins[i].estParcourus()) {
                    listeVoisins[i] = null;
                }
            }
            listeVoisins = OutilsTableau.copieSaufNull(listeVoisins);
            
            if (sontTousVisites(listeVoisins)) {
                pileSommets.depiler();
                if (!pileSommets.estVide()) {
                    sommetCourant = (Sommet) pileSommets.sommet();
                } 
            } else {
                sommetCourant = listeVoisins[0];
                sommetCourant.setEstParcourus(true);
                pileSommets.empiler(sommetCourant);
            }
            
        }
        
        return pileSommets;
        
    }
    
    
    @Override
    public String toString() {
        String affichage;
        affichage = "";

        for (int hauteur = 0 ; hauteur < this.hauteur ; hauteur++){ 
            for (int j = 0 ; j < this.largeur ; j++ ) {
                if (this.listeSommet[hauteur][j].getVoisins()[HAUT]) {
                    affichage += HAUT_CASE_VIDE; 
                }
                else {
                    affichage += HAUT_CASE;
                }  
            }    
            for (int i = 0; i < HAUTEUR_CASE ; i++) {
                affichage += "\n";        
                   for (int j = 0; j < this.largeur; j++) {
                    if (this.listeSommet[hauteur][j].getVoisins()[GAUCHE]) {
                        affichage += BORD_VIDE; 
                    } else {
                        affichage += BORD_CASE;
                    }
                    	
                    	if (this.listeSommet[hauteur][j].equals(entree) && i == 1) {
                    		affichage += "  E  ";
                    	} else if (this.listeSommet[hauteur][j].equals(sortie) && i == 1) {
                    		affichage += "  S  ";
                    	} else {
                    		affichage += CHAINE_VIDE;
                    	}
                    	
                       
                       if (this.listeSommet[hauteur][j].getVoisins()[DROITE]){
                        affichage += BORD_VIDE;   
                    } else {
                        affichage += BORD_CASE; 
                    }
                       
                }
            }
            affichage += "\n";
            for (int j = 0 ; j < this.largeur ; j++ ) {
                if (this.listeSommet[hauteur][j].getVoisins()[BAS]){
                        affichage += HAUT_CASE_VIDE;   
                } else {
                    affichage += HAUT_CASE; 
                }     
            }
            affichage += "\n";
        }
        return affichage;
    }

    /**
     * @return la valeur de listeSommet
     */
    public Sommet[][] getListeSommet() {
        return listeSommet;
    }

    /**
     * @return la valeur de listeArcs
     */
    public Sommet[][] getListeArcs() {
        return listeArcs;
    } 
    
        /**
     * @return la valeur de entree
     */
    public Sommet getEntre() {
        return entree;
    }

    /**
     * @param entree modifie la valeur de entree
     */
    public void setEntre(Sommet entre) {
        this.entree = entre;
    }

    /**
     * @return la valeur de sortie
     */
    public Sommet getSortie() {
        return sortie;
    }

    /**
     * @param sortie modifie la valeur de sortie
     */
    public void setSortie(Sommet sortie) {
        this.sortie = sortie;
    }
    
    /** */
    public int getHauteur() {
        return hauteur;
    }
    
    /** */
    public int getLargeur() {
        return largeur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Labyrinthe other = (Labyrinthe) obj;
        return Objects.equals(entree, other.entree) && hauteur == other.hauteur && largeur == other.largeur
                && Arrays.deepEquals(listeArcs, other.listeArcs) && Arrays.deepEquals(listeSommet, other.listeSommet)
                && Objects.equals(sortie, other.sortie);
    }

    
}