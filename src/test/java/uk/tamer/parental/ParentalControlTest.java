package uk.tamer.parental;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.tamer.parental.api.*;
import uk.tamer.parental.impl.ParentalControlServiceImpl;


/**
 * Tests the expected behaviour of the {@link ParentalControlService}
 * 
 * @author tamer
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ParentalControlTest {

	String movieId;

	@Mock
    MovieService movieService;

	ParentalControlService parentalControlService;
		

	
	@Before
	public void init() {
		movieId = "123";
		parentalControlService = new ParentalControlServiceImpl(movieService);
	}

    /**
     * Test that TitleNotFoundException will bubble up when thrown from the movie service
     */
	@Test(expected = TitleNotFoundException.class)
    public void titleNotFoundTest() {

	    when(movieService.getParentalControlLevel(any(String.class))).thenThrow( new TitleNotFoundException());

	    parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_18, movieId);
    }

    /**
     * Test that TechnicalFailureException will bubble up when thrown from the movie service
     */
    @Test(expected = TechnicalFailureException.class)
    public void technicalFailureTest() {

        when(movieService.getParentalControlLevel(any(String.class))).thenThrow( new TechnicalFailureException());

        parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_18, movieId);
    }


    /**
     * Try to break the control with an invalid value (null)
     */
    @Test(expected = TitleNotFoundException.class)
    public void failSafeTest() {

        when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_18.toString());
        assertTrue(parentalControlService.isAbleToViewMovie(null, movieId));

    }

	@Test
	public void level_U_OK_Test() throws Exception {
		when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_U.toString());
		assertTrue(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_U, movieId));

	}


	@Test
    public void level_PG_OK_Test() throws Exception {
        when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_PG.toString());
        assertTrue(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_PG, movieId));

    }

    @Test
    public void level_PG_Denied_Test() throws Exception {
        when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_PG.toString());
        assertFalse(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_U, movieId));

    }

	@Test
	public void level_12_OK_Test() throws Exception {
		when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_12.toString());
		assertTrue(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_12, movieId));

	}

    @Test
    public void level_12_Denied_Test() throws Exception {
        when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_12.toString());
        assertFalse(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_PG, movieId));

    }

	@Test
	public void level_15_OK_Test() throws Exception {
		when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_15.toString());
		assertTrue(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_15, movieId));

	}

    @Test
    public void level_15_Denied_Test() throws Exception {
        when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_15.toString());
        assertFalse(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_PG, movieId));

    }

	@Test
	public void level_18_OK_Test() throws Exception {
		when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_18.toString());
		assertTrue(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_18, movieId));

	}

    @Test
    public void level_18_Denied_Test() throws Exception {
        when(movieService.getParentalControlLevel(any(String.class))).thenReturn(ControlLevel.LEVEL_18.toString());
        assertFalse(parentalControlService.isAbleToViewMovie(ControlLevel.LEVEL_15, movieId));

    }
	

}
