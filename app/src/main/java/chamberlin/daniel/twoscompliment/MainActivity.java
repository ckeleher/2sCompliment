package chamberlin.daniel.twoscompliment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://shining-inferno-9683.firebaseio.com/");
        firebase.child("Frank").setValue(99999999);
    }
}
