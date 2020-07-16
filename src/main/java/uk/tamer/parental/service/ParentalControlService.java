package uk.tamer.parental.service;

/**
 * Defines the contract of ParentalControlService
 *
 * @author tamer
 */
public interface ParentalControlService {

    /**
     * Checks whether a movie is suitable for viewing based on the
     * users' control level preference.
     *
     * This is based on the simple logic that any movie with an equal or lower level
     * to that specified in controlLevel is permitted.
     *
     * @param controlLevel
     * @param movieId
     * @return Returns true if the movie is suitable for viewing
     */
    boolean isAbleToViewMovie(ControlLevel controlLevel, String movieId);
}
