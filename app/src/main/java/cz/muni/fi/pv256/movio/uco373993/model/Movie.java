package cz.muni.fi.pv256.movio.uco373993.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David Boron on 15.10.2015.
 */
public class Movie implements Parcelable {


    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
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

    public Movie() {
    }

    public Movie(String releaseDate, String coverPath, String title) {
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
    }

    protected Movie(Parcel in) {
        this.id = in.readLong();
        this.mReleaseDate = in.readString();
        this.mCoverPath = in.readString();
        this.mBackdropPath = in.readString();
        this.mTitle = in.readString();
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

    public String getReleaseDate() {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(mReleaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat("dd.MM.yyyy");
        return fmtOut.format(date);
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getCoverPath() {
        return mCoverPath;
    }

    public void setCoverPath(String coverPath) {
        mCoverPath = coverPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
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
}
