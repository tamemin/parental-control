package uk.tamer.parental.movie.client;

/**
 * Provides a lower level service to get information about movies
 *
 * @author tamer
 *
 */
public interface MovieService {

    /**
     * Get movie parent control level
     * @param movieId
     * @return The string representing the parental control level
     * @throws TitleNotFoundException If a movie is not found
     * @throws TechnicalFailureException If some other system error has occurred
     */
    String getParentalControlLevel(String movieId)
            throws TitleNotFoundException,
            TechnicalFailureException;
}
