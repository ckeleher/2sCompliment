package chamberlin.daniel.twoscompliment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://shining-inferno-9683.firebaseio.com/");
        ArrayList hs = new ArrayList();
        Player test = new Player("Max", 40);
        Player test1 = new Player("Bobby", 20);
        hs.add(test);
        hs.add(test1);
        firebase.child("High scores").setValue(hs);
    }
}
