package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fruta especial de power up. Activa efectos temporales en la perro.
 */
public class FrutaPowerUp extends ObjetoCaida {
    private TipoPowerUp tipoPowerUp;

    public FrutaPowerUp(Texture textura, float x, float y, int puntos, float velocidad,
                        EstrategiaCaida estrategiaCaida, TipoPowerUp tipoPowerUp) {
        super(textura, x, y, puntos, velocidad, estrategiaCaida);
        this.tipoPowerUp = tipoPowerUp;
    }

    public TipoPowerUp getTipoPowerUp() {
        return tipoPowerUp;
    }

    @Override
    public void aplicarEfecto(PerroRecolector perro) {
        if (tipoPowerUp == TipoPowerUp.DOBLE_PUNTAJE) {
            perro.activarDoblePuntaje();
        } else if (tipoPowerUp == TipoPowerUp.CAMARA_LENTA) {
            perro.activarCamaraLenta();
        } else if (tipoPowerUp == TipoPowerUp.ESCUDO) {
            perro.activarEscudo();
        }
        perro.sumarPuntos(getPuntos());
        GestorRecursos.getInstancia().reproducirSonidoRecolectar();
    }
}
