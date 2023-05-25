package lecture;

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

public class Lecture {
    public static void main(final String[] args) {
       
        ArrayList<Sommet> sommetsValidesSansDoublon; 
        sommetsValidesSansDoublon = new ArrayList<>(10);
        
        /*  0 */sommetsValidesSansDoublon.add(new Sommet(0,0));
        /*  1 */sommetsValidesSansDoublon.add(new Sommet(1,0));
        /*  2 */sommetsValidesSansDoublon.add(new Sommet(2,0));
        /*  3 */sommetsValidesSansDoublon.add(new Sommet(3,0));
        /*  4 */sommetsValidesSansDoublon.add(new Sommet(4,0));
        /*  5 */sommetsValidesSansDoublon.add(new Sommet(0,1));
        /*  6 */sommetsValidesSansDoublon.add(new Sommet(0,2));
        /*  7 */sommetsValidesSansDoublon.add(new Sommet(0,3));
        /*  8 */sommetsValidesSansDoublon.add(new Sommet(0,4));
        /*  9 */sommetsValidesSansDoublon.add(new Sommet(1,1));
        /* 10 */sommetsValidesSansDoublon.add(new Sommet(2,2));
        /* 11 */sommetsValidesSansDoublon.add(new Sommet(3,3));
        /* 12 */sommetsValidesSansDoublon.add(new Sommet(4,4));
        
        
        // ECRITURE DANS UN FICHIER JSON
        
        // faut convertir l'arraylist en liste json
        JsonArray listeSommets = new Gson().toJsonTree(sommetsValidesSansDoublon).getAsJsonArray();
        
        // on fait un nouvel objet 
        JsonObject sommets = new JsonObject();
        
        // on met sommet en cle et la liste en valeur
        // (car  plus facile pour rajouter les arcs apres)
        sommets.add("sommets", listeSommets);
        
        // on fait un objet gson fait pour etre ecrit
        // (pretty printing c'est pour les indentations)
        Gson test = new GsonBuilder().setPrettyPrinting().create();
        
        // l'ouverture se fait comme avec un with open() en python
        // donc pas besoin de 'close' le fichier
        try (FileWriter fichier = new FileWriter("src/lecture/oui.json")) {
            // on ecrrit l'objet sommets dans le fichier souhaite
            test.toJson(sommets, fichier);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
  
        // LECTURE D'UN FICHIER JSON
        
        File input = new File("src/lecture/oui.json"); // le fichier a lire
        
        try {
            // on parse le fichier avec la classe adaptee
            JsonElement fichier = JsonParser.parseReader(new FileReader(input));
            
            // on recupere le contenu du fichier dans un objet json
            JsonObject testObject = fichier.getAsJsonObject();
            
            // on recupere une valeur (ici une liste) avec une cle (ici sommets)
            JsonArray listeSommet = testObject.get("sommets").getAsJsonArray();
            
            for (JsonElement s : listeSommet) {
                
                // on recupere un objet "simple"
                JsonObject sommet = s.getAsJsonObject();
                
                int x = sommet.get("posX").getAsInt(); 
                int y = sommet.get("posY").getAsInt();
                
                // et PAF ca fait des chocapics
                Sommet a = new Sommet(x, y); 
                
                System.out.println(a);
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
       
      }
}
