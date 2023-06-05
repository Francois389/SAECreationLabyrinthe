/*
 * LabyrintheJson.java                                   25 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package sauvegarde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import application.Jeux;
import representation.Sommet;

/**
 * 
 * //TODO Commenter la responsabilités de la classe LabyrintheJson
 * @author Denamiel Clément
 *
 */
public class LabyrintheJson {

    private final static String CHEMIN_FICHIER = "src/sauvegardes/labyrinthe.json";
    
    private static Sommet getSommetFromJSON(JsonElement element) {
        JsonObject sommet = element.getAsJsonObject();
        
        int x = sommet.get("posX").getAsInt(); 
        int y = sommet.get("posY").getAsInt();
        int marque = sommet.get("marque").getAsInt();
        boolean estParcouru = sommet.get("estParcourus").getAsBoolean();
        boolean[] voisins = new boolean[4];
        
        JsonArray listeVoisins = sommet.get("voisins").getAsJsonArray();
        
        for (int i = 0; i < listeVoisins.size(); i++) {
            JsonElement elt = listeVoisins.get(i);
            voisins[i] = elt.getAsBoolean();
        }
        
        Sommet s = new Sommet(x, y); 
        s.setEstParcourus(estParcouru);
		s.setMarque(marque);
		s.setVoisin(voisins);
		        
        return s;
    }
    
    /**
     * TODO Commenter
     * @param laby
     */
    public static void enregistrerLabyrinthe(Jeux laby) {
        
        
        ArrayList<Sommet> listeSommetsLabyrinthe = new ArrayList<>(10);
        ArrayList<Sommet[]> listeArcsLabyrinthe  = new ArrayList<>(10);
        
        for (Sommet[] listeSommetsTemp : laby.getListeSommet()) {
            for (Sommet s : listeSommetsTemp) {
                listeSommetsLabyrinthe.add(s);
            }
        }
        
        for (Sommet[] arcs : laby.getListeArcs()) {
            listeArcsLabyrinthe.add(arcs);
        }
     
        JsonArray listeSommets = new Gson().toJsonTree(listeSommetsLabyrinthe).getAsJsonArray();
        JsonArray listeArcs    = new Gson().toJsonTree(listeArcsLabyrinthe).getAsJsonArray();
       
        JsonElement hauteur = new Gson().toJsonTree(laby.getHauteur());
        JsonElement largeur = new Gson().toJsonTree(laby.getLargeur());
        
        JsonElement entree = new Gson().toJsonTree(laby.getEntre());
        JsonElement sortie = new Gson().toJsonTree(laby.getSortie());
        
        JsonElement joueurPosX = new Gson().toJsonTree(laby.getPosXJoueur());
        JsonElement joueurPosY = new Gson().toJsonTree(laby.getPosYJoueur());
        
        
        JsonObject labyrintheJSON = new JsonObject();
        labyrintheJSON.add("hauteur", hauteur);
        labyrintheJSON.add("largeur", largeur);
        labyrintheJSON.add("entree", entree);
        labyrintheJSON.add("sortie", sortie);
        labyrintheJSON.add("sommets", listeSommets);
        labyrintheJSON.add("arcs", listeArcs);
        labyrintheJSON.add("posXJoueur", joueurPosX);
        labyrintheJSON.add("posYJoueur", joueurPosY);
     
        Gson test = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter fichier = new FileWriter(CHEMIN_FICHIER)) {
            test.toJson(labyrintheJSON, fichier);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static Jeux chargerLabyrinthe() {
        
        File fichierLabyrinthe = new File(CHEMIN_FICHIER);
        
        
        try {
            JsonElement fichier = JsonParser.parseReader(new FileReader(fichierLabyrinthe));
            
            JsonObject objetLabyrinthe = fichier.getAsJsonObject();
            
            int hauteur = objetLabyrinthe.get("hauteur").getAsInt();
            int largeur = objetLabyrinthe.get("largeur").getAsInt();

            int joueurPosX = objetLabyrinthe.get("posXJoueur").getAsInt();
            int joueurPosY = objetLabyrinthe.get("posYJoueur").getAsInt();
            
            JsonArray listeSommet = objetLabyrinthe.get("sommets").getAsJsonArray();
            JsonArray listeArcs = objetLabyrinthe.get("arcs").getAsJsonArray();
            
            Sommet[][] tableauSommet = new Sommet[hauteur][largeur];
            Sommet[][] tableauArcs   = new Sommet[listeArcs.size()][2];
            
            Sommet entree = getSommetFromJSON(objetLabyrinthe.get("entree"));
            Sommet sortie = getSommetFromJSON(objetLabyrinthe.get("sortie"));
            
            
            for (JsonElement element : listeSommet) {
                
                Sommet s = getSommetFromJSON(element);
                
                tableauSommet[s.getPosY()][s.getPosX()] = s;
                                
            }
            
            int indiceTableauArcs = 0;
            for (JsonElement sommets : listeArcs) {
                
                for (int i = 0; i < sommets.getAsJsonArray().size(); i++) {
                    JsonElement element = sommets.getAsJsonArray().get(i);
                    
                    Sommet s = getSommetFromJSON(element);

                    tableauArcs[indiceTableauArcs][i] = s;
                }
                
                indiceTableauArcs++;
                
            }
            
            Jeux charge = new Jeux(
                    hauteur, largeur, tableauSommet, tableauArcs, entree, 
                    sortie, joueurPosX, joueurPosY);
            
            return charge;
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        return null; //stub
        
    }

}
