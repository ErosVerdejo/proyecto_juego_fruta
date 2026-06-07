package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Administra la persistencia del puntaje maximo del juego.
 * Se usa Preferences de LibGDX para que el dato quede guardado entre ejecuciones.
 */
public class GestorPuntaje {
    private static GestorPuntaje instancia;
    private Preferences preferencias;
    private static final String CLAVE_PUNTAJE_MAXIMO = "puntajeMaximo";

    private GestorPuntaje() {
    }

    public static GestorPuntaje getInstancia() {
        if (instancia == null) {
            instancia = new GestorPuntaje();
        }
        return instancia;
    }

    private Preferences getPreferencias() {
        if (preferencias == null) {
            preferencias = Gdx.app.getPreferences("PerroRecolectorFrutalPuntajes");
        }
        return preferencias;
    }

    public int getPuntajeMaximo() {
        return getPreferencias().getInteger(CLAVE_PUNTAJE_MAXIMO, 0);
    }

    public void guardarSiEsMayor(int puntaje) {
        if (puntaje > getPuntajeMaximo()) {
            getPreferencias().putInteger(CLAVE_PUNTAJE_MAXIMO, puntaje);
            getPreferencias().flush();
        }
    }
}
