package deptinfo.cegepgarneau.ca.tp2.classes;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */
public class Utilisateur {
    public final int    ID_UNDEFINED = -1;
    public int          id = ID_UNDEFINED;
    public int          typeCompte;
    public String       username;
    public String       password;
    public String       prenom;
    public String       nom;
    public String       email;
    public String       adresse;
    public int          noTelephone;

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
    public int GetNoTelephone(){return this.noTelephone;}
    public void SetNoTelephone(int noTelephone){this.noTelephone = noTelephone;}
}
