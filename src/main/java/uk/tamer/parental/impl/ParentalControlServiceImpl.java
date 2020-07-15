package uk.tamer.parental.impl;

import uk.tamer.parental.api.*;

/**
 * Provides an implementation for {@Link ParentalControlService}
 *
 * @author tamer
 *
 */
public class ParentalControlServiceImpl implements ParentalControlService {

    private MovieService movieService;

    /**
     * Create a ParentalControlServiceImpl
     * @param movieService provide the dependency
     *
     */
    public ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }
    @Override
    public boolean isAbleToViewMovie(ControlLevel controlLevel, String movieId) {

        ControlLevel returnedLevel = ControlLevel.valueOf(movieService.getParentalControlLevel(movieId));

        if (controlLevel==null) {
            throw new TitleNotFoundException();
        }

        return(returnedLevel.ordinal() <= controlLevel.ordinal());

    }
}
