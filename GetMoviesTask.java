package android.esteban.nyc.popularmovies;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by Spoooon on 8/23/2015.
 */
public class GetMoviesTask extends AsyncTask<String , Void, List<Movie>> {




    @Override
    protected List<Movie> doInBackground(String... params) {
        return MovieDAO.getMovieList();
    }
}
