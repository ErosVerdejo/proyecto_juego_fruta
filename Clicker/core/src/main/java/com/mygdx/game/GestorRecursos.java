package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Patrón Singleton: centraliza la carga de imágenes, sonidos y música.
 */
public class GestorRecursos {
    private static GestorRecursos instancia;

    private Texture texturaPerroRecolector;
    private Texture texturaManzana;
    private Texture texturaPlatano;
    private Texture texturaNaranja;
    private Texture texturaPodrida;
    private Texture texturaDorada;
    private Texture texturaGuinda;
    private Texture texturaKiwi;
    private Texture texturaDurian;
    private Texture texturaCorazon;
    private Texture texturaFondoGranja;
    private Texture texturaFinalAprobaste;
    private Texture texturaFinalReprobaste;
    private Sound sonidoRecolectar;
    private Sound sonidoFrutaPodrida;
    private Music musicaFondo;
    private boolean cargado;

    private GestorRecursos() {
        cargado = false;
    }

    public static GestorRecursos getInstancia() {
        if (instancia == null) {
            instancia = new GestorRecursos();
        }
        return instancia;
    }

    public void cargarRecursos() {
        if (cargado) {
            return;
        }
        texturaPerroRecolector = new Texture(Gdx.files.internal("perro_recolector.png"));
        texturaManzana = new Texture(Gdx.files.internal("manzana.png"));
        texturaPlatano = new Texture(Gdx.files.internal("platano.png"));
        texturaNaranja = new Texture(Gdx.files.internal("naranja.png"));
        texturaPodrida = new Texture(Gdx.files.internal("fruta_podrida.png"));
        texturaDorada = new Texture(Gdx.files.internal("fruta_dorada.png"));
        texturaGuinda = new Texture(Gdx.files.internal("guinda.png"));
        texturaKiwi = new Texture(Gdx.files.internal("kiwi.png"));
        texturaDurian = new Texture(Gdx.files.internal("durian.png"));
        texturaCorazon = new Texture(Gdx.files.internal("corazon.png"));
        texturaFondoGranja = new Texture(Gdx.files.internal("fondo_granja.png"));
        texturaFinalAprobaste = new Texture(Gdx.files.internal("final_aprobaste.png"));
        texturaFinalReprobaste = new Texture(Gdx.files.internal("final_reprobaste.png"));
        sonidoRecolectar = Gdx.audio.newSound(Gdx.files.internal("sonido_mordida.wav"));
        sonidoFrutaPodrida = Gdx.audio.newSound(Gdx.files.internal("sonido_podrida.mp3"));

        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("musica_fondo.mp3"));
        musicaFondo.setLooping(true);
        musicaFondo.setVolume(0.22f);
        musicaFondo.play();

        precalentarSonidos();
        cargado = true;
    }

    public Texture getTexturaPerroRecolector() {
        return texturaPerroRecolector;
    }

    public Texture getTexturaManzana() {
        return texturaManzana;
    }

    public Texture getTexturaPlatano() {
        return texturaPlatano;
    }

    public Texture getTexturaNaranja() {
        return texturaNaranja;
    }

    public Texture getTexturaPodrida() {
        return texturaPodrida;
    }

    public Texture getTexturaDorada() {
        return texturaDorada;
    }

    public Texture getTexturaGuinda() {
        return texturaGuinda;
    }

    public Texture getTexturaKiwi() {
        return texturaKiwi;
    }

    public Texture getTexturaDurian() {
        return texturaDurian;
    }

    public Texture getTexturaCorazon() {
        return texturaCorazon;
    }

    public Texture getTexturaFondoGranja() {
        return texturaFondoGranja;
    }


    public Texture getTexturaFinalAprobaste() {
        return texturaFinalAprobaste;
    }

    public Texture getTexturaFinalReprobaste() {
        return texturaFinalReprobaste;
    }

    public Sound getSonidoRecolectar() {
        return sonidoRecolectar;
    }

    public Sound getSonidoFrutaPodrida() {
        return sonidoFrutaPodrida;
    }

    /**
     * Reproduce el sonido de fruta normal/especial.
     * Se usa WAV para evitar el retardo típico de efectos cortos en MP3.
     */
    public void reproducirSonidoRecolectar() {
        sonidoRecolectar.play(0.75f);
    }

    /**
     * Reproduce el sonido de error/daño cuando se consume una fruta podrida.
     */
    public void reproducirSonidoFrutaPodrida() {
        sonidoFrutaPodrida.play(0.85f);
    }

    /**
     * Reproduce cada efecto en volumen 0 apenas se cargan.
     * Esto fuerza al motor de audio a dejarlos listos y reduce el retraso
     * de la primera reproducción durante la partida.
     */
    private void precalentarSonidos() {
        long idRecolectar = sonidoRecolectar.play(0f);
        sonidoRecolectar.stop(idRecolectar);

        long idPodrida = sonidoFrutaPodrida.play(0f);
        sonidoFrutaPodrida.stop(idPodrida);
    }


    public void destruir() {
        if (!cargado) {
            return;
        }
        texturaPerroRecolector.dispose();
        texturaManzana.dispose();
        texturaPlatano.dispose();
        texturaNaranja.dispose();
        texturaPodrida.dispose();
        texturaDorada.dispose();
        texturaGuinda.dispose();
        texturaKiwi.dispose();
        texturaDurian.dispose();
        texturaCorazon.dispose();
        texturaFondoGranja.dispose();
        texturaFinalAprobaste.dispose();
        texturaFinalReprobaste.dispose();
        sonidoRecolectar.dispose();
        sonidoFrutaPodrida.dispose();
        musicaFondo.dispose();
        cargado = false;
        instancia = null;
    }
}
