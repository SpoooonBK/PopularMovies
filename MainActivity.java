package android.esteban.nyc.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieDAO.setApiKey(readProperty());

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
//        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

        if (id == R.id.moviePopularity){
//            editor.putInt("SORT_BY_METHOD", MovieDAO.SORT_BY_POPULARITY);
//            editor.commit();
            MovieDAO.setsSortByMethod(MovieDAO.SORT_BY_POPULARITY);
        }

        if (id == R.id.releaseDate){
//            editor.putInt("SORT_BY_METHOD", MovieDAO.SORT_BY_RATING);
//            editor.commit();
            MovieDAO.setsSortByMethod(MovieDAO.SORT_BY_RATING);
        }

        GridView gridView = (GridView)findViewById(R.id.movieGrid);
        MovieImageAdapter movieImageAdapter = (MovieImageAdapter) gridView.getAdapter();
        GetMoviesTask task = new GetMoviesTask();
        task.execute();
        movieImageAdapter.updateGrid();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String readProperty (){

        final String PROPERTIES_KEY_FOR_API = "key";
        String apiProperties = null;

        Properties properties = new Properties();
        AssetManager assetManager = this.getAssets();
        try {
            InputStream inputStream = assetManager.open("API.properties");
            properties.load(inputStream);
            apiProperties = properties.getProperty(PROPERTIES_KEY_FOR_API);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiProperties;
    }
}
