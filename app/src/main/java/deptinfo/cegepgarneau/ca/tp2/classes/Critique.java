package deptinfo.cegepgarneau.ca.tp2.classes;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Andrey on 28/03/2016.
 */

public class Critique implements Serializable{

    // Variables
    public static final int ID_UNDEFINED = -1;
    public int              id = ID_UNDEFINED;

    // Variables qui varies.
    public String           user = "n/a";
    public int              ouvr;
    public String           diff = "n/a";
    public Date             date;
    public int              piste;
    // Constructeur
    public Critique(String user, int ouvr, String diff, Date date,int piste){
        this.user = user;
        this.ouvr = ouvr;
        this.diff = diff;
        this.date = date;
        this.piste = piste;
    }

    // Index
    public int GetID(){return this.id;}
    public void SetID(int id){this.id = id;}

    public String GetUser(){return this.user ;}
    public void SetUser(String user){this.user = user;}

    public int GetOuvreur(){return this.ouvr;}
    public void SetOuvreur(int ouvr){this.ouvr = ouvr;}

    public String GetDiff(){return this.diff ;}
    public void SetDiff(String diff){this.diff = diff;}

    public Date GetDate(){return this.date ;}
    public void SetDate(Date date){this.date = date;}

    public int GetPiste(){return this.piste ;}
    public void SetPiste(int piste){this.piste = piste;}

}
