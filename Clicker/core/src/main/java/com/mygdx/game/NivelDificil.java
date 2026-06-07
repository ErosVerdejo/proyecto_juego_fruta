package com.mygdx.game;

public class NivelDificil extends Nivel {
    @Override
    protected void definirNombre() {
        setNombre("Dificil");
    }

    @Override
    protected void definirVelocidadBase() {
        setVelocidadBase(360f);
    }

    @Override
    protected void definirIntervaloGeneracion() {
        setIntervaloGeneracion(170000000L);
    }

    @Override
    protected void definirProbabilidades() {
        setProbabilidadFrutaMala(34);
        setProbabilidadFrutaEspecial(6);
    }
}
