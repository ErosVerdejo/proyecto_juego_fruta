package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Patrón Builder: construye frutas con textura, puntaje, velocidad y estrategia.
 */
public class FrutaBuilder {
    private Texture textura;
    private float x;
    private float y;
    private int puntos;
    private float velocidad;
    private EstrategiaCaida estrategiaCaida;
    private String tipo;
    private TipoPowerUp tipoPowerUp;

    public FrutaBuilder conTextura(Texture textura) {
        this.textura = textura;
        return this;
    }

    public FrutaBuilder conPosicion(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public FrutaBuilder conPuntos(int puntos) {
        this.puntos = puntos;
        return this;
    }

    public FrutaBuilder conVelocidad(float velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public FrutaBuilder conEstrategia(EstrategiaCaida estrategiaCaida) {
        this.estrategiaCaida = estrategiaCaida;
        return this;
    }

    public FrutaBuilder conTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public FrutaBuilder conTipoPowerUp(TipoPowerUp tipoPowerUp) {
        this.tipoPowerUp = tipoPowerUp;
        return this;
    }

    public ObjetoCaida construir() {
        if ("MALA".equals(tipo)) {
            return new FrutaMala(textura, x, y, puntos, velocidad, estrategiaCaida);
        }
        if ("ESPECIAL".equals(tipo)) {
            return new FrutaEspecial(textura, x, y, puntos, velocidad, estrategiaCaida);
        }
        if ("POWER_UP".equals(tipo)) {
            return new FrutaPowerUp(textura, x, y, puntos, velocidad, estrategiaCaida, tipoPowerUp);
        }
        return new FrutaBuena(textura, x, y, puntos, velocidad, estrategiaCaida);
    }
}
