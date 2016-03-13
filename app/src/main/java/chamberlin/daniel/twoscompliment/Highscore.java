package chamberlin.daniel.twoscompliment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Highscore extends AppCompatActivity {
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        ArrayAdapter<Player> ad = new ArrayAdapter<Player>(this,android.R.layout.simple_list_item_1,MainActivity.sclist);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(ad);
        music = MediaPlayer.create(this,R.raw.broke_for_free_night_owl);
        music.setLooping(true);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musicOn = settings.getBoolean("musicOn",true);
        if(musicOn){
            music.start();
        }
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
    public void toggleMusic(View v){
        Button button = (Button) findViewById(R.id.mutebutton);
        if(MainActivity.on%2==0) {
            pauseMusic();
            button.setText("RESUME");
        }
        else {
            startMusic();
            button.setText("PAUSE");
        }
        MainActivity.on++;
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void goToMainMenu(View V){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
