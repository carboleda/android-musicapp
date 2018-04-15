package co.devhack.musicapp.domain.usecase.impl;

import junit.framework.TestFailure;

import org.junit.Assert;
import org.junit.Test;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.helpers.Callback;

import static org.junit.Assert.*;

/**
 * Created by krlosf on 15/04/18.
 */
public class LastFmAuthUseCaseImplTest {
    @Test
    public void getMobileSession() throws Exception {
        LastFmAuthUseCase lastFmAuthUseCase = new LastFmAuthUseCaseImpl();
        lastFmAuthUseCase.getMobileSession("carboleda", "1234abcd*", new Callback<SessionDto>() {
            @Override
            public void success(SessionDto result) {
                System.out.println(result.getSession().toString());
                Assert.assertNotNull(result);
            }

            @Override
            public void error(Exception error) {
                System.err.println(error);
                Assert.assertNull(error);
            }
        });
    }

}