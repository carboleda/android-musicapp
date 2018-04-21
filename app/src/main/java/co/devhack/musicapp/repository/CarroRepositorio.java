package co.devhack.musicapp.repository;

/**
 * Created by krlosf on 20/04/18.
 */

public interface CarroRepositorio {

    String obtenerMarca(String placa);

    void guardarMarca(String placa, String marca);

}
