package com.mygdx.game;

public class CaidaRapida implements EstrategiaCaida {
    @Override
    public void caer(ObjetoCaida objeto, float delta) {
        objeto.getArea().y -= (objeto.getVelocidad() * 1.55f) * delta;
    }
}
