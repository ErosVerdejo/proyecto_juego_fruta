package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class FrutaMala extends ObjetoCaida {
    public FrutaMala(Texture textura, float x, float y, int puntos, float velocidad, EstrategiaCaida estrategiaCaida) {
        super(textura, x, y, puntos, velocidad, estrategiaCaida);
    }

    @Override
    public void aplicarEfecto(PerroRecolector perro) {
        perro.daniar();
        perro.sumarPuntos(getPuntos());
        GestorRecursos.getInstancia().reproducirSonidoFrutaPodrida();
    }
}
