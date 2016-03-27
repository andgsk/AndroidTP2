package deptinfo.cegepgarneau.ca.tp2.classes;

import java.io.Serializable;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */

public class Demande implements Serializable{

    // Variables
    public static final int ID_UNDEFINED = -1;
    public int              id = ID_UNDEFINED;

    // Variables qui varies.
    public String           username;
    public String           password;
    public int              typeCompte;
    public String           prenom = "n/a";
    public String           nom = "n/a";
    public String           email = "n/a";
    public String           adresse = "n/a";
    public String           noTelephone = "555-123-4567";

    // Type de comptes
    public static final int TYPE_GRIMPEUR = 0;
    public static final int TYPE_OUVREUR = 1;

    // Constructeur
    public Demande(String username, String password, int typeCompte){
        this.username = username;
        this.password = password;
        this.typeCompte = typeCompte;
    }

    // Index
    public int GetID(){return this.id;}
    public void SetID(int id){this.id = id;}

    // Type compte
    public int GetTypeCompte(){return this.typeCompte;}
    public void SetTypeCompte(int typeCompte){this.typeCompte = typeCompte;}

    // Username
    public String GetUsername(){return this.username;}
    public void SetUsername(String username){this.username = username;}

    // Password
    public String GetPassword(){return this.password;}
    public void SetPassword(String password){this.password = password;}

    // Prenom
    public String GetPrenom(){return this.prenom;}
    public void SetPrenom(String prenom){this.prenom = prenom;}

    // Nom
    public String GetNom(){return this.nom;}
    public void SetNom(String nom){this.nom = nom;}

    // Email
    public String GetEmail(){return this.email;}
    public void SetEmail(String email){this.email = email;}

    // Adresse
    public String GetAdresse(){return this.adresse;}
    public void SetAdresse(String adresse){this.adresse = adresse;}

    // Numero de telephone
    public String GetNoTelephone(){return this.noTelephone;}
    public void SetNoTelephone(String noTelephone){this.noTelephone = noTelephone;}
}
