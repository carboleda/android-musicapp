package co.devhack.musicapp.repository.impl;

import co.devhack.musicapp.repository.CarroRepositorio;

/**
 * Created by krlosf on 20/04/18.
 */

public class CarroRepositorioGov implements CarroRepositorio {

    @Override
    public String obtenerMarca(String placa) {
        //CONECTARME A LA BASE DE DATOS DEL GOBIERNO
        String marca;
        switch (placa) {
            case "HYK902":
                marca = "Kia";
                break;
            case "ABC356":
                marca = "Chevrolet";
                break;
            default:
                marca = "Desconocida";
        }

        return marca;
    }

    @Override
    public void guardarMarca(String placa, String marca) {
        //NO HAGO NADA, LAS MARCAS LAS GUARDA SOLO EL GOBIERNO
    }
}
