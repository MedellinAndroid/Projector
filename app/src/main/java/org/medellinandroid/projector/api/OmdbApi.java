package org.medellinandroid.projector.api;

import org.medellinandroid.projector.domain.Movie;
import org.medellinandroid.projector.domain.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 11:29 AM
 */

public interface OmdbApi {
  @GET("/") Observable<SearchResponse> search(@Query("s") String titleSearch,
      @Query("type") String type, @Query("t") String year, @Query("page") int page);

  @GET("/") Observable<Movie> get(@Query("i") String imdbId);
}
