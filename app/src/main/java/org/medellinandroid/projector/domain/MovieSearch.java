package org.medellinandroid.projector.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: HUGE-gilbert
 * Date: 1/18/17
 * Time: 4:23 PM
 */
public class MovieSearch implements Parcelable {
  private final String title;
  private final String genre;
  private final float rating;

  public MovieSearch(String title, String genre, float rating) {
    this.title = title;
    this.genre = genre;
    this.rating = rating * 2;
  }

  public String getTitle() {
    return title;
  }

  public String getGenre() {
    return genre;
  }

  public float getRating() {
    return rating;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeString(this.genre);
    dest.writeFloat(this.rating);
  }

  protected MovieSearch(Parcel in) {
    this.title = in.readString();
    this.genre = in.readString();
    this.rating = in.readFloat();
  }

  public static final Parcelable.Creator<MovieSearch> CREATOR =
      new Parcelable.Creator<MovieSearch>() {
        @Override public MovieSearch createFromParcel(Parcel source) {
          return new MovieSearch(source);
        }

        @Override public MovieSearch[] newArray(int size) {
          return new MovieSearch[size];
        }
      };

  @Override public String toString() {
    final StringBuilder sb =
        new StringBuilder(title).append(", ").append(genre).append(", ").append(rating);
    return sb.toString();
  }
}
