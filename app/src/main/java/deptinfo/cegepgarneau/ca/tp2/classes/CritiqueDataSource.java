package deptinfo.cegepgarneau.ca.tp2.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */
public class CritiqueDataSource {

    // PROPRIETEES DE LA TABLE
    private final static int        DB_VERSION = 1;
    private final static String     TABLE_NAME = "critiques";

    private final static String     COL_ID = "_id";
    private final static String     COL_USER = "user";
    private final static String     COL_OUVR = "ouvr";
    private final static String     COL_DIFF = "diff";
    private final static String     COL_DATE = "date";
    private final static String     COL_PISTE = "piste";

    private final static int        IDX_ID = 0;
    private final static int        IDX_USER = 1;
    private final static int        IDX_OUVR = 2;
    private final static int        IDX_DIFF = 3;
    private final static int        IDX_DATE = 4;
    private final static int        IDX_PISTE = 5;

    private CritiqueDBHelper     m_dbHelper;
    private SQLiteDatabase          m_db;

    // CONSTRUCTEUR
    public CritiqueDataSource(Context context){
        m_dbHelper = new CritiqueDBHelper(context);
    }

    // Ouvre la connexion a la DB
    public void open(){
        m_db = m_dbHelper.getWritableDatabase();
    }

    // Ferme la connexion a la DB
    public void close(){
        m_db.close();
    }

    // Cursor vers une critique.
    private Critique CursorToCritique(Cursor c) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.FRENCH);
        String test = c.getString(IDX_DATE);
        Date uneD = format.parse(test);

        Critique crit = new Critique(
                c.getString(IDX_USER),
                c.getInt(IDX_OUVR),
                c.getString(IDX_DIFF),
                (java.sql.Date)uneD,
                c.getInt(IDX_PISTE));
        return crit;
    }

    // Permet de recuperer un utilisateur par son ID.
    public Critique GetCritiqueByID(int id) throws ParseException {
        Critique crit = null;
        String[] args = new String[]{String.valueOf(id)};

        Cursor c = m_db.query(TABLE_NAME, null, COL_ID + "=?", args, null, null, null);
        c.moveToFirst();

        if (!c.isAfterLast()){
            crit = CursorToCritique(c);
        }

        return crit;
    }

    // Permet de recuperer toutes les critiques.
    public List<Critique> GetAllCritiques() throws ParseException {
        List<Critique> list = new ArrayList<Critique>();

        Cursor c = m_db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Critique crit = CursorToCritique(c);
            list.add(crit);
            c.moveToNext();
        }

        return list;
    }

    // Fonction qui ajoute une nouvelle critique dans la base de donnees.
    public int InsertCritiques(Critique crit) throws ParseException {

        // S'assurer que l'utilisateur n'existe pas.
        if (GetCritiqueByID(crit.GetID()) == null){

            ContentValues row = critiqueToContentValue(crit);
            int newId = (int) m_db.insert(TABLE_NAME, null, row);
            crit.SetID(newId);
            return newId;
        }
        else
            return Utilisateur.ID_UNDEFINED;
    }

    // Fonction qui convertie les valeurs user en content value pour la base de donnees.
    private ContentValues critiqueToContentValue(Critique crit){
        ContentValues row = new ContentValues();
        row.put(COL_USER, crit.GetUser());
        row.put(COL_OUVR, crit.GetOuvreur());
        row.put(COL_DIFF, crit.GetDiff());
        row.put(COL_DATE, String.valueOf(crit.GetDate()));
        row.put(COL_PISTE, crit.GetPiste());
        return row;
    }


    //
    // HELPER DB, NE PAS VRAIMENT TOUCHER.
    //


    private static class CritiqueDBHelper extends SQLiteOpenHelper{
        public CritiqueDBHelper(Context context){
            super(context, "pistes.sqlite", null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME
                    + "(" + COL_ID + " integer primary key autoincrement, "
                    + COL_USER + " text, "
                    + COL_OUVR + " text, "
                    + COL_DIFF + " text, "
                    + COL_DATE + " text, "
                    + COL_PISTE + " text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            this.onCreate(db);
        }
    }
}