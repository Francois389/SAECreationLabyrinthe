/**
 * OutilsMatrice.java                       16 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package outils;


/**
 * outils sur les matrices
 * @author de Saint Palais François
 *
 */
public class OutilsMatrice {
 
 	/**
  	 * Vérifie si une colonne est false
  	 * @param indice de la colonne à vérifier
  	 * @return matrice avec la ligne et colonne en moins
  	 */
  	public static boolean colonneVide(boolean[][] matrice, int indiceColonne) {
  	    for (int j = 0; j < matrice.length; j++) {
  	        if (matrice[j][indiceColonne] != false) {
  	            return false;
  	        }
  	    }
  	    return true;
  	}
  	
  	/**
  	 * Vérifie si une colonne est nul.
  	 * @param indice de la colonne à vérifier
  	 * @return matrice avec la ligne et colonne en moins
  	 */
  	public static boolean colonneVide(int[][] matrice, int indiceColonne) {
        for (int j = 0; j < matrice.length; j++) {
            if (matrice[j][indiceColonne] != 0) {
                return false;
            }
        }
        return true;
   	}

  	/**
  	 * @param matrice
  	 * @return true si la matrice est nul false sinon
  	 */
  	public static boolean estNul(boolean[][] matrice) {
  	    for (boolean[] ligne : matrice) {
            for (boolean element : ligne) {
                if (element) {
                    return false;
                }
            }
        }
  	    
  	    return true;
  	}
   	
   	/**
 	 * Vérifie si une matrice est nulle donc ne contient que des 0
 	 * @param matrice 
 	 * @return true si vide false sinon
 	 */
 	public static boolean estNulle(int[][] matrice) {
   		// STUB
 	    for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] != 0) {
                    return false;
                }
            }
        }
   		return true;
  	}

  	/**
  	 * Vérifie si une ligne est false
  	 * @param indice de la ligne à vérifier
  	 * @return matrice avec la ligne et colonne en moins
  	 */
  	public static boolean ligneVide(boolean[][] matrice, int indiceLigne) {
  	    for (int j = 0; j < matrice[indiceLigne].length; j++) {
  	        if (matrice[indiceLigne][j] != false) {
  	            return false;
  	        }
  	    }
  	    return true;
  	}
   	
   	/**
  	 * Vérifie si une ligne est nul
  	 * @param indice de la ligne à vérifier
  	 * @return matrice avec la ligne et colonne en moins
  	 */
  	public static boolean ligneVide(int[][] matrice, int indiceLigne) {
  	    for (int j = 0; j < matrice[indiceLigne].length; j++) {
            if (matrice[indiceLigne][j] != 0) {
                return false;
            }
        }
    	return true;
   	}

  	/**
  	 * Supprime la ligne et la colonne d'indice i dans la matrice
  	 * @param indice de la ligne/colonne a supprimer
  	 * @param matrice initial où il faut retirer une ligne et colonne
  	 * @return matrice avec la ligne et colonne en moins
  	 */
  	public static boolean[][] supLigneColonne(boolean[][] matrice, int indice) {
  	    boolean[][] matriceResultat = new boolean[matrice.length - 1][matrice[0].length - 1];
  	    for (int i = 0; i < matrice.length; i++) {
  	        for (int j = 0; j < matrice[i].length; j++) {
  	            if (i != indice && j != indice) {
  	                matriceResultat[i][j] = matrice[i][j];
  	            }
  	        }
  	    }
  	    return matriceResultat;
  	}
  	
  	/**
  	 * Supprime la ligne et la colonne d'indice i dans la matrice
     * @param indice de la ligne/colonne a supprimer
     * @param matrice initial où il faut retirer une ligne et colonne
  	 * @return matrice avec la ligne et colonne en moins
  	 */
  	public static int[][] supLigneColonne(int[][] matrice, int indice) {
  	  int[][] matriceResultat = new int[matrice.length - 1][matrice[0].length - 1];
      for (int i = 0; i < matrice.length; i++) {
          for (int j = 0; j < matrice[i].length; j++) {
              if (i != indice && j != indice) {
                  matriceResultat[i][j] = matrice[i][j];
              }
          }
      }
      return matriceResultat;
   	}
  	
  	public static String matriceToString(Object[][] matrice) {
  	    String chaine = "[";
  	    chaine += OutilsListe.tabVersString(matrice[0]) + ",\n";
  	    for (int i = 1; i < matrice.length - 1; i++) {
            chaine += OutilsListe.tabVersString(matrice[i]) + ",\n ";
        }
  	    chaine += OutilsListe.tabVersString(matrice[matrice.length-1]) + "]";
  	    return chaine;
  	}

    public static String matriceToString(boolean[][] matrice) {
        String chaine = "[";
        chaine += OutilsListe.tabVersString(matrice[0]) + ",\n";
        for (int i = 1; i < matrice.length - 1; i++) {
            chaine += OutilsListe.tabVersString(matrice[i]) + ",\n ";
        }
        chaine += OutilsListe.tabVersString(matrice[matrice.length-1]) + "]";
        return chaine;
    }

}
