package cz.muni.fi.pv256.movio.uco373993;

import android.test.AndroidTestCase;

import cz.muni.fi.pv256.movio.uco373993.db.MovieDao;
import cz.muni.fi.pv256.movio.uco373993.model.Movie;

public class TestMovieManager extends AndroidTestCase {

    private MovieDao mMovieDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMovieDao = new MovieDao(mContext);
    }

    private Movie createMovie(long id, String title) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        return movie;
    }

    public void testCreateMovie() throws Exception {

        Movie m1 = createMovie(1, "Movie 1");
        mMovieDao.createOrUpdate(m1);
        Movie fetched = mMovieDao.fetch(m1.getId());

        assertDeepEquals(m1, fetched);
    }

    private void assertDeepEquals(Movie expected, Movie actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getBackdropPath(), actual.getBackdropPath());
        assertEquals(expected.getCoverPath(), actual.getCoverPath());
        assertEquals(expected.getReleaseDate(), actual.getReleaseDate());
    }

}
