package android.esteban.nyc.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.posterImage);
        Intent intent = getActivity().getIntent();

        if(intent.hasExtra("POSTER_PATH")){
            String posterPath = intent.getStringExtra("POSTER_PATH");
            Picasso.with(getActivity()).load(posterPath)
                    .into(imageView);
        }

        if (intent.hasExtra("OVERVIEW")){
            TextView overview = (TextView)rootView.findViewById(R.id.overview);
            overview.setText(intent.getStringExtra("OVERVIEW"));
        }

        if (intent.hasExtra("MOVIE_TITLE")) {
            TextView movieTitle = (TextView) rootView.findViewById(R.id.movieTitle);
            movieTitle.setText(intent.getStringExtra("MOVIE_TITLE"));
        }

        if (intent.hasExtra("RELEASE_DATE")){
            TextView releaseDate = (TextView)rootView.findViewById(R.id.releaseDate);
            releaseDate.setText(intent.getStringExtra("RELEASE_DATE"));
        }

        if (intent.hasExtra("POPULARITY")){
            TextView popularity = (TextView)rootView.findViewById(R.id.popularity);
            Double pop = intent.getDoubleExtra("POPULARITY", 0);
            popularity.setText(pop.toString());
        }

        if (intent.hasExtra("RATING")){
            TextView popularity = (TextView)rootView.findViewById(R.id.rating);
            Double pop = intent.getDoubleExtra("RATING", 0);
            popularity.setText(pop.toString());
        }



        return rootView;
    }
}
