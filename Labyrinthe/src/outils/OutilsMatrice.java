/**
 * OutilsMatrice.java                       16 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package outils;

/**
 * outils sur les matrices
 * @author francois.desaintpala
 *
 */
public class OutilsMatrice {
 
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
                    return true;
                }
            }
        }
   		return false;
  	}
  	
  	/**
  	 * supprime la ligne i et la colonne i d'une matrice
  	 * @param indice de la ligne/colonne a supprimer
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
   	
   	/**
  	 * Vérifie si une colonne est nulle
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
  	 * Vérifie si une ligne est nulle
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

}
