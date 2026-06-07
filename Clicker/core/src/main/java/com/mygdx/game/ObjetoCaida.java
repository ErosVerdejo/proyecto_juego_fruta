package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

/**
 * Clase abstracta del dominio. Sirve como base para frutas buenas, malas y especiales.
 */
public abstract class ObjetoCaida {
    private Rectangle area;
    private Texture textura;
    private int puntos;
    private float velocidad;
    private EstrategiaCaida estrategiaCaida;
    private float direccionHorizontal;

    public ObjetoCaida(Texture textura, float x, float y, int puntos, float velocidad, EstrategiaCaida estrategiaCaida) {
        this.textura = textura;
        this.puntos = puntos;
        this.velocidad = velocidad;
        this.estrategiaCaida = estrategiaCaida;
        this.area = new Rectangle(x, y, 64, 64);
        this.direccionHorizontal = MathUtils.randomBoolean() ? 1f : -1f;
    }

    public Rectangle getArea() {
        return area;
    }

    public int getPuntos() {
        return puntos;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public float getDireccionHorizontal() {
        return direccionHorizontal;
    }

    public void setDireccionHorizontal(float direccionHorizontal) {
        this.direccionHorizontal = direccionHorizontal;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public void actualizar(float delta) {
        estrategiaCaida.caer(this, delta);
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, area.x, area.y);
    }

    public abstract void aplicarEfecto(PerroRecolector perro);
}
