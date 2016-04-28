package deptinfo.cegepgarneau.ca.tp2.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Renaud-Charles on 2016-04-28.
 */
public class JsonParser {

    // Permet de deserializer une liste d'utilisateurs.
    public static ArrayList<Utilisateur> deserializeUserArray(String json_data) throws JSONException {
        ArrayList<Utilisateur> listeUsers = new ArrayList<Utilisateur>();

        JSONArray array = new JSONArray(json_data);

        for (int i = 0; i < array.length(); i++) {
            JSONObject userJson = array.getJSONObject(i);
            Utilisateur user = deserialiserUser(userJson.toString());
            listeUsers.add(user);
        }

        return listeUsers;
    }

    // Permet de deserializer un utilisateur.
    public static Utilisateur deserialiserUser(String json_data) throws JSONException {

        JSONObject jsonObject = new JSONObject(json_data);

        // Creation du nouvel utilisateur.
        Utilisateur user = new Utilisateur(jsonObject.getString("str_username"), jsonObject.getInt("int_compte"));

        return user;
    }

    /*
    // Permet de sérialiser en JSON un objet Personne.
    public static JSONObject serialiserJsonPersonne(Personne p) throws JSONException {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put(PERS_NAS, p.getNAS());
        jsonObj.put(PERS_NOM, p.getNom());
        jsonObj.put(PERS_AGE, p.getAge());

        return jsonObj;
    }*/


}
