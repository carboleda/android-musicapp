package co.devhack.musicapp.domain.usecase.impl;

import org.junit.Assert;
import org.junit.Test;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.helpers.Callback;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by krlosf on 15/04/18.
 */
public class LastFmAuthUseCaseImplTest {
    @Test
    public void getMobileSession() throws Exception {
        LastFmAuthUseCase lastFmAuthUseCase = new LastFmAuthUseCaseImpl();
        lastFmAuthUseCase.getMobileSession("carboleda", "1234abcd*")
        .observeOn(Schedulers.single())
        .subscribe(result -> {
            System.out.println("KEY" + result.getSession().getKey());
            Assert.assertNotNull(result);
        }, error -> {
            System.err.println(error);
            Assert.assertNull(error);
        });
    }
}