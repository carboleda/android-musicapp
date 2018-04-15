package co.devhack.musicapp.domain.usecase;

import java.util.Map;

/**
 * Created by krlosf on 15/04/18.
 */

public interface SignCallUseCase {

    String signature(String method, Map<String, String> params, String secret);

}
