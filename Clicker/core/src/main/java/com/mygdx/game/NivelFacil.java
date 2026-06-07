package com.mygdx.game;

public class NivelFacil extends Nivel {
    @Override
    protected void definirNombre() {
        setNombre("Facil");
    }

    @Override
    protected void definirVelocidadBase() {
        setVelocidadBase(260f);
    }

    @Override
    protected void definirIntervaloGeneracion() {
        setIntervaloGeneracion(260000000L);
    }

    @Override
    protected void definirProbabilidades() {
        setProbabilidadFrutaMala(22);
        setProbabilidadFrutaEspecial(8);
    }
}
