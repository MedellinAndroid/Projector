package org.medellinandroid.projector.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import okhttp3.OkHttpClient;
import org.medellinandroid.projector.BuildConfig;
import org.medellinandroid.projector.domain.Movie;
import org.medellinandroid.projector.domain.SearchResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import rx.Observable;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 11:30 AM
 */
public enum MovieService {
  INSTANCE;

  public static final int DEFAULT_PAGE_SIZE = 10;
  public static final int MAX_CONCURRENT_REQUESTS = 5;

  private final OmdbApi omdbApi;

  MovieService() {
    OkHttpClient okHttpClient =
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();

    Moshi moshi = new Moshi.Builder().add(new ImdbRatingAdapter()).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    omdbApi = retrofit.create(OmdbApi.class);
  }

  public Observable<SearchResponse> search(String titleSearch) {
    return search(titleSearch, null, null, 1);
  }

  public Observable<SearchResponse> search(String titleSearch, int page) {
    return omdbApi.search(titleSearch, null, null, page);
  }

  public Observable<SearchResponse> search(String titleSearch, String type, String year, int page) {
    return omdbApi.search(titleSearch, type, year, page);
  }

  public Observable<Movie> get(String imdbId) {
    return omdbApi.get(imdbId);
  }

  class ImdbRatingAdapter {
    @ToJson String toJson(@ImdbRating float imdbRating) {
      return (imdbRating < 0) ? "N/A" : String.valueOf(imdbRating);
    }

    @FromJson @ImdbRating float fromJson(String imdbRating) {
      try {
        return Float.valueOf(imdbRating);
      } catch (NumberFormatException nfe) {
        return 0.0f;
      }
    }
  }
}
