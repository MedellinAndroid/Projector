package org.medellinandroid.projector;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.medellinandroid.projector.domain.Movie;

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieHolder> {
  private List<Movie> movies = new ArrayList<>();

  @Override public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
    return new MovieHolder(itemView);
  }

  @Override public void onBindViewHolder(MovieHolder holder, int position) {
    holder.bindMovie(movies.get(position));
  }

  @Override public int getItemCount() {
    return movies.size();
  }

  /**
   * Movie holder
   */
  static class MovieHolder extends RecyclerView.ViewHolder {
    private final View itemView;

    @BindView(R.id.movieImage) ImageView imageView;
    @BindView(R.id.movieTitle) TextView movieTitle;
    @BindView(R.id.rating) TextView ratingView;
    @BindColor(R.color.colorAccent) int placeholderColor;
    @BindString(R.string.rating) String rating;

    public MovieHolder(final View itemView) {
      super(itemView);
      this.itemView = itemView;
      ButterKnife.bind(this, itemView);
    }

    public void bindMovie(Movie movie) {
      Picasso.with(itemView.getContext())
          .load(movie.getPosterUrl())
          .placeholder(new ColorDrawable(placeholderColor))
          .into(imageView);
      movieTitle.setText(movie.getTitle());
      ratingView.setText(String.format(rating, movie.getImdbRating()));
    }
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
    notifyDataSetChanged();
  }
}