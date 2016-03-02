package chamberlin.daniel.twoscompliment;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    private int length;
    private static int on = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //song is Night Owl by Broke For Free. We need to give credit
        //and post license
        music = MediaPlayer.create(this,R.raw.broke_for_free_night_owl);
        music.setLooping(true);
        music.start();
    }

    @Override
    public void onPause(){
        pauseMusic();
        super.onPause();
    }

    @Override
    public void onResume(){
        startMusic();
        super.onResume();
    }
    private void pauseMusic(){
        if(music.isPlaying())
            music.stop();
        length = music.getCurrentPosition();
    }
    private void startMusic(){
        if(music.isPlaying()==false) {
            music.seekTo(length);
            music.start();
        }
    }
    public void toggleMusic(View v){
        if(on%2==0)
            pauseMusic();
        else
            startMusic();
        on++;
    }
}
