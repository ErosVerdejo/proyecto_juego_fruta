# PawFall Game 🐕🍎

**PawFall Game** es un videojuego 2D desarrollado en Java con LibGDX donde controlas a un perro granjero que debe recolectar frutas que caen desde la parte superior de la pantalla.
El objetivo es sobrevivir el mayor tiempo posible sin perder las 3 vidas disponibles, acumulando puntos para superar el récord histórico guardado en el dispositivo.

---

## Requisitos

- JDK 8 u 11 (Oracle JDK)
- IDE: NetBeans 21 (o inferior) o Eclipse
- LibGDX (incluido en el proyecto)

---

## Instrucciones de ejecución

Una vez descargado y descomprimido el proyecto, ábrelo con un IDE compatible con Java, como NetBeans o Eclipse. Siga los pasos según corresponda.

### IntelliJ

Una vez abierto el proyecto, es posible que salte una alerta para cargar el proyecto con Gradle. En ese caso, presione el botón para cargar el proyecto con Gradle.
Si no saltó la alerta, o ya cargó el proyecto con Gradle, ejecute **lwjgl3 → src → main → java → puppy.code.lwjgl3 → Lwjgl3Launcher**.

---

### Eclipse

Una vez abierto el proyecto, Eclipse descarga las dependencias automáticamente con Gradle. Espere a que terminen y luego ejecute **lwjgl3 → src → main → java → puppy.code.lwjgl3 → Lwjgl3Launcher**.

---

### NetBeans

Una vez abierto el proyecto, siga los pasos a continuación.

**Paso 1**

Haga clic derecho en la carpeta del proyecto, busque **Tasks** en el listado de opciones y seleccione **lwjgl3:run**.

**Paso 2**

En la ventana emergente que aparece, asegúrese de que solo esté marcada la opción **Recursive** y luego presione **OK**.

**Paso 3**

Si el proyecto no ejecuta directamente, navegue manualmente a **lwjgl3 → src → main → java → puppy.code.lwjgl3** y ejecute la clase **Lwjgl3Launcher**.

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

---

## Patrones de diseño implementados

- **Singleton** — `GestorRecursos` y `GestorPuntaje`: gestión centralizada de assets y persistencia del puntaje máximo.
- **Strategy** — `EstrategiaCaida`: tres estrategias de movimiento intercambiables (`CaidaNormal`, `CaidaRapida`, `CaidaZigZag`).
- **Template Method** — `Nivel`: estructura común para `NivelFacil` y `NivelDificil`, con transición automática a los 200 puntos.
- **Builder** — `FrutaBuilder`: construcción fluida de objetos `ObjetoCaida` con hasta 7 parámetros configurables.

---

## Contexto académico

Proyecto desarrollado para **INF2236 — Programación Avanzada** en la [Pontificia Universidad Católica de Valparaíso (PUCV)](https://www.pucv.cl), 4° semestre de Ingeniería en Informática durante el 2026.

Requisitos cubiertos dentro del proyecto: clases abstractas, interfaces, encapsulamiento, herencia, polimorfismo, patrones de diseño (Singleton, Strategy, Template Method, Builder), persistencia con Preferences de LibGDX y diagrama UML.

---

## Autores

<div align="center">

[![GitHub](https://img.shields.io/badge/@Noah--rhc-c0392b?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Noah-rhc)
[![GitHub](https://img.shields.io/badge/@ErosVerdejo-1e8449?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ErosVerdejo)

</div>
