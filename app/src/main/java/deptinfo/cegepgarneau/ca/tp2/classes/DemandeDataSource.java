package deptinfo.cegepgarneau.ca.tp2.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Renaud-Charles on 20/03/2016.
 */
public class DemandeDataSource {
    public static final int ID_UNDEFINED = -1;
    public int              id = ID_UNDEFINED;

    public static final int TYPE_ACC = 0;
    public static final int TYPE_NONACC = 1;

    private final static int        DB_VERSION = 1;
    private final static String     TABLE_NAME = "demandes";

    private final static String     COL_ID = "_id";
    private final static String     COL_USERNAME = "username";
    private final static String     COL_NIVEAU= "niveau";
    private final static String     COL_TYPE = "type";
    private final static String     COL_DATE = "date";

    private final static int        IDX_ID = 0;
    private final static int        IDX_USERNAME = 1;
    private final static int        IDX_NIVEAU = 2;
    private final static int        IDX_TYPE = 3;
    private final static int        IDX_DATE = 4;

    private DemandeDBHelper     m_dbHelper;
    private SQLiteDatabase          m_db;

    public DemandeDataSource(Context context){
        m_dbHelper = new DemandeDBHelper(context);
    }

    public void open(){
        m_db = m_dbHelper.getWritableDatabase();
    }

    public void close(){
        m_db.close();
    }

    private Demande CursorToDema(Cursor c){
        Demande dema = new Demande(
                c.getString(IDX_USERNAME),
                c.getString(IDX_NIVEAU));//,
                //c.getInt(IDX_TYPE),
                //c.getString(IDX_DATE));
        return dema;
    }

    public Demande GetDemandeByID(int id){
        Demande dema = null;
        String[] args = new String[]{String.valueOf(id)};

        Cursor c = m_db.query(TABLE_NAME, null, COL_ID + "=?", args, null, null, null);
        c.moveToFirst();

        if (!c.isAfterLast()){
            dema = CursorToDema(c);
        }

        return dema;
    }

    // Permet de recuperer selon le type.
    public List<Demande> GetAllDemandesType(int type){
        List<Demande> list = new ArrayList<Demande>();
        String[] args = new String[]{String.valueOf(type)};

        Cursor c = m_db.query(TABLE_NAME, null, COL_TYPE + "=?", args, null, null, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Demande dema = CursorToDema(c);
            list.add(dema);
            c.moveToNext();
        }

        return list;
    }

    public Demande GetDemandeByUsername(String username){
        Demande dema = null;
        String[] args = new String[]{username};

        Cursor c = m_db.query(TABLE_NAME, null, COL_USERNAME + "=?", args, null, null, null);

        if (c.moveToFirst()){
            dema = CursorToDema(c);
        }

        return dema;
    }

    public int InsertDemande(Demande dema){

        // S'assurer que l'utilisateur n'existe pas.
        if (GetDemandeByUsername(dema.GetUsername()) == null){

            ContentValues row = demandeToContentValue(dema);
            int newId = (int) m_db.insert(TABLE_NAME, null, row);
            dema.SetID(newId);
            return newId;
        }
        else
            return Demande.ID_UNDEFINED;
    }

    private ContentValues demandeToContentValue(Demande dema){
        ContentValues row = new ContentValues();
        row.put(COL_USERNAME, dema.GetUsername());
        row.put(COL_DATE, (dema.GetDate()).toString());
        row.put(COL_NIVEAU, dema.GetNiveau());
        row.put(COL_TYPE,dema.GetType());

        return row;
    }

    //
    // HELPER DB, NE PAS VRAIMENT TOUCHER.
    //


    private static class DemandeDBHelper extends SQLiteOpenHelper{
        public DemandeDBHelper(Context context){
            super(context, "demandes.sqlite", null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME
                    + "(" + COL_ID + " integer primary key autoincrement, "
                    + COL_USERNAME + " text, "
                    + COL_DATE + " text, "
                    + COL_TYPE + " integer, "
                    + COL_NIVEAU + " text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            this.onCreate(db);
        }
    }
}