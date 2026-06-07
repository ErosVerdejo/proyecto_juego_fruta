package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Representa al jugador. El perro recolector reemplaza a la perro: mueve su tractor y recolecta frutas con la cesta.
 */
public class PerroRecolector {
    private static final int VIDAS_MAXIMAS = 3;
    private static final float DURACION_DOBLE_PUNTAJE = 8f;
    private static final float DURACION_CAMARA_LENTA = 6f;
    private static final float DURACION_ESCUDO = 8f;

    private Rectangle area;
    private Texture textura;
    private int vidas;
    private int puntos;
    private int velocidadX;
    private boolean herido;
    private int tiempoHeridoMax;
    private int tiempoHerido;

    private boolean doblePuntajeActivo;
    private boolean camaraLentaActiva;
    private boolean escudoActivo;
    private float tiempoDoblePuntaje;
    private float tiempoCamaraLenta;
    private float tiempoEscudo;

    public PerroRecolector(Texture textura) {
        this.textura = textura;
        this.vidas = VIDAS_MAXIMAS;
        this.puntos = 0;
        this.velocidadX = 420;
        this.herido = false;
        this.tiempoHeridoMax = 50;
        this.doblePuntajeActivo = false;
        this.camaraLentaActiva = false;
        this.escudoActivo = false;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return area;
    }

    public boolean estaHerido() {
        return herido;
    }

    public boolean isDoblePuntajeActivo() {
        return doblePuntajeActivo;
    }

    public boolean isCamaraLentaActiva() {
        return camaraLentaActiva;
    }

    public boolean isEscudoActivo() {
        return escudoActivo;
    }

    public float getTiempoDoblePuntaje() {
        return tiempoDoblePuntaje;
    }

    public float getTiempoCamaraLenta() {
        return tiempoCamaraLenta;
    }

    public float getTiempoEscudo() {
        return tiempoEscudo;
    }

    public float getFactorVelocidadCaida() {
        if (camaraLentaActiva) {
            return 0.45f;
        }
        return 1f;
    }

    public void crear() {
        area = new Rectangle();
        area.width = 150;
        area.height = 120;
        area.x = 800 / 2f - area.width / 2f;
        area.y = 10;
    }

    public void sumarPuntos(int puntos) {
        int puntosFinales = puntos;
        if (puntos > 0 && doblePuntajeActivo) {
            puntosFinales = puntos * 2;
        }
        this.puntos += puntosFinales;
        if (this.puntos < 0) {
            this.puntos = 0;
        }
    }

    public void recuperarVida() {
        if (vidas < VIDAS_MAXIMAS) {
            vidas++;
        }
    }

    public void daniar() {
        if (escudoActivo) {
            escudoActivo = false;
            tiempoEscudo = 0f;
            GestorRecursos.getInstancia().reproducirSonidoRecolectar();
            return;
        }

        if (vidas > 0) {
            vidas--;
        }
        if (vidas < 0) {
            vidas = 0;
        }
        herido = true;
        tiempoHerido = tiempoHeridoMax;
    }

    public void activarDoblePuntaje() {
        doblePuntajeActivo = true;
        tiempoDoblePuntaje = DURACION_DOBLE_PUNTAJE;
    }

    public void activarCamaraLenta() {
        camaraLentaActiva = true;
        tiempoCamaraLenta = DURACION_CAMARA_LENTA;
    }

    public void activarEscudo() {
        escudoActivo = true;
        tiempoEscudo = DURACION_ESCUDO;
    }

    public void actualizarPowerUps(float delta) {
        if (doblePuntajeActivo) {
            tiempoDoblePuntaje -= delta;
            if (tiempoDoblePuntaje <= 0f) {
                doblePuntajeActivo = false;
                tiempoDoblePuntaje = 0f;
            }
        }

        if (camaraLentaActiva) {
            tiempoCamaraLenta -= delta;
            if (tiempoCamaraLenta <= 0f) {
                camaraLentaActiva = false;
                tiempoCamaraLenta = 0f;
            }
        }

        if (escudoActivo) {
            tiempoEscudo -= delta;
            if (tiempoEscudo <= 0f) {
                escudoActivo = false;
                tiempoEscudo = 0f;
            }
        }
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
            batch.draw(textura, area.x, area.y, area.width, area.height);
        } else {
            batch.draw(textura, area.x, area.y + MathUtils.random(-5, 5), area.width, area.height);
            tiempoHerido--;
            if (tiempoHerido <= 0) {
                herido = false;
            }
        }
    }

    public void actualizarMovimiento() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            area.x -= velocidadX * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            area.x += velocidadX * Gdx.graphics.getDeltaTime();
        }
        if (area.x < 0) {
            area.x = 0;
        }
        if (area.x > 800 - area.width) {
            area.x = 800 - area.width;
        }
    }

    public void destruir() {
        // La textura y sonidos son administrados por GestorRecursos.
    }
}
