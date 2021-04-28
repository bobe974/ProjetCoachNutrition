package com.example.projetcoachnutrition.Controler;
import android.content.Context;
import android.util.Log;

import com.example.projetcoachnutrition.Bdd.AccesLocal;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.Repas;
import com.example.projetcoachnutrition.Modele.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;


public class Controle {

    //propriété
    //permet de mémoriser l'instance (controle)
    private static Controle instance = null;
    private static Aliment aliment;
    private static User user;
    private static Repas repas;
    private static AccesLocal accesLocal; //acces a la bdd

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
    public void creerAliment(String nom, int nbcalories){
        int id = 99999;
        aliment = new Aliment(id,nom, nbcalories);
        accesLocal.ajoutAliment(aliment);
    }

    /**
     * Créer un utilisateur
     * @param nom
     * @param age
     * @param poids
     * @param taille
     * @param sexe
     */
    public void creerUser(String nom, int age, float poids, int taille, int sexe)
    {
        int iduser = 9999;
        user = new User(iduser,nom,age,poids,taille,sexe);
        accesLocal.ajoutUser(user);
    }

    /**
     * créer un repas qui contient des aliments et une date
     * @param lesAliments
     */
    public void creerRepas(ArrayList<Aliment> lesAliments, int qte){
        int id = 99999;
        repas = new Repas(id,new Date(),lesAliments, qte);
        accesLocal.ajoutRepas(repas);

        /*************TEST****************
         int[] tab = repas.getAllId();

         for(int a = 0; a<tab.length;a++){
         Log.d("LES ID", "******************************: "+ tab[a]);
         }
         */

    }

    /**
     * retourne tout les profils
     * @return
     */
    public  List<User> loadUser() {
        Log.d("TAG", "************************loaduser: ");
        List<User> allUser = accesLocal.getAllUser();
        for(User u : allUser){
            Log.d("TAG", "LOADUSER LISTE: "+ u.getNom());
        }
        return allUser;
    }

    /**
     * retourne le dernier profil
     * @return
     */
    public  User loadLastUser() {

        User lastUser = accesLocal.recupDernierUser();
        Log.d("TAG", "************************loadLastUser: "+lastUser.getNom());
        return lastUser;
    }


    public boolean verifUserExistant(){
        boolean unCompteExiste = accesLocal.utilisateurExistant();
        Log.d("TAG", "************************VerifUser: "+unCompteExiste);
        return unCompteExiste;
    }

    /**
     * recupere tout les aliment depuis la base
     *
     * @return
     */
    public List<Aliment> loadAliment(){
        List<Aliment> allAliment = accesLocal.getAllAliments();
        return allAliment;
    }

    public void updateFoodToDatabase(int id, int calories){
        accesLocal.updateAliment(id,calories);
    }

    public void deleteFoodToDatabase(int id){
        accesLocal.deleteAliment(id);
    }

    /**
     *recupere l'historique de tout les repas
     */
    public List<Repas>loadMeal(){

        List<Repas> allRepas = accesLocal.getAllRepas();
        /****************testtt***********/
        for (Repas unrepas: allRepas){
            Log.d("TAG", "********loadMeal*********: "+unrepas.getSdate()+"Calories"+ unrepas.getCalories());
        }
        return allRepas;
    }

    public void deleteRepasToDatabse(int id){ accesLocal.deleteRepas(id);}

}