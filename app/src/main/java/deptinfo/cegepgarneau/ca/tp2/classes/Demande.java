package deptinfo.cegepgarneau.ca.tp2.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrey on 20/03/2016.
 */

public class Demande implements Serializable{

    // Variables
    public static final int ID_UNDEFINED = -1;
    public int              id = ID_UNDEFINED;

    // Variables qui varies.
    public String           username;
    public String           niveau;
    public int              type;
    public Date             date;

    // Type de demandes
    public static final int TYPE_ACC = 0;
    public static final int TYPE_NONACC = 1;

    // Constructeur
    public Demande(String username, String niveau){
        this.username = username;
        this.niveau = niveau;
        this.type = TYPE_ACC;
        this.date = new Date();
    }

    // Index
    public int GetID(){return this.id;}
    public void SetID(int id){this.id = id;}

    // Type compte
    public int GetType(){return this.type;}
    public void SetType(int type){this.type = type;}

    // Username
    public String GetUsername(){return this.username;}
    public void SetUsername(String username){this.username = username;}

    // Niveau
    public String GetNiveau(){return this.niveau;}
    public void SetNiveau(String niveau){this.niveau = niveau;}

    // Date
    public Date GetDate(){return this.date;}
    public void SetDate(Date date){this.date = date;}

}
