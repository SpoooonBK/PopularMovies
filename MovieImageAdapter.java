package android.esteban.nyc.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spoooon on 8/25/2015.
 */
public class MovieImageAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movies;

    public MovieImageAdapter(List<Movie> movies, Context context) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(context).load(movies.get(position).getPosterPath()).into(imageView);

        //Todo fix gridview size
        //        imageView.setLayoutParams(
        //                new GridView.LayoutParams(imageView.getWidth(), imageView.getHeight()));

        imageView.isFocusable();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("MOVIE_TITLE", movies.get(position).getTitle());
                detailIntent.putExtra("RELEASE_DATE", movies.get(position).getReleaseDate());
                detailIntent.putExtra("OVERVIEW", movies.get(position).getOverview());
                detailIntent.putExtra("POSTER_PATH", movies.get(position).getPosterPath());
                detailIntent.putExtra("POPULARITY", movies.get(position).getPopularity());
                context.startActivity(detailIntent);
            }
        });


        return imageView;
    }

    public void updateGrid(){
        notifyDataSetChanged();
    }

//    public void sortByPopularity() {
//
//        List<Movie> tempList = new ArrayList<Movie>();
//
//        for (int i = 0; i < getCount(); i++) {
//            Movie movie = movies.get(i);
//            double moviePopularity = movie.getPopularity();
//
//            if (tempList.size() == 0) {
//                tempList.add(movie);
//            } else {
//
//                int index = 0;
//                while (index < tempList.size()) {
//                    if (moviePopularity == tempList.get(index).getPopularity() ||
//                            moviePopularity > tempList.get(index).getPopularity()) {
//                        tempList.add(index, movie);
//                        break;
//                    }
//                    index++;
//                }
//                if (index == tempList.size()) {
//                    tempList.add(movie);
//                }
//            }
//        }
//        movies = tempList;
//        notifyDataSetChanged();
//    }
//
//    public void sortByRating() {
//
//        List<Movie> tempList = new ArrayList<Movie>();
//
//        for (int i = 0; i < getCount(); i++) {
//            Movie movie = movies.get(i);
//            double rating = movie.getRating();
//
//            if (tempList.size() == 0) {
//                tempList.add(movie);
//            } else {
//
//                int index = 0;
//                while (index < tempList.size()) {
//                    if (rating == tempList.get(index).getRating() ||
//                            rating > tempList.get(index).getRating()) {
//                        tempList.add(index, movie);
//                        break;
//                    }
//                    index++;
//                }
//                if (index == tempList.size()) {
//                    tempList.add(movie);
//                }
//            }
//        }
//        movies = tempList;
//        notifyDataSetChanged();
//    }


}
