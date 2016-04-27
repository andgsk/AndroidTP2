package deptinfo.cegepgarneau.ca.tp2.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.wearable.internal.ChannelSendFileResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */
public class PisteDataSource {

    // PROPRIETEES DE LA TABLE
    private final static int        DB_VERSION = 3;
    private final static String     TABLE_NAME = "pistes";

    private final static String     COL_ID = "_id";
    private final static String     COL_TYPE = "type";
    private final static String     COL_USERNAME = "username";
    private final static String     COL_NOM = "nom";
    private final static String     COL_DESCRIPTION = "description";
    private final static String     COL_DIFFICULTE = "difficulte";
    private final static String     COL_DATECREATION = "date";
    private final static String     COL_ACTIF = "actif";

    private final static int        IDX_ID = 0;
    private final static int        IDX_TYPE = 1;
    private final static int        IDX_USERNAME = 2;
    private final static int        IDX_NOM = 3;
    private final static int        IDX_DESCRIPTION = 4;
    private final static int        IDX_DIFFICULTE = 5;
    private final static int        IDX_DATECREATION = 6;
    private final static int        IDX_ACTIF = 7;

    private PisteDBHelper           m_dbHelper;
    private SQLiteDatabase          m_db;

    // CONSTRUCTEUR
    public PisteDataSource(Context context){
        m_dbHelper = new PisteDBHelper(context);
    }

    // Ouvre la connexion a la DB
    public void open(){
        m_db = m_dbHelper.getWritableDatabase();
    }

    // Ferme la connexion a la DB
    public void close(){
        m_db.close();
    }

    // Cursor vers une piste.
    private Piste CursorToPiste(Cursor c){
        Piste piste = new Piste(
                c.getString(IDX_NOM),
                c.getInt(IDX_TYPE),
                c.getString(IDX_USERNAME),
                c.getString(IDX_DESCRIPTION),
                c.getString(IDX_DIFFICULTE));
        piste.SetID(c.getInt(IDX_ID));
        piste.SetDate(new Date(c.getInt(IDX_DATECREATION)));
        if (c.getInt(IDX_ACTIF) < 0)
            piste.SetActif(false);

        return piste;
    }

    // Permet de recuperer selon le type.
    public List<Piste> GetAllPistesType(int type){
        List<Piste> list = new ArrayList<Piste>();
        String[] args = new String[]{String.valueOf(type)};

        Cursor c = m_db.query(TABLE_NAME, null, COL_TYPE + "=?", args, null, null, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Piste piste = CursorToPiste(c);
            list.add(piste);
            c.moveToNext();
        }

        return list;
    }

    // Permet de recuperer tout les users.
    public List<Piste> GetAllPistes(){
        List<Piste> list = new ArrayList<Piste>();

        Cursor c = m_db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Piste piste = CursorToPiste(c);
            list.add(piste);
            c.moveToNext();
        }

        return list;
    }

    // Fonction qui ajoute un nouvelle utilisateur dans la base de donnees.
    public Piste Insert(Piste piste){
        ContentValues row = PisteToContentValue(piste);


            row.put("difficulte", piste.GetDifficulte());
            row.put("actif", piste.EstActif());
            row.put("description", piste.GetDescription());
            row.put("username",piste.GetOuvreurUsername());
            row.put("nom", piste.GetNom());
            row.put("type", piste.GetType());

        //int newId = (int)
         m_db.insert(TABLE_NAME, null, row);
        //piste.SetID(newId);
        return piste;
    }

    // Fonction qui convertie les valeurs user en content value pour la base de donnees.
    private ContentValues PisteToContentValue(Piste piste){
        ContentValues row = new ContentValues();
        //row.put(COL_ID, piste.GetID());
        row.put(COL_TYPE, piste.GetType());
        row.put(COL_NOM, piste.GetNom());
        row.put(COL_USERNAME, piste.GetOuvreurUsername());
        row.put(COL_DESCRIPTION, piste.GetDescription());
        row.put(COL_DIFFICULTE, piste.GetDifficulte());
        row.put(COL_DATECREATION, piste.GetDate().getTime());
        if (piste.EstActif())
            row.put(COL_ACTIF, 1);
        else
            row.put(COL_ACTIF, 0);

        return row;
    }

    //
    // HELPER DB, NE PAS VRAIMENT TOUCHER.
    //
    private static class PisteDBHelper extends SQLiteOpenHelper{
        public PisteDBHelper(Context context){
            super(context, "pistes.sqlite", null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME
                    + "(" + COL_ID + " INTEGER PRIMARY KEY   AUTOINCREMENT, "
                    + COL_TYPE + " integer, "
                    + COL_USERNAME + " text, "
                    + COL_NOM + " text, "
                    + COL_DESCRIPTION + " text, "
                    + COL_DIFFICULTE + " text, "
                    + COL_DATECREATION + " integer, "
                    + COL_ACTIF + " integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            this.onCreate(db);
        }
    }
}