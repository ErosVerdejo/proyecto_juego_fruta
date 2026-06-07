package com.mygdx.game;

/**
 * Patrón Template Method: define los pasos obligatorios para configurar un nivel.
 */
public abstract class Nivel {
    private String nombre;
    private float velocidadBase;
    private long intervaloGeneracion;
    private int probabilidadFrutaMala;
    private int probabilidadFrutaEspecial;

    public final void iniciarNivel() {
        definirNombre();
        definirVelocidadBase();
        definirIntervaloGeneracion();
        definirProbabilidades();
    }

    protected abstract void definirNombre();
    protected abstract void definirVelocidadBase();
    protected abstract void definirIntervaloGeneracion();
    protected abstract void definirProbabilidades();

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getVelocidadBase() {
        return velocidadBase;
    }

    protected void setVelocidadBase(float velocidadBase) {
        this.velocidadBase = velocidadBase;
    }

    public long getIntervaloGeneracion() {
        return intervaloGeneracion;
    }

    protected void setIntervaloGeneracion(long intervaloGeneracion) {
        this.intervaloGeneracion = intervaloGeneracion;
    }

    public int getProbabilidadFrutaMala() {
        return probabilidadFrutaMala;
    }

    protected void setProbabilidadFrutaMala(int probabilidadFrutaMala) {
        this.probabilidadFrutaMala = probabilidadFrutaMala;
    }

    public int getProbabilidadFrutaEspecial() {
        return probabilidadFrutaEspecial;
    }

    protected void setProbabilidadFrutaEspecial(int probabilidadFrutaEspecial) {
        this.probabilidadFrutaEspecial = probabilidadFrutaEspecial;
    }
}
