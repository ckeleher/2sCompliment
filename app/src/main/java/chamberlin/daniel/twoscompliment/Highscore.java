package chamberlin.daniel.twoscompliment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Highscore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        //MyAdapter ad = new MyAdapter(this,R.layout.rowtext,MainActivity.sclist);
        ArrayAdapter<Player> ad = new ArrayAdapter<Player>(this,android.R.layout.simple_list_item_1,MainActivity.sclist);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(ad);
    }
}
