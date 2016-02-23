package deptinfo.cegepgarneau.ca.tp2;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// L'activite login est utilise seulement pour le login
// avant d'entrer dans l'app.
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //Lorsque l'on clique connexion.
    public void onConnexionClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        finish(); // Plus besoin de cette activite.
        startActivity(intent);
    }

    // Un placeholder fragment pour voir si on peut switch btw
    public static class TestFragment extends Fragment {
        public TestFragment(){

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }


    }
}
