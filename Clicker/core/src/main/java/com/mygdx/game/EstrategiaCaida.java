package com.mygdx.game;

/**
 * Interfaz usada para el patrón Strategy. Define cómo cae un objeto.
 */
public interface EstrategiaCaida {
    void caer(ObjetoCaida objeto, float delta);
}
