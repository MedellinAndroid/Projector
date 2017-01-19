package org.medellinandroid.projector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import org.medellinandroid.projector.domain.Movie;
import org.medellinandroid.projector.domain.MovieSearch;

public class MovieListActivity extends AppCompatActivity implements MovieView {
  private static final String SEARCH_KEY = "search";

  @BindView(R.id.movieList) RecyclerView movieListView;

  private MoviePresenter presenter;
  private MovieListAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_list);
    ButterKnife.bind(this);

    initialize();
  }

  private void initialize() {
    movieListView.setLayoutManager(
        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    adapter = new MovieListAdapter();
    movieListView.setAdapter(adapter);

    presenter = new MoviePresenter(this);
    MovieSearch movieSearch = getIntent().getParcelableExtra(SEARCH_KEY);
    setTitle(movieSearch.toString());
    presenter.search(movieSearch);
  }

  @Override public void showMovies(List<Movie> movies) {
    adapter.setMovies(movies);
  }

  @Override public void showError(Throwable exception) {
    Log.e(Constants.LOG_TAG, Log.getStackTraceString(exception));
  }

  public static Intent getIntent(final Context context, final MovieSearch movieSearch) {
    Intent intent = new Intent(context, MovieListActivity.class);
    intent.putExtra(SEARCH_KEY, movieSearch);
    return intent;
  }
}
