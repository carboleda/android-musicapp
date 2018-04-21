package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.usecase.CarroUseCase;
import co.devhack.musicapp.repository.CarroRepositorio;
import co.devhack.musicapp.repository.impl.CarroRepositorioBDPropia;
import co.devhack.musicapp.repository.impl.CarroRepositorioGov;

/**
 * Created by krlosf on 20/04/18.
 */

public class CarroUseCaseImpl implements CarroUseCase {

    private boolean hayConexion = true;
    private CarroRepositorio carroRepositorio;

    @Override
    public String obtenerMarca(String placa) {

        if(hayConexion) {
            carroRepositorio = new CarroRepositorioGov();
        } else {
            carroRepositorio = new CarroRepositorioBDPropia();
        }

        String marca = carroRepositorio.obtenerMarca(placa);
        if(hayConexion) {
            CarroRepositorio carroRepositorioDBPropia = new CarroRepositorioBDPropia();
            carroRepositorioDBPropia.guardarMarca(placa, marca);
        }

        return marca;
    }

}
