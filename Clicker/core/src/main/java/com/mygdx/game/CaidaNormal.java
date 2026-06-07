package com.mygdx.game;

public class CaidaNormal implements EstrategiaCaida {
    @Override
    public void caer(ObjetoCaida objeto, float delta) {
        objeto.getArea().y -= objeto.getVelocidad() * delta;
    }
}
