package chamberlin.daniel.twoscompliment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 3/1/16.
 */
    public class MyAdapter extends ArrayAdapter<Player> {
        int resource;
        Context context;

        public MyAdapter(Context _context, int _resource, List<Player> items) {
            super(_context, _resource, items);
            resource = _resource;
            context = _context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout newView;
            Log.i("test",getItem(position).toString());
           // Player w = getItem(position);

            // Inflate a new view if necessary.
            if (convertView == null) {
                newView = new LinearLayout(getContext());
                String inflater = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
                vi.inflate(resource,  newView, true);
            } else {
                newView = (LinearLayout) convertView;
            }
            TextView tv = (TextView) newView.findViewById(R.id.rowTextView);
          //  tv.setText(w.toString());
            return newView;
        }
    }

