package cz.muni.fi.pv256.movio.uco373993;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David Boron on 15.10.2015.
 */
public class Movie implements Parcelable {

    private long mReleaseDate;
    private String mCoverPath;
    private String mTitle;

    public Movie(long releaseDate, String coverPath, String title) {
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
    }

    public void setReleaseDate(long releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public void setCoverPath(String coverPath) {
        this.mCoverPath = coverPath;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public long getReleaseDate() {

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
        dest.writeLong(this.mReleaseDate);
        dest.writeString(this.mCoverPath);
        dest.writeString(this.mTitle);
    }

    protected Movie(Parcel in) {
        this.mReleaseDate = in.readLong();
        this.mCoverPath = in.readString();
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
