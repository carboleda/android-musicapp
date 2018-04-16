package co.devhack.musicapp.domain.usecase.impl;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.domain.usecase.TrackUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.Globals;

/**
 * Created by krlosf on 15/04/18.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrackUseCaseImplTest {

    @Test
    public void firstGetMobileSession() throws Exception {
        LastFmAuthUseCase lastFmAuthUseCase = new LastFmAuthUseCaseImpl();
        /*lastFmAuthUseCase.getMobileSession("carboleda", "1234abcd*", new Callback<SessionDto>() {
            @Override
            public void success(SessionDto result) {
                System.out.println(result.getSession().getKey());
                Globals.USERNAME = result.getSession().getUsername();
                Globals.SESSION_KEY = result.getSession().getKey();
                Assert.assertNotNull(result);
            }

            @Override
            public void error(Throwable error) {
                System.err.println(error);
                Assert.assertNull(error);
            }
        });*/
    }

    @Test
    public void secondLove() throws Exception {
        TrackUseCase trackUseCase = new TrackUseCaseImpl();
        trackUseCase.love("Take Me Out", "Franz Ferdinand", new Callback<Boolean>() {
            @Override
            public void success(Boolean result) {
                Assert.assertTrue(result);
            }

            @Override
            public void error(Throwable error) {
                Assert.assertNull(error);
            }
        });
    }

    @Test
    public void thirdUnlove() throws Exception {
        TrackUseCase trackUseCase = new TrackUseCaseImpl();
        trackUseCase.unlove("Take Me Out", "Franz Ferdinand", new Callback<Boolean>() {
            @Override
            public void success(Boolean result) {
                Assert.assertTrue(result);
            }

            @Override
            public void error(Throwable error) {
                Assert.assertNull(error);
            }
        });
    }

}