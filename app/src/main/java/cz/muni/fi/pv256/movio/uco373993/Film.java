package cz.muni.fi.pv256.movio.uco373993;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David Boron on 15.10.2015.
 */
public class Film implements Parcelable {

    private long mReleaseDate;
    private String mCoverPath;
    private String mTitle;

    public Film(long releaseDate, String coverPath, String title) {
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

    protected Film(Parcel in) {
        this.mReleaseDate = in.readLong();
        this.mCoverPath = in.readString();
        this.mTitle = in.readString();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
