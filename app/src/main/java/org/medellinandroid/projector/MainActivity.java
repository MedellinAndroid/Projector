package org.medellinandroid.projector;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.medellinandroid.projector.domain.MovieSearch;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 2:40 PM
 */
@RuntimePermissions public class MainActivity extends AppCompatActivity {
  @BindView(R.id.titleText) EditText titleView;
  @BindView(R.id.genreText) EditText genreView;
  @BindView(R.id.ratingBar) RatingBar ratingView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @NeedsPermission(Manifest.permission.INTERNET) @OnClick(R.id.search_button) void search() {
    MovieSearch movieSearch =
        new MovieSearch(titleView.getText().toString(), genreView.getText().toString(),
            ratingView.getRating());
    Intent intent = MovieListActivity.getIntent(this, movieSearch);
    startActivity(intent);
  }
}
