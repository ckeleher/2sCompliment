package chamberlin.daniel.twoscompliment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class enterName extends AppCompatActivity {
    private EditText ed;
    private Firebase firebase;
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        music = MediaPlayer.create(this, R.raw.broke_for_free_night_owl);
        music.setLooping(true);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musicOn = settings.getBoolean("musicOn",true);
        if(musicOn){
            music.start();
        }
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://shining-inferno-9683.firebaseio.com/");
        ed = (EditText) findViewById(R.id.editText);
        ed.setText("");
        final Button button = (Button)findViewById(R.id.hsbutton);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //should also check if bool accurate is true
                if (!ed.getText().toString().trim().equals("")) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.INVISIBLE);
                }
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
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musicOn = settings.getBoolean("musicOn",true);
        if(musicOn) {
            startMusic();
        }
        super.onResume();
    }

    @Override
    public void onDestroy(){
        music.stop();
        music.release();
        super.onDestroy();
    }

    private void pauseMusic(){
        if(music.isPlaying())
            music.pause();
    }
    private void startMusic(){
        if(!music.isPlaying()) {
            music.start();
        }
    }
    public void goToHighscores(View v){
        Intent intent = new Intent(this, Highscore.class);
        Player temp = new Player(ed.getText().toString().trim(),boardActivity.score);
        firebase.child("High scores").push().setValue(temp);
        startActivity(intent);
    }
}
