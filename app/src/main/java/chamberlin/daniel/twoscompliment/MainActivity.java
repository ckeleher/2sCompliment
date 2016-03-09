package chamberlin.daniel.twoscompliment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.media.MediaPlayer;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<Player> sclist = new ArrayList<>();
    static int on = 0;
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //song is Night Owl by Broke For Free. We need to give credit
        //and post license
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://shining-inferno-9683.firebaseio.com/");
        firebase.child("High scores").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                sclist.add(dataSnapshot.getValue(Player.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onPause(){
        pauseMusic();
        super.onPause();
    }

    @Override
    public void onResume(){
        music = MediaPlayer.create(this,R.raw.broke_for_free_night_owl);
        music.setLooping(true);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musicOn = settings.getBoolean("musicOn",true);
        Log.i("musicstuff","musciOn:"+musicOn);
        if(musicOn) {
            startMusic();
        }
        super.onResume();
    }

    @Override
    public void onDestroy(){
        if(music != null) {
            music.stop();
            music.release();
            music = null;
        }
        super.onDestroy();
    }

    private void pauseMusic(){
        if(music!=null && music.isPlaying())
            music.pause();
    }
    private void startMusic(){
        if(music!=null && !music.isPlaying()) {
            music.start();
        }
    }
    public void toggleMusic(View v){
        Button button = (Button) findViewById(R.id.mutebutton);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        if(on%2==0) {
            editor.putBoolean("musicOn",false);
            editor.commit();
            pauseMusic();
            button.setText("RESUME");
        }
        else {
            editor.putBoolean("musicOn",true);
            editor.commit();
            startMusic();
            button.setText("PAUSE");
        }
        on++;
    }

    public void highscore(View v){
        Intent intent = new Intent(this, Highscore.class);
        music.stop();
        music.reset();
        startActivity(intent);
    }

    public void startGame4(View v){

        //switch to different activity
        Intent intent = new Intent(this, boardActivity.class);
        intent.putExtra("size",4);
        music.stop();
        music.reset();
        startActivity(intent);
    }
    public void startGame6(View v){

        //switch to different activity
        Intent intent = new Intent(this, boardActivity.class);
        intent.putExtra("size",6);
        music.stop();
        music.reset();
        startActivity(intent);
    }
    public void startGame8(View v){

        //switch to different activity
        Intent intent = new Intent(this, boardActivity.class);
        intent.putExtra("size",8);
        music.stop();
        music.reset();
        startActivity(intent);
    }
    public void startGame10(View v){

        //switch to different activity
        Intent intent = new Intent(this, boardActivity.class);
        intent.putExtra("size",10);
        music.stop();
        music.reset();
        startActivity(intent);
    }
}
