package org.medellinandroid.projector.domain;

import com.squareup.moshi.Json;
import java.util.List;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 11:39 AM
 */
public class SearchResponse {
  @Json(name = "Search")
  private final List<Movie> movies;
  private final int totalResults;

  public SearchResponse(List<Movie> movies, int totalResults) {
    this.movies = movies;
    this.totalResults = totalResults;
  }

  public List<Movie> getMovies() {
    return movies;
  }

  public int getTotalResults() {
    return totalResults;
  }
}
