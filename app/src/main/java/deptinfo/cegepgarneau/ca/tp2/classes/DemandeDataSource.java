package deptinfo.cegepgarneau.ca.tp2.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */
public class DemandeDataSource {

    // PROPRIETEES DE LA TABLE
    private final static int        DB_VERSION = 1;
    private final static String     TABLE_NAME = "pistes";

    private final static String     COL_ID = "_id";
    private final static String     COL_USERNAME = "username";
    private final static String     COL_PASSWORD = "password";
    private final static String     COL_USERTYPE = "usertype";
    private final static String     COL_NOM = "nom";
    private final static String     COL_PRENOM = "prenom";
    private final static String     COL_EMAIL = "email";
    private final static String     COL_ADRESSE = "adresse";
    private final static String     COL_TELEPHONE = "telephone";

    private final static int        IDX_ID = 0;
    private final static int        IDX_USERNAME = 1;
    private final static int        IDX_PASSWORD = 2;
    private final static int        IDX_USERTYPE = 3;
    private final static int        IDX_NOM = 4;
    private final static int        IDX_PRENOM = 5;
    private final static int        IDX_EMAIL = 6;
    private final static int        IDX_ADRESSE = 7;
    private final static int        IDX_TELEPHONE = 8;

    private UtilisateurDBHelper     m_dbHelper;
    private SQLiteDatabase          m_db;

    // CONSTRUCTEUR
    public DemandeDataSource(Context context){
        m_dbHelper = new UtilisateurDBHelper(context);
    }

    // Ouvre la connexion a la DB
    public void open(){
        m_db = m_dbHelper.getWritableDatabase();
    }

    // Ferme la connexion a la DB
    public void close(){
        m_db.close();
    }

    // Cursor vers un utilisateur.
    private Utilisateur CursorToUser(Cursor c){
        Utilisateur user = new Utilisateur(
                c.getString(IDX_USERNAME),
                c.getString(IDX_PASSWORD),
                c.getInt(IDX_USERTYPE));
        return user;
    }

    // Permet de recuperer un utilisateur par son ID.
    public Utilisateur GetUtilisateurByID(int id){
        Utilisateur user = null;
        String[] args = new String[]{String.valueOf(id)};

        Cursor c = m_db.query(TABLE_NAME, null, COL_ID + "=?", args, null, null, null);
        c.moveToFirst();

        if (!c.isAfterLast()){
            user = CursorToUser(c);
        }

        return user;
    }

    public Utilisateur GetUtilisateur(String username, String password){
        Utilisateur user = null;

        String[] args = new String[]{username, password};

        Cursor c = m_db.query(TABLE_NAME, null, COL_USERNAME + "=? AND " + COL_PASSWORD + "=?" , args, null, null, null);

        if (c.moveToFirst()){
            user = CursorToUser(c);
        }

        return user;
    }

    // Permet de recuperer un utilisateur par son ID.
    public Utilisateur GetUtilisateurByUsername(String username){
        Utilisateur user = null;
        String[] args = new String[]{username};

        Cursor c = m_db.query(TABLE_NAME, null, COL_USERNAME + "=?", args, null, null, null);

        if (c.moveToFirst()){
            user = CursorToUser(c);
        }

        return user;
    }

    // Permet de recuperer tout les users.
    public List<Utilisateur> GetAllUtilisateurs(){
        List<Utilisateur> list = new ArrayList<Utilisateur>();

        Cursor c = m_db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Utilisateur user = CursorToUser(c);
            list.add(user);
            c.moveToNext();
        }

        return list;
    }

    // Fonction qui ajoute un nouvelle utilisateur dans la base de donnees.
    public int InsertUser(Utilisateur user){

        // S'assurer que l'utilisateur n'existe pas.
        if (GetUtilisateurByUsername(user.GetUsername()) == null){

            ContentValues row = utilisateurToContentValue(user);
            int newId = (int) m_db.insert(TABLE_NAME, null, row);
            user.SetID(newId);
            return newId;
        }
        else
            return Utilisateur.ID_UNDEFINED;
    }

    // Fonction qui convertie les valeurs user en content value pour la base de donnees.
    private ContentValues utilisateurToContentValue(Utilisateur user){
        ContentValues row = new ContentValues();
        row.put(COL_USERNAME, user.GetUsername());
        row.put(COL_PASSWORD, user.GetPassword());
        row.put(COL_USERTYPE, user.GetTypeCompte());
        row.put(COL_NOM, user.GetNom());
        row.put(COL_PRENOM, user.GetPrenom());
        row.put(COL_EMAIL, user.GetEmail());
        row.put(COL_ADRESSE, user.GetAdresse());
        row.put(COL_TELEPHONE, user.GetNoTelephone());
        return row;
    }

    //
    // HELPER DB, NE PAS VRAIMENT TOUCHER.
    //


    private static class UtilisateurDBHelper extends SQLiteOpenHelper{
        public UtilisateurDBHelper(Context context){
            super(context, "pistes.sqlite", null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME
                    + "(" + COL_ID + " integer primary key autoincrement, "
                    + COL_USERNAME + " text, "
                    + COL_PASSWORD + " text, "
                    + COL_USERTYPE + " integer, "
                    + COL_NOM + " text, "
                    + COL_PRENOM + " text, "
                    + COL_EMAIL + " text, "
                    + COL_ADRESSE + " text, "
                    + COL_TELEPHONE + " text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            this.onCreate(db);
        }
    }
}