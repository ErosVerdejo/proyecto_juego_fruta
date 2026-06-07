package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class FrutaEspecial extends ObjetoCaida {
    public FrutaEspecial(Texture textura, float x, float y, int puntos, float velocidad, EstrategiaCaida estrategiaCaida) {
        super(textura, x, y, puntos, velocidad, estrategiaCaida);
    }

    @Override
    public void aplicarEfecto(PerroRecolector perro) {
        perro.sumarPuntos(getPuntos());
        perro.recuperarVida();
        GestorRecursos.getInstancia().reproducirSonidoRecolectar();
    }
}
