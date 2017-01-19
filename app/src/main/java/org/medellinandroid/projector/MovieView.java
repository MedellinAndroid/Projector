package org.medellinandroid.projector;

import java.util.List;
import org.medellinandroid.projector.domain.Movie;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 2:33 PM
 */
public interface MovieView {
  /**
   * Show the movies in the view
   * @param movies List of <code>Movie</code>
   */
  void showMovies(List<Movie> movies);

  /**
   * Show the given exception on the view
   * @param exception Exception to show
   */
  void showError(Throwable exception);
}
