/*
 * //TODO comentez
 */
package lecture;

import representation.Labyrinthe;
import representation.Sommet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;

/**
 * 
 * //TODO Commenter la responsabilités de la classe LabyrintheJson
 * @author Lenovo
 *
 */
public class LabyrintheJson {

    /**
     * //TODO commentez
     * @return
     */
    private static Labyrinthe getLabyrinthe() {
        Labyrinthe retour = new Labyrinthe(5,7);
        retour.ajouterArrete(retour.listeSommet[0][0], retour.listeSommet[0][1]);
        retour.ajouterArrete(retour.listeSommet[0][1], retour.listeSommet[0][2]);
        retour.ajouterArrete(retour.listeSommet[0][2], retour.listeSommet[0][3]);
        retour.ajouterArrete(retour.listeSommet[0][2], retour.listeSommet[1][2]);
        retour.ajouterArrete(retour.listeSommet[0][3], retour.listeSommet[0][4]);
        retour.ajouterArrete(retour.listeSommet[0][5], retour.listeSommet[0][6]);
        retour.ajouterArrete(retour.listeSommet[0][5], retour.listeSommet[1][5]);
        retour.ajouterArrete(retour.listeSommet[1][0], retour.listeSommet[1][1]);
        retour.ajouterArrete(retour.listeSommet[1][1], retour.listeSommet[1][2]);
        retour.ajouterArrete(retour.listeSommet[1][3], retour.listeSommet[1][4]);
        retour.ajouterArrete(retour.listeSommet[1][5], retour.listeSommet[1][6]);
        retour.ajouterArrete(retour.listeSommet[1][0], retour.listeSommet[2][0]);
        retour.ajouterArrete(retour.listeSommet[1][2], retour.listeSommet[2][2]);
        retour.ajouterArrete(retour.listeSommet[2][1], retour.listeSommet[3][1]);
        retour.ajouterArrete(retour.listeSommet[1][4], retour.listeSommet[2][4]);
        retour.ajouterArrete(retour.listeSommet[1][3], retour.listeSommet[2][3]);
        retour.ajouterArrete(retour.listeSommet[3][1], retour.listeSommet[3][2]);
        retour.ajouterArrete(retour.listeSommet[3][2], retour.listeSommet[3][3]);
        retour.ajouterArrete(retour.listeSommet[2][0], retour.listeSommet[3][0]);
        retour.ajouterArrete(retour.listeSommet[2][3], retour.listeSommet[3][3]);
        retour.ajouterArrete(retour.listeSommet[1][5], retour.listeSommet[2][5]);
        retour.ajouterArrete(retour.listeSommet[1][6], retour.listeSommet[2][6]);
        retour.ajouterArrete(retour.listeSommet[2][4], retour.listeSommet[3][4]);
        retour.ajouterArrete(retour.listeSommet[4][0], retour.listeSommet[3][0]);
        retour.ajouterArrete(retour.listeSommet[4][0], retour.listeSommet[4][1]);
        retour.ajouterArrete(retour.listeSommet[4][1], retour.listeSommet[4][2]);
        retour.ajouterArrete(retour.listeSommet[4][2], retour.listeSommet[4][3]);
        retour.ajouterArrete(retour.listeSommet[4][3], retour.listeSommet[3][3]);
        retour.ajouterArrete(retour.listeSommet[4][4], retour.listeSommet[4][5]);
        retour.ajouterArrete(retour.listeSommet[4][4], retour.listeSommet[3][4]);
        retour.ajouterArrete(retour.listeSommet[4][5], retour.listeSommet[4][6]);
        retour.ajouterArrete(retour.listeSommet[4][6], retour.listeSommet[3][6]);
        retour.ajouterArrete(retour.listeSommet[3][4], retour.listeSommet[3][5]);
        retour.ajouterArrete(retour.listeSommet[2][5], retour.listeSommet[3][5]);
        retour.listeSommet[0][0].setVoisin(true, 3);
        retour.listeSommet[2][6].setVoisin(true, 1);
        retour.setEntre(retour.listeSommet[0][0]);
        retour.setSortie(retour.listeSommet[2][6]);
        
        return retour;
    }
    
    private static Sommet getSommetFromJSON(JsonElement element) {
        JsonObject sommet = element.getAsJsonObject();
        
        int x = sommet.get("posX").getAsInt(); 
        int y = sommet.get("posY").getAsInt();
        int marque = sommet.get("marque").getAsInt();
        boolean[] voisins = new boolean[4];
        
        JsonArray listeVoisins = sommet.get("voisins").getAsJsonArray();
        
        for (int i = 0; i < listeVoisins.size(); i++) {
            JsonElement elt = listeVoisins.get(i);
            voisins[i] = elt.getAsBoolean();
        }
        
        Sommet s = new Sommet(x, y); 
        
        return s;
    }
    
    public static void enregistrerLabyrinthe(Labyrinthe laby) {
        
        
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
        
        JsonObject labyrintheJSON = new JsonObject();
        labyrintheJSON.add("hauteur", hauteur);
        labyrintheJSON.add("largeur", largeur);
        labyrintheJSON.add("entree", entree);
        labyrintheJSON.add("sortie", sortie);
        labyrintheJSON.add("sommets", listeSommets);
        labyrintheJSON.add("arcs", listeArcs);
     
        Gson test = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter fichier = new FileWriter("src/lecture/labyrinthe.json")) {
            test.toJson(labyrintheJSON, fichier);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static Labyrinthe chargerLabyrinthe() {
        
        File fichierLabyrinthe = new File("src/lecture/labyrinthe.json");
        
        
        try {
            JsonElement fichier = JsonParser.parseReader(new FileReader(fichierLabyrinthe));
            
            JsonObject objetLabyrinthe = fichier.getAsJsonObject();
            
            int hauteur = objetLabyrinthe.get("hauteur").getAsInt();
            int largeur = objetLabyrinthe.get("largeur").getAsInt();
            
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
            
            Labyrinthe charge = new Labyrinthe(
                    hauteur, largeur, tableauSommet, tableauArcs, entree, sortie);
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        return getLabyrinthe(); // stub
        
        
    }
    
    
    public static void main(String[] args) {
        Labyrinthe l = getLabyrinthe();
        
        enregistrerLabyrinthe(l);
        
        Labyrinthe charge = chargerLabyrinthe();
        System.out.println(charge);
    }
}
