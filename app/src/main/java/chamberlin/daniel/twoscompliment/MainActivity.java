package chamberlin.daniel.twoscompliment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Player> sclist = new ArrayList<Player>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://shining-inferno-9683.firebaseio.com/");
        ArrayList<Player> hs = new ArrayList<>();
        Player test = new Player("Max", 30);
        Player test1 = new Player("Bobby", 20);
        hs.add(test);
        hs.add(test1);
        firebase.child("High scores").setValue(hs);
        firebase.child("High scores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // GenericTypeIndicator<ArrayList<Player>> t = new GenericTypeIndicator<ArrayList<Player>>() {};
                sclist = (ArrayList<Player>)dataSnapshot.getValue();
                Log.i("data","data:" + dataSnapshot.getValue() + "child:" + dataSnapshot.child("High scores").getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void highscore(View v){
        Intent intent = new Intent(this, Highscore.class);
        startActivity(intent);
    }
}
