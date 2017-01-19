package org.medellinandroid.projector;

import android.support.v4.util.ArrayMap;
import java.util.Map;
import org.medellinandroid.projector.api.MovieService;
import org.medellinandroid.projector.domain.Movie;
import org.medellinandroid.projector.domain.MovieSearch;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 11:28 AM
 */
public class MoviePresenter {

  private final MovieView view;
  private final Map<String, Movie> movieCache = new ArrayMap<>();

  public MoviePresenter(MovieView view) {
    this.view = view;
  }

  void search(MovieSearch movieSearch) {
    // Search movies
    MovieService.INSTANCE.search(movieSearch.getTitle()).concatMap(searchResponse -> {
      final int pages = searchResponse.getTotalResults() / MovieService.DEFAULT_PAGE_SIZE;

      return Observable.range(2, pages)
          // Search pages and movies
          .flatMap(page -> MovieService.INSTANCE.search(movieSearch.getTitle(), page)
                  .flatMap(pagedSearchResponse -> Observable.from(
                      pagedSearchResponse.getMovies())) // Peliculas de las demas paginas
                  .mergeWith(Observable.from(searchResponse.getMovies())), // Peliculas de la pagina 1
              MovieService.MAX_CONCURRENT_REQUESTS)
          //
          .distinct() // Unique

          // Detalle  de la pelicula
          .flatMap(movie -> {
            if (movieCache.containsKey(movie.getImdbId())) {
              return Observable.just(movieCache.get(movie.getImdbId()));
            }
            return MovieService.INSTANCE.get(movie.getImdbId());
          }, MovieService.MAX_CONCURRENT_REQUESTS)
          // Cache del detalle
          .doOnNext(movie -> movieCache.put(movie.getImdbId(), movie))
          // Filtro por genero y por imdb rating
          .filter(movie -> movie.getGenre().contains(movieSearch.getGenre())
              && movie.getImdbRating() >= movieSearch.getRating())
          // Genera una lista ordenada por rating descendente
          .toSortedList((movie, movie2) -> Float.valueOf(movie2.getImdbRating())
              .compareTo(movie.getImdbRating()));
      // All esta listo
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(movies -> {
          // OK -> Mostrar las peliculas
      view.showMovies(movies);
          // Error -> Log error
    }, throwable -> view.showError(throwable));
  }
}
