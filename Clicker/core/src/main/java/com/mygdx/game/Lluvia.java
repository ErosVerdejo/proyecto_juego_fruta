package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Administra la generación, movimiento, colisiones y dibujo de las frutas.
 */
public class Lluvia {
    private static final int PROBABILIDAD_DOBLE_PUNTAJE = 10;
    private static final int PROBABILIDAD_CAMARA_LENTA = 7;
    private static final int PROBABILIDAD_ESCUDO = 5;
    private static final int PROBABILIDAD_DORADA = 2;
    private static final int PROBABILIDAD_PODRIDA = 30;

    private Array<ObjetoCaida> frutas;
    private long ultimoTiempoFruta;
    private Nivel nivel;

    public Lluvia(Nivel nivel) {
        this.nivel = nivel;
    }

    public void crear() {
        frutas = new Array<ObjetoCaida>();
        crearFruta();
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    private void crearFruta() {
        int probabilidad = MathUtils.random(1, 100);

        String tipo = "BUENA";
        Texture textura = obtenerTexturaFrutaBuena();
        int puntos = MathUtils.random(10, 25);
        EstrategiaCaida estrategia = new CaidaNormal();
        TipoPowerUp tipoPowerUp = null;

        if (probabilidad <= PROBABILIDAD_DOBLE_PUNTAJE) {
            tipo = "POWER_UP";
            textura = GestorRecursos.getInstancia().getTexturaGuinda();
            puntos = 0;
            estrategia = new CaidaZigZag();
            tipoPowerUp = TipoPowerUp.DOBLE_PUNTAJE;
        } else if (probabilidad <= PROBABILIDAD_DOBLE_PUNTAJE + PROBABILIDAD_CAMARA_LENTA) {
            tipo = "POWER_UP";
            textura = GestorRecursos.getInstancia().getTexturaKiwi();
            puntos = 0;
            estrategia = new CaidaZigZag();
            tipoPowerUp = TipoPowerUp.CAMARA_LENTA;
        } else if (probabilidad <= PROBABILIDAD_DOBLE_PUNTAJE + PROBABILIDAD_CAMARA_LENTA + PROBABILIDAD_ESCUDO) {
            tipo = "POWER_UP";
            textura = GestorRecursos.getInstancia().getTexturaDurian();
            puntos = 0;
            estrategia = new CaidaZigZag();
            tipoPowerUp = TipoPowerUp.ESCUDO;
        } else if (probabilidad <= PROBABILIDAD_DOBLE_PUNTAJE + PROBABILIDAD_CAMARA_LENTA + PROBABILIDAD_ESCUDO + PROBABILIDAD_DORADA) {
            tipo = "ESPECIAL";
            textura = GestorRecursos.getInstancia().getTexturaDorada();
            puntos = 50;
            estrategia = new CaidaZigZag();
        } else if (probabilidad <= PROBABILIDAD_DOBLE_PUNTAJE + PROBABILIDAD_CAMARA_LENTA + PROBABILIDAD_ESCUDO + PROBABILIDAD_DORADA + PROBABILIDAD_PODRIDA) {
            tipo = "MALA";
            textura = GestorRecursos.getInstancia().getTexturaPodrida();
            puntos = -10;
            estrategia = new CaidaRapida();
        }

        ObjetoCaida fruta = new FrutaBuilder()
                .conTipo(tipo)
                .conTipoPowerUp(tipoPowerUp)
                .conTextura(textura)
                .conPosicion(MathUtils.random(0, 800 - 64), 480)
                .conPuntos(puntos)
                .conVelocidad(nivel.getVelocidadBase())
                .conEstrategia(estrategia)
                .construir();

        frutas.add(fruta);
        ultimoTiempoFruta = TimeUtils.nanoTime();
    }

    private Texture obtenerTexturaFrutaBuena() {
        int fruta = MathUtils.random(1, 3);
        if (fruta == 1) {
            return GestorRecursos.getInstancia().getTexturaManzana();
        }
        if (fruta == 2) {
            return GestorRecursos.getInstancia().getTexturaPlatano();
        }
        return GestorRecursos.getInstancia().getTexturaNaranja();
    }

    public void actualizarMovimiento(PerroRecolector perro) {
        if (TimeUtils.nanoTime() - ultimoTiempoFruta > nivel.getIntervaloGeneracion()) {
            crearFruta();
        }

        float delta = Gdx.graphics.getDeltaTime() * perro.getFactorVelocidadCaida();

        for (int i = frutas.size - 1; i >= 0; i--) {
            ObjetoCaida fruta = frutas.get(i);
            fruta.actualizar(delta);

            if (fruta.getArea().y + fruta.getArea().height < 0) {
                frutas.removeIndex(i);
            } else if (fruta.getArea().overlaps(perro.getArea())) {
                fruta.aplicarEfecto(perro);
                frutas.removeIndex(i);
            }
        }
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (ObjetoCaida fruta : frutas) {
            fruta.dibujar(batch);
        }
    }

    public void destruir() {
        if (frutas != null) {
            frutas.clear();
        }
    }
}
