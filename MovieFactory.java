package android.esteban.nyc.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Spoooon on 8/22/2015.
 *
 *
 */
public class MovieFactory {

    private static final String ARRAY_KEY = "results";
    private static final String TITLE = "original_title";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";
    private static final String POPULARITY = "popularity";
    private static final String RATING = "vote_average";

    public static
    List<Movie> buildMovieList (String movieData) {
        List<Movie> movieList = new ArrayList<Movie>();


        try {
            JSONObject jsonObject = new JSONObject(movieData);
            JSONArray movieArray = jsonObject.getJSONArray(ARRAY_KEY);

            Log.d("popular", "Movie Array length = " + movieArray.length());

            for(int i = 0; i < movieArray.length(); i++){
                Movie movie = buildMovie(movieArray.getJSONObject(i));
                Log.d("popular", "added movie title: " + movie.getTitle());
                movieList.add(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public static Movie buildMovie (JSONObject movieData) throws JSONException {

        Log.d("popular", movieData.toString());
        String title = movieData.getString(TITLE);
        String overview = movieData.getString(OVERVIEW);
        String releaseDate = movieData.getString(RELEASE_DATE);
        String posterPath = movieData.getString(POSTER_PATH);
        Double popularity = movieData.getDouble(POPULARITY);
        Double rating = movieData.getDouble(RATING);


        Movie movie =  new Movie(title,overview,releaseDate,posterPath, popularity, rating );
        return movie;
    }
}
