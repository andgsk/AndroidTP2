package deptinfo.cegepgarneau.ca.tp2.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */

public class Piste implements Serializable{

    // Variables
    public static final int ID_UNDEFINED = -1;
    public int              id = ID_UNDEFINED;

    // Variables qui varies.
    private String           nom;
    private int              type;
    private String           ouvreurUsername;
    private String           description;
    private String           difficulte = "5.5";
    private Date             dateCreation;
    private boolean          actif = true;

    // Type de comptes
    public static final int TYPE_BLOC = 0;
    public static final int TYPE_VOIE = 1;

    // Constructeur
    public Piste(String nom, int type, String username, String description, String diff){
        this.nom = nom;
        this.type = type;
        this.ouvreurUsername = username;
        this.description = description;
        this.difficulte = diff;
        this.dateCreation = new Date();
    }


    // Index
    public int GetID(){return this.id;}
    public void SetID(int id){this.id = id;}

    // Le nom de la piste.
    public String GetNom(){return this.nom;}
    public void SetNom(String nom){this.nom = nom;}

    // Type de piste.
    public int GetType(){return this.type;}
    public void SetType(int type){this.type = type;}

    // Nom d'utilisateur de l'ouvreur.
    public String GetOuvreurUsername(){return this.ouvreurUsername;}
    public void SetOuvreurUsername(String username){this.ouvreurUsername = username;}

    // La description de la piste.
    public String GetDescription(){return this.description;}
    public void SetDescription(String description){this.description = description;}

    // La difficulte de la piste.
    public String GetDifficulte(){return this.difficulte;}
    public void SetDifficulte(String difficulte){this.difficulte = difficulte;}

    // La date de creation.
    public Date GetDate(){return this.dateCreation;}
    public void SetDate(Date date){this.dateCreation = date;}

    // Verification de letat de la piste.
    public Boolean EstActif(){return this.actif;}
    public void SetActif(boolean actif){this.actif = actif;}
}
