package android.esteban.nyc.popularmovies;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static String apiKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(!preferences.contains("SORT_BY_METHOD")) {
            editor.putInt("SORT_BY_METHOD", MovieDAO.SORT_BY_POPULARITY);
            editor.commit();
        }
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        SharedPreferences.Editor editor = preferences.edit();

        if (id == R.id.moviePopularity){
            editor.putInt("SORT_BY_METHOD", MovieDAO.SORT_BY_POPULARITY);
            editor.commit();

            GridView gridView = (GridView)findViewById(R.id.movieGrid);
            MovieImageAdapter movieImageAdapter = (MovieImageAdapter) gridView.getAdapter();
            GetMoviesTask task = new GetMoviesTask();
            task.execute(apiKey);
            movieImageAdapter.updateGrid();
        }

        if (id == R.id.releaseDate){
            editor.putInt("SORT_BY_METHOD", MovieDAO.SORT_BY_RATING);
            editor.commit();

            GridView gridView = (GridView)findViewById(R.id.movieGrid);
            MovieImageAdapter movieImageAdapter = (MovieImageAdapter) gridView.getAdapter();
            GetMoviesTask task = new GetMoviesTask();
            task.execute(apiKey);
            movieImageAdapter.updateGrid();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
