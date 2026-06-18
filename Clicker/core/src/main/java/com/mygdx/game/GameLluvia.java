package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Juego principal: Perro Frutal.
 * Incluye menu inicial, partida, dos finales y persistencia del puntaje maximo.
 */
public class GameLluvia extends ApplicationAdapter {
    private enum EstadoJuego {
        MENU,
        JUGANDO,
        FINAL
    }

    private static final float ANCHO_PANTALLA = 800f;
    private static final float ALTO_PANTALLA = 480f;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontGrande;
    private GlyphLayout layout;

    private PerroRecolector perro;
    private Lluvia lluvia;
    private Nivel nivelActual;
    private EstadoJuego estadoJuego;

    private boolean finalProcesado;
    private boolean finalAprobaste;
    private int puntajeObtenidoFinal;
    private int recordAnteriorFinal;
    private int recordActualFinal;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ANCHO_PANTALLA, ALTO_PANTALLA);

        batch = new SpriteBatch();
        font = new BitmapFont();
        fontGrande = new BitmapFont();
        fontGrande.getData().setScale(2f);
        layout = new GlyphLayout();

        font.setColor(Color.BLACK);
        fontGrande.setColor(Color.BLACK);

        GestorRecursos.getInstancia().cargarRecursos();
        estadoJuego = EstadoJuego.MENU;
    }

    private void iniciarJuego() {
        perro = new PerroRecolector(GestorRecursos.getInstancia().getTexturaPerroRecolector());
        perro.crear();

        nivelActual = new NivelFacil();
        nivelActual.iniciarNivel();

        lluvia = new Lluvia(nivelActual);
        lluvia.crear();

        finalProcesado = false;
        finalAprobaste = false;
        puntajeObtenidoFinal = 0;
        recordAnteriorFinal = 0;
        recordActualFinal = 0;
        estadoJuego = EstadoJuego.JUGANDO;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.45f, 0.75f, 0.95f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (estadoJuego == EstadoJuego.MENU) {
            actualizarMenu();
        } else if (estadoJuego == EstadoJuego.JUGANDO) {
            actualizarJuego();
        } else if (estadoJuego == EstadoJuego.FINAL) {
            actualizarFinal();
        }

        batch.begin();
        dibujarFondo();
        if (estadoJuego == EstadoJuego.MENU) {
            dibujarMenu();
        } else if (estadoJuego == EstadoJuego.JUGANDO) {
            dibujarJuego();
        } else if (estadoJuego == EstadoJuego.FINAL) {
            dibujarFinal();
        }
        batch.end();
    }

    private void actualizarMenu() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            iniciarJuego();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void actualizarJuego() {
        perro.actualizarMovimiento();
        perro.actualizarPowerUps(Gdx.graphics.getDeltaTime());
        lluvia.actualizarMovimiento(perro);

        if (perro.getPuntos() >= 200 && !(nivelActual instanceof NivelDificil)) {
            nivelActual = new NivelDificil();
            nivelActual.iniciarNivel();
            lluvia.setNivel(nivelActual);
        }

        if (perro.getVidas() <= 0) {
            finalizarPartida();
        }
    }

    /**
     * Metodo central para decidir el final del juego.
     *
     * Regla solicitada:
     * - Si no existe record previo, la primera partida muestra el final bueno: APROBASTE.
     * - Si el puntaje obtenido supera el record anterior, muestra APROBASTE y guarda el nuevo record.
     * - Si el puntaje obtenido no supera el record anterior, muestra REPROBASTE.
     */
    private void finalizarPartida() {
        if (finalProcesado) {
            return;
        }

        puntajeObtenidoFinal = perro.getPuntos();
        recordAnteriorFinal = GestorPuntaje.getInstancia().getPuntajeMaximo();
        finalAprobaste = debeMostrarFinalAprobaste(puntajeObtenidoFinal, recordAnteriorFinal);

        if (finalAprobaste) {
            GestorPuntaje.getInstancia().guardarSiEsMayor(puntajeObtenidoFinal);
        }

        recordActualFinal = GestorPuntaje.getInstancia().getPuntajeMaximo();
        finalProcesado = true;
        estadoJuego = EstadoJuego.FINAL;
    }

    private boolean debeMostrarFinalAprobaste(int puntajeObtenido, int recordAnterior) {
        return recordAnterior == 0 || puntajeObtenido > recordAnterior;
    }

    private void actualizarFinal() {
        if (!finalProcesado && perro != null) {
            finalizarPartida();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (lluvia != null) {
                lluvia.destruir();
            }
            iniciarJuego();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            if (lluvia != null) {
                lluvia.destruir();
            }
            estadoJuego = EstadoJuego.MENU;
        }
    }

    private void dibujarFondo() {
        if (estadoJuego == EstadoJuego.FINAL) {
            if (finalAprobaste) {
                batch.draw(GestorRecursos.getInstancia().getTexturaFinalAprobaste(), 0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
            } else {
                batch.draw(GestorRecursos.getInstancia().getTexturaFinalReprobaste(), 0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
            }
        } else {
            batch.draw(GestorRecursos.getInstancia().getTexturaFondoGranja(), 0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
        }
    }

    private void dibujarMenu() {
        fontGrande.setColor(Color.BLACK);
        font.setColor(Color.BLACK);

        dibujarTextoCentrado(fontGrande, "Puppy Fruit", 390);
        dibujarTextoCentrado(font, "Recolecta frutas, usa power ups y evita la fruta podrida", 335);
        dibujarTextoCentrado(font, "Power ups: guinda 10%, kiwi 7%, durian 5%, dorada 2%", 315);
        dibujarTextoCentrado(font, "PUNTAJE MAXIMO: " + GestorPuntaje.getInstancia().getPuntajeMaximo(), 280);
        dibujarTextoCentrado(font, "[ ENTER / ESPACIO ] JUGAR", 235);
        dibujarTextoCentrado(font, "[ ESC ] SALIR", 215);
        dibujarTextoCentrado(font, "Controles: flecha izquierda y flecha derecha", 160);
    }

    private void dibujarJuego() {
        font.setColor(Color.BLACK);

        dibujarTextoCentrado(font, "Puppy Fruit", 475);
        font.draw(batch, "Puntos: " + perro.getPuntos(), 15, 475);
        font.draw(batch, "Maximo: " + GestorPuntaje.getInstancia().getPuntajeMaximo(), 15, 450);
        dibujarVidasConCorazones();
        dibujarPowerUpsActivos();
        dibujarTextoCentrado(font, "Nivel: " + nivelActual.getNombre(), 50);
        dibujarTextoCentrado(font, "<-  ->  mover perro | evita frutas podridas", 25);

        perro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
    }

    private void dibujarVidasConCorazones() {
        int vidas = perro.getVidas();
        float tamanoCorazon = 32f;
        float separacion = 8f;
        float totalAncho = vidas * tamanoCorazon + Math.max(0, vidas - 1) * separacion;
        float xInicial = ANCHO_PANTALLA - totalAncho - 15f;
        float y = ALTO_PANTALLA - tamanoCorazon - 12f;

        for (int i = 0; i < vidas; i++) {
            float x = xInicial + i * (tamanoCorazon + separacion);
            batch.draw(GestorRecursos.getInstancia().getTexturaCorazon(), x, y, tamanoCorazon, tamanoCorazon);
        }
    }

    private void dibujarPowerUpsActivos() {
        float y = 420f;
        if (perro.isDoblePuntajeActivo()) {
            font.draw(batch, "x2 puntaje: " + (int) Math.ceil(perro.getTiempoDoblePuntaje()) + "s", 15, y);
            y -= 22f;
        }
        if (perro.isCamaraLentaActiva()) {
            font.draw(batch, "Camara lenta: " + (int) Math.ceil(perro.getTiempoCamaraLenta()) + "s", 15, y);
            y -= 22f;
        }
        if (perro.isEscudoActivo()) {
            font.draw(batch, "Escudo activo: " + (int) Math.ceil(perro.getTiempoEscudo()) + "s", 15, y);
        }
    }

    private void dibujarFinal() {
        fontGrande.setColor(Color.BLACK);
        font.setColor(Color.BLACK);

        // Las imagenes ya contienen los titulos principales.
        // Aqui se dibuja dinamicamente el puntaje dentro del cuadro inferior.
        fontGrande.getData().setScale(2.3f);
        dibujarTextoCentrado(fontGrande, String.valueOf(puntajeObtenidoFinal), 72);
        fontGrande.getData().setScale(2f);

        if (finalAprobaste) {
            dibujarTextoCentrado(font, "Nuevo record: " + recordActualFinal, 118);
        } else {
            dibujarTextoCentrado(font, "Puntaje obtenido: " + puntajeObtenidoFinal + " | Record: " + recordActualFinal, 118);
        }

        dibujarTextoCentrado(font, "[ ENTER / ESPACIO ] JUGAR DE NUEVO", 35);
        dibujarTextoCentrado(font, "[ M ] VOLVER AL MENU", 18);
    }

    private void dibujarTextoCentrado(BitmapFont fuente, String texto, float y) {
        layout.setText(fuente, texto);
        float x = (ANCHO_PANTALLA - layout.width) / 2f;
        fuente.draw(batch, texto, x, y);
    }

    @Override
    public void dispose() {
        if (lluvia != null) lluvia.destruir();
        if (perro != null) perro.destruir();
        GestorRecursos.getInstancia().destruir();
        batch.dispose();
        font.dispose();
        fontGrande.dispose();
    }
}
