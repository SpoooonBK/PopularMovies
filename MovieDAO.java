package android.esteban.nyc.popularmovies;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Created by Spoooon on 8/22/2015.
 * This class has three methods:
 * buildURL uses the API key to build the URL.
 * getMovieData uses the URL to connect to the database.
 * getMovieList is the only public method of the class.
 * It invokes the MovieFactory.class to return an ArrayList of Movie objects
 */
public class MovieDAO {

    private static final String LOG_TAG = "POPULAR_MOVIES";

    public static List<Movie> getMovieList(String apiKey){
        String movieListData = null;
        try {

            movieListData = getMovieData(buildURL(apiKey));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return MovieFactory.buildMovieList(movieListData);
    }

    private static String getMovieData(URL url) {
        String movieData = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));


            while ((movieData = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(movieData + "\n");

            }
            movieData = buffer.toString();
            Log.d(LOG_TAG, movieData);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return movieData;
    }

    private static URL buildURL(String apiKey) throws MalformedURLException {
        String url;
        Uri.Builder builder = new Uri.Builder();

        final String MOVIEDB_BASE_URI_ = "api.themoviedb.org";


        final String SORT_BY = "sort_by" ;
        final String POPULARITY = "popularity.desc";
        final String API = "api_key";

        Log.d(LOG_TAG, "IN buildURL: " + apiKey);

        builder.scheme("http")
                .authority(MOVIEDB_BASE_URI_)
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter(SORT_BY, POPULARITY)
                .appendQueryParameter(API, apiKey);
//            "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=[]"

        Log.d(LOG_TAG, "URL: " + builder.build().toString());

        return new URL(builder.build().toString());
    }

}
