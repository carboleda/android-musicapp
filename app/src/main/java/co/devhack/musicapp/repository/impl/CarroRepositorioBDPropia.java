package co.devhack.musicapp.repository.impl;

import co.devhack.musicapp.repository.CarroRepositorio;

/**
 * Created by krlosf on 20/04/18.
 */

public class CarroRepositorioBDPropia implements CarroRepositorio {

    @Override
    public String obtenerMarca(String placa) {
        //CARGAR EL ARCHIVO DE CARRO Y BUSCAR POR PLACA
        String marca;
        if(placa.equals("HYK902")) {
            marca = "Kia";
        } else if(placa.equals("ABC356")) {
            marca = "Chevrolet";
        } else if(placa.equals("DFA123")) {
            marca = "Volvo";
        } else {
            marca = "NO se puedo obtener la marca";
        }
        return marca;
    }

    @Override
    public void guardarMarca(String placa, String marca) {
        //GUARDO LA MARCA DEL CARRO QUE COINCIDA CON LA PLACA
    }

}
