package cz.muni.fi.pv256.movio.uco373993;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by David Boron on 15.10.2015.
 */
public class Movie implements Parcelable {


    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("id")
    private long id;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("poster_path")
    private String mCoverPath;
    @SerializedName("title")
    private String mTitle;

    public Movie(){
    }

    public Movie(String releaseDate, String coverPath, String title) {
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setCoverPath(String coverPath) {
        mCoverPath = coverPath;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getCoverPath() {
        return mCoverPath;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mCoverPath);
        dest.writeString(this.mBackdropPath);
        dest.writeString(this.mTitle);
    }

    protected Movie(Parcel in) {
        this.id = in.readLong();
        this.mReleaseDate = in.readString();
        this.mCoverPath = in.readString();
        this.mBackdropPath = in.readString();
        this.mTitle = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
