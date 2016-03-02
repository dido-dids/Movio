package cz.muni.fi.pv256.movio.uco373993.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David Boron on 15.10.2015.
 */
@AdditionalAnnotation.DefaultContentUri(authority= "cz.muni.fi.pv256.movio.uco373993", path= "movies")
@AdditionalAnnotation.DefaultContentMimeTypeVnd(name="cz.muni.fi.pv256.movio.uco373993.provider", type="movies")
@DatabaseTable(tableName = "movies")
public class Movie implements Parcelable {


    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    @DatabaseField
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @DatabaseField(columnName = BaseColumns._ID, id = true)
    @SerializedName("id")
    private Long id;
    @DatabaseField
    @SerializedName("release_date")
    private String mReleaseDate;
    @DatabaseField
    @SerializedName("poster_path")
    private String mCoverPath;
    @DatabaseField
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (mBackdropPath != null ? !mBackdropPath.equals(movie.mBackdropPath) : movie.mBackdropPath != null)
            return false;
        if (!id.equals(movie.id)) return false;
        if (mReleaseDate != null ? !mReleaseDate.equals(movie.mReleaseDate) : movie.mReleaseDate != null)
            return false;
        if (mCoverPath != null ? !mCoverPath.equals(movie.mCoverPath) : movie.mCoverPath != null)
            return false;
        return !(mTitle != null ? !mTitle.equals(movie.mTitle) : movie.mTitle != null);

    }

    @Override
    public int hashCode() {
        int result = mBackdropPath != null ? mBackdropPath.hashCode() : 0;
        result = 31 * result + id.hashCode();
        result = 45 * result + (mReleaseDate != null ? mReleaseDate.hashCode() : 0);
        result = 64 * result + (mCoverPath != null ? mCoverPath.hashCode() : 0);
        result = 72 * result + (mTitle != null ? mTitle.hashCode() : 0);
        return result;
    }
}
