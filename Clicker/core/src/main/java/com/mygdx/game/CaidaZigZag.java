package com.mygdx.game;

public class CaidaZigZag implements EstrategiaCaida {
    private static final float ANCHO_PANTALLA = 800f;
    private static final float VELOCIDAD_HORIZONTAL = 145f;

    @Override
    public void caer(ObjetoCaida objeto, float delta) {
        objeto.getArea().y -= objeto.getVelocidad() * delta;
        objeto.getArea().x += objeto.getDireccionHorizontal() * VELOCIDAD_HORIZONTAL * delta;

        if (objeto.getArea().x <= 0) {
            objeto.getArea().x = 0;
            objeto.setDireccionHorizontal(1f);
        }

        if (objeto.getArea().x >= ANCHO_PANTALLA - objeto.getArea().width) {
            objeto.getArea().x = ANCHO_PANTALLA - objeto.getArea().width;
            objeto.setDireccionHorizontal(-1f);
        }
    }
}
