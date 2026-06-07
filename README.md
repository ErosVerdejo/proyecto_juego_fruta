# Puppy Fruit 🐕🍎

**Puppy Fruit** es un videojuego 2D desarrollado en Java con LibGDX donde controlas a un perro granjero que debe recolectar frutas que caen desde la parte superior de la pantalla.
El objetivo es sobrevivir el mayor tiempo posible sin perder las 3 vidas disponibles, acumulando puntos para superar el récord histórico guardado en el dispositivo.

---

## Requisitos

- JDK 8 u 11 (Oracle JDK)
- IDE: Eclipse
- LibGDX (incluido en el proyecto)

---

## Instrucciones de ejecución

Una vez descargado y descomprimido el proyecto, ábrelo con un IDE compatible con Java, como NetBeans o Eclipse. Siga los pasos según corresponda.

---

### Eclipse

Una vez abierto el proyecto, Eclipse descarga las dependencias automáticamente con Gradle. Espere a que terminen y luego ejecute **lwjgl3 → src → main → java → com → mygdx → game → lwjgl3 → Lwjgl3Launcher**.

---

## Controles

| Tecla | Acción |
|-------|--------|
| ← → | Mover al perro recolector |
| ENTER / ESPACIO | Iniciar o reiniciar partida |
| M | Volver al menú principal |
| ESC | Salir del juego |

---

## Tipos de frutas

| Fruta | Tipo | Efecto |
|-------|------|--------|
| 🍎 Manzana / 🍌 Plátano / 🍊 Naranja | Normal | +10 a +25 puntos |
| 🍒 Guinda | Power-up | Doble puntaje por 8 segundos |
| 🥝 Kiwi | Power-up | Cámara lenta por 6 segundos |
| 🥭 Durián | Power-up | Escudo protector por 8 segundos |
| ⭐ Fruta dorada | Especial | +50 puntos y recupera 1 vida |
| 🔴 Fruta podrida | Mala | -1 vida y -10 puntos |


## Patrones de diseño implementados

- **Singleton** — `GestorRecursos` y `GestorPuntaje`: gestión centralizada de assets y persistencia del puntaje máximo.
- **Strategy** — `EstrategiaCaida`: tres estrategias de movimiento intercambiables (`CaidaNormal`, `CaidaRapida`, `CaidaZigZag`).
- **Template Method** — `Nivel`: estructura común para `NivelFacil` y `NivelDificil`, con transición automática a los 200 puntos.
- **Builder** — `FrutaBuilder`: construcción fluida de objetos `ObjetoCaida` con hasta 7 parámetros configurables.

## Capturas
<img width="1918" height="1107" alt="image" src="https://github.com/user-attachments/assets/c962d5b0-f02b-43ba-933f-be0739b6f402" />
<img width="1732" height="1028" alt="image" src="https://github.com/user-attachments/assets/21d3b1d7-790c-44f5-acc2-add4282ef700" />
<img width="1732" height="1028" alt="image" src="https://github.com/user-attachments/assets/a451d1e4-4643-48a6-8d22-ea93f51dff43" />


## Autores
Eros Verdejo
Isaias Rojas
Ariel Ponce


</div>
