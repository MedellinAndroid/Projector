package org.medellinandroid.projector.domain;

import com.squareup.moshi.Json;
import org.medellinandroid.projector.api.ImdbRating;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 11:41 AM
 */
public class Movie {
  @Json(name = "Title")
  private final String title;
  @Json(name = "Year")
  private final String year;
  @Json(name = "Rated")
  private final String rated;
  @Json(name = "Released")
  private final String released;
  @Json(name = "Runtime")
  private final String runtime;
  @Json(name = "Genre")
  private final String genre;
  @Json(name = "Director")
  private final String director;
  @Json(name = "Writer")
  private final String writer;
  @Json(name = "Actors")
  private final String actors;
  @Json(name = "Plor")
  private final String plot;
  @Json(name = "Language")
  private final String language;
  @Json(name = "Country")
  private final String country;
  @Json(name = "Awards")
  private final String awards;
  @Json(name = "Poster")
  private final String posterUrl;
  @ImdbRating
  private final float imdbRating;
  private final String imdbVotes;
  @Json(name = "imdbID")
  private final String imdbId;
  @Json(name = "Type")
  private final String type;

  public Movie(String title, String year, String rated, String released, String runtime,
      String genre, String director, String writer, String actors, String plot, String language,
      String country, String awards, String posterUrl, Float imdbRating, String imdbVotes,
      String imdbId, String type) {
    this.title = title;
    this.year = year;
    this.rated = rated;
    this.released = released;
    this.runtime = runtime;
    this.genre = genre;
    this.director = director;
    this.writer = writer;
    this.actors = actors;
    this.plot = plot;
    this.language = language;
    this.country = country;
    this.awards = awards;
    this.posterUrl = posterUrl;
    this.imdbRating = imdbRating;
    this.imdbVotes = imdbVotes;
    this.imdbId = imdbId;
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public String getYear() {
    return year;
  }

  public String getRated() {
    return rated;
  }

  public String getReleased() {
    return released;
  }

  public String getRuntime() {
    return runtime;
  }

  public String getGenre() {
    return genre;
  }

  public String getDirector() {
    return director;
  }

  public String getWriter() {
    return writer;
  }

  public String getActors() {
    return actors;
  }

  public String getPlot() {
    return plot;
  }

  public String getLanguage() {
    return language;
  }

  public String getCountry() {
    return country;
  }

  public String getAwards() {
    return awards;
  }

  public String getPosterUrl() {
    return posterUrl;
  }

  public float getImdbRating() {
    return imdbRating;
  }

  public String getImdbVotes() {
    return imdbVotes;
  }

  public String getImdbId() {
    return imdbId;
  }

  public String getType() {
    return type;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Movie{");
    sb.append("title='").append(title).append('\'');
    sb.append(", imdbRating=").append(imdbRating);
    sb.append('}');
    return sb.toString();
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Movie movie = (Movie) o;

    if (!title.equals(movie.title)) return false;
    return imdbId.equals(movie.imdbId);
  }

  @Override public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + imdbId.hashCode();
    return result;
  }
}
