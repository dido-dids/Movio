package cz.muni.fi.pv256.movio.uco373993;

/**
 * Created by David Boron on 15.10.2015.
 */
public class Film {

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
}
