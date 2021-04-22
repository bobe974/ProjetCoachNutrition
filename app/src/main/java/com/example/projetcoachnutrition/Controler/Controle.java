package com.example.projetcoachnutrition.Controler;
import android.content.Context;

import com.example.projetcoachnutrition.Bdd.AccesLocal;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.R;

import java.util.Date;

public class Controle {

   //propriété
    //permet de mémoriser l'instance (controle)
    private static Controle instance = null;
    private static Aliment aliment;
    //private static Profil profil; //declare pour utiliser les methodes et pp de profil
    private static String nomFic = "saveprofil"; //fichier ou on va serialiser un profil
    private static AccesLocal accesLocal; //acces a la bdd
    //private static AccesDistant accesDistant;

    /**
     * constructeur prive , peut pas etre instancier
     */
    private Controle() {

        super(); //appelle rien (sauf classe Object)
    }

    /**
     * Singleton
     * permet d'instancier une seule fois un controleur
     * permet de générer un controleur si il y en a pas
     *
     * @return instance
     */
    public static final Controle getInstance(Context contexte) {
        if (Controle.instance == null) { //si null alors créer instance

            Controle.instance = new Controle(); //n'est pas accessible de l'extérieur
            accesLocal = new AccesLocal(contexte); //accede a la bdd
            //profil = accesLocal.recupDernier();  // recupere le dernier profil
        }
        return Controle.instance;
    }

    /**
     *  créer un aliment et l'ajoute dans la bdd
     * @param nom
     * @param nbcalories
     */
    public void creerAliment( String nom, int nbcalories){
        aliment = new Aliment(nom, nbcalories);
        accesLocal.ajoutAliment(aliment);

    }

    /**
     * Créer un utilisateur
     * @param nom
     * @param age
     * @param poids
     * @param taille
     * @param sexe
     * @param minCalories
     * @param maxCalories
     */
    public void creerUser(String nom,String age,float poids,int taille,int sexe, int minCalories, int maxCalories){

    }
    /**
     * créer un nouveau profil
     *
     * @param poids
     * @param age
     * @param taille
     * @param sexe   1 pour homme et 0 pour femme
     */ /*
    public void creerProfil(Integer poids, Integer age, Integer taille, Integer sexe, Context contexte) { // un contexte nécéssaire pour la méthode

        profil = new Profil(poids, age, taille, sexe, new Date());   //new date genere la date actuelle
        accesLocal.ajout(profil); //ajout du profil dans la bdd

    }*/
}
