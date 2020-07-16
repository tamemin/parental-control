package uk.tamer.parental.service.impl;

import uk.tamer.parental.movie.client.TechnicalFailureException;
import uk.tamer.parental.movie.client.TitleNotFoundException;
import uk.tamer.parental.service.*;
import uk.tamer.parental.movie.client.MovieService;

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

        if (controlLevel==null) {
            throw new TitleNotFoundException();
        }

        try {

            ControlLevel returnedLevel = ControlLevel.valueOf(movieService.getParentalControlLevel(movieId));
            return(returnedLevel.ordinal() <= controlLevel.ordinal());

        } catch (TechnicalFailureException e) {

            // This ensures fail safety, anything wrong in the client API call just returns false as specified in the story
            return false;

        }


    }
}
