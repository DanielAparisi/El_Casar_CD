# 📋 Tareas de práctica — El Casar CD

Objetivo: practicar **Java, POO y herencia** de forma abundante. Toda la lógica del club (jugadores, equipos, partidos, clasificación...) debe vivir en clases Java bien diseñadas, usando las librerías estándar de Java (`java.util`, `java.time`, `java.util.stream`...).

Regla de oro: **el controlador no piensa, solo pide**. La lógica va en los modelos y servicios.

---

## 🟢 Nivel 1 — Fundamentos y encapsulación (repaso de lo que ya tienes)

### 1.1 Limpiar `Jugador`
- [ ] Elimina el método `public void jugador(...)` que está vacío (línea 21). No es un constructor, es un método mal nombrado que no hace nada.
- [ ] Corrige la errata `setPosiccion` → `setPosicion`.
- [ ] Añade un constructor vacío `public Jugador() {}` (JPA lo necesita para crear entidades).
- [ ] Sobrescribe `toString()` para que imprima algo como `"#9 Dani Aparisi (Delantero)"`.
  - *Concepto: **sobrescritura (override)** — redefinir un método heredado de `Object`.*
- [ ] Sobrescribe `equals()` y `hashCode()` basándote en el dorsal.
  - *Concepto: dos objetos distintos en memoria pueden ser "iguales" lógicamente. Necesario para que `List.contains()` y `Set` funcionen bien.*

### 1.2 Usar `java.time` en vez de `int edad`
- [ ] Cambia `int edad` por `LocalDate fechaNacimiento`.
- [ ] Haz que `getEdad()` **calcule** la edad con `Period.between(fechaNacimiento, LocalDate.now()).getYears()`.
  - *Concepto: un getter no tiene por qué devolver un campo tal cual; puede derivar información. La edad guardada como `int` se queda obsoleta cada cumpleaños.*

### 1.3 Validación en los setters
- [ ] En `setDorsal`, lanza `IllegalArgumentException` si el dorsal no está entre 1 y 99.
- [ ] En `setNombre`, lanza excepción si es `null` o está en blanco (usa `String.isBlank()`).
  - *Concepto: **encapsulación de verdad** — los setters protegen el estado interno, no solo asignan.*

### 1.4 Terminar `ListaJugadores`
- [ ] Implementa `agregarJugador(Jugador j)` que devuelva tus constantes `EXITO`, `ERROR_JugadoNULL` o `ERROR_DEMASIADOS_JUGADORES`.
- [ ] Implementa `buscarPorDorsal(int dorsal)` recorriendo el array con un `for`.
- [ ] Implementa `eliminarJugador(int dorsal)` (recuerda desplazar los elementos del array).
- [ ] Haz `numJugadores` **privado** y crea su getter.
  - *Concepto: practicar arrays "a mano" antes de pasarte a `ArrayList` te enseña qué hace `ArrayList` por dentro.*

---

## 🟡 Nivel 2 — Herencia y polimorfismo (el corazón de la práctica)

### 2.1 Jerarquía de personas del club
Crea una clase base y haz que varias clases hereden de ella:

```
Persona (abstracta)
├── Jugador
├── Entrenador
├── Arbitro
└── Directivo
```

- [ ] Crea `Persona` **abstracta** con: `nombre`, `apellido`, `fechaNacimiento`, `getNombreCompleto()`, `getEdad()`, `esMayorDeEdad()`.
- [ ] Haz que `Jugador extends Persona` y elimina los campos duplicados. Usa `super(...)` en el constructor.
- [ ] Crea `Entrenador` con campos propios: `licencia` (UEFA A, B...), `añosExperiencia`.
- [ ] Crea `Arbitro` con `categoria` y `partidosArbitrados`.
- [ ] Añade en `Persona` un método abstracto `String getRol();` y que cada hija lo implemente ("Jugador", "Entrenador"...).
  - *Concepto: una clase **abstracta** no se puede instanciar; obliga a las hijas a implementar sus métodos abstractos.*

### 2.2 Jerarquía de jugadores por posición
- [ ] Crea subclases de `Jugador`: `Portero`, `Defensa`, `Centrocampista`, `Delantero`.
- [ ] `Portero` tiene `paradas` y `porteriasACero`; `Delantero` tiene `goles` y `asistencias`, etc.
- [ ] En `Jugador`, crea un método `double calcularValoracion()` y **sobrescríbelo** en cada subclase con una fórmula distinta (el portero puntúa por paradas, el delantero por goles...).
- [ ] Demuestra el **polimorfismo**: guarda todos en una `List<Jugador>` y recorre llamando a `calcularValoracion()` — cada uno ejecutará SU versión.
  - *Concepto: **polimorfismo** — el mismo mensaje (`calcularValoracion()`), distinto comportamiento según la clase real del objeto.*
- [ ] Usa `instanceof` con *pattern matching* (`if (j instanceof Portero p)`) para sacar estadísticas específicas.

### 2.3 Interfaces
- [ ] Crea la interfaz `Convocable` con `boolean estaDisponible()` y `void convocar()`.
- [ ] Que `Jugador` y `Entrenador` la implementen (un jugador lesionado no está disponible; añade el campo `lesionado`).
- [ ] Crea la interfaz `Fichable` con `double getPrecioFichaje()`.
- [ ] Haz que `Jugador` implemente **las dos** interfaces a la vez.
  - *Concepto: Java no tiene herencia múltiple de clases, pero sí de interfaces. Una interfaz es un contrato: "quien me implemente, promete saber hacer esto".*
- [ ] Añade a `Convocable` un **método default** con una implementación común.

### 2.4 Clase `Equipo` (ahora mismo está vacía)
- [ ] Campos: `nombre`, `categoria`, `List<Jugador> plantilla`, `Entrenador entrenador`.
- [ ] Métodos: `ficharJugador(Jugador j)` (rechaza dorsales repetidos usando tu `equals()`), `darDeBaja(int dorsal)`, `getPlantilla()` (devuelve una copia con `List.copyOf()` para que nadie modifique la lista desde fuera).
- [ ] `convocarOnce()`: devuelve 11 jugadores disponibles, obligatoriamente 1 portero. Lanza una excepción propia si no se puede.
  - *Concepto: **composición** — un Equipo TIENE jugadores (composición) mientras que un Portero ES un jugador (herencia). Saber elegir entre ambas es clave en POO.*

### 2.5 Excepciones propias (herencia aplicada a errores)
- [ ] Crea `ClubException extends RuntimeException`.
- [ ] Crea hijas: `DorsalOcupadoException`, `PlantillaCompletaException`, `JugadorNoEncontradoException`.
- [ ] Sustituye los códigos de error de `ListaJugadores` (los `int` `EXITO`/`ERROR_...`) por estas excepciones.
  - *Concepto: las excepciones también forman jerarquías. Un `catch (ClubException e)` captura todas sus hijas — polimorfismo otra vez.*

### 2.6 Enums (mejores que Strings)
- [ ] Crea `enum Posicion { PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO }` y sustituye el `String posicion`.
- [ ] Dale al enum un campo `abreviatura` ("POR", "DEF"...) y un constructor.
- [ ] Crea `enum Categoria { BENJAMIN, ALEVIN, INFANTIL, CADETE, JUVENIL, SENIOR }` con un método `getEdadMaxima()`.
  - *Concepto: un enum en Java es una clase completa — puede tener campos, constructores y métodos. Evita errores de escritura como `"delnatero"`.*

---

## 🟠 Nivel 3 — Librerías de Java (Collections, Streams, Comparator)

### 3.1 Collections Framework
- [ ] Reescribe `ListaJugadores` internamente con `ArrayList` (mantén los mismos métodos públicos — así practicas que la **interfaz pública** no cambia aunque cambie la implementación).
- [ ] Usa un `Set<Integer>` para controlar dorsales ocupados.
- [ ] Usa un `Map<Posicion, List<Jugador>>` para agrupar la plantilla por posición.
- [ ] Usa `Optional<Jugador>` como retorno de `buscarPorDorsal` en vez de devolver `null`.
  - *Concepto: `Optional` te obliga a pensar en el caso "no encontrado" en vez de explotar con `NullPointerException`.*

### 3.2 Ordenación con Comparator y Comparable
- [ ] Haz que `Jugador implements Comparable<Jugador>` ordenando por dorsal.
- [ ] Crea `Comparator`s: por edad, por apellido, por valoración descendente. Usa `Comparator.comparing(...)` y `.thenComparing(...)`.
- [ ] Ordena la plantilla de las dos formas y entiende cuándo usar cada una.
  - *Concepto: `Comparable` = "mi orden natural"; `Comparator` = "un orden externo a la carta".*

### 3.3 Streams y lambdas
Con la plantilla del equipo, resuelve con **streams** (nada de bucles):
- [ ] Media de edad de la plantilla (`mapToInt` + `average`).
- [ ] El máximo goleador (`max` + `Comparator`).
- [ ] Lista de nombres de los menores de edad (`filter` + `map` + `toList`).
- [ ] Agrupar jugadores por posición (`Collectors.groupingBy`).
- [ ] Total de goles del equipo (`mapToInt` + `sum`).
- [ ] ¿Hay algún jugador lesionado? (`anyMatch`).
  - *Concepto: los streams son programación declarativa — dices QUÉ quieres, no CÓMO recorrerlo.*

### 3.4 Simulador de partidos (fundamentos + POO juntos)
- [ ] Clase `Partido`: `equipoLocal`, `equipoVisitante`, `LocalDateTime fecha`, `golesLocal`, `golesVisitante`, `jugado`.
- [ ] Método `simular()` usando `java.util.Random` (los goles dependen de la valoración media de cada equipo — reutiliza tu `calcularValoracion()` polimórfico).
- [ ] Clase `Clasificacion` que reciba una `List<Partido>` y calcule puntos (3/1/0), goles a favor/en contra, y ordene la tabla con un `Comparator` compuesto (puntos → diferencia de goles).
- [ ] Clase `Temporada` que genere el calendario de enfrentamientos (todos contra todos, ida y vuelta) con bucles anidados.
  - *Concepto: aquí juntas todo — composición, colecciones, streams y aleatoriedad.*

### 3.5 Utilidades varias de la librería estándar
- [ ] Usa `StringBuilder` para generar un informe de texto de la plantilla.
- [ ] Usa `String.format()` / `formatted()` para alinear la tabla de clasificación en columnas.
- [ ] Usa `DateTimeFormatter` para mostrar fechas de partido en formato español ("domingo 14 de septiembre").
- [ ] Guarda y lee la plantilla en un fichero `.csv` con `java.nio.file.Files` (`readAllLines` / `write`).

---

## 🔴 Nivel 4 — Integración con Spring y PostgreSQL

### 4.1 Herencia en JPA
- [ ] Anota `Persona` con `@MappedSuperclass` (o prueba `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` en la jerarquía de posiciones) y mira qué tablas crea Hibernate en PostgreSQL.
  - *Concepto: la herencia de Java se puede mapear a tablas de varias formas — verlo en la BD real ayuda mucho a entenderla.*
- [ ] Ojo: en `Jugador` tienes `@Id` sobre `nombre` con `GenerationType.AUTO` — un `String` no se autogenera. Crea un campo `Long id` como clave primaria.

### 4.2 Capas bien separadas
- [ ] Crea `JugadorRepository extends JpaRepository<Jugador, Long>`.
- [ ] Crea `JugadorService`: toda la lógica (validar dorsal libre, convocar...) va AQUÍ, no en el controlador.
- [ ] El controlador solo recibe la petición, llama al servicio y devuelve la vista.
  - *Concepto: separación de responsabilidades — cada clase tiene UN motivo para cambiar.*
- [ ] Renombra `paginaController` → `PaginaController` (las clases en Java siempre empiezan por mayúscula).

### 4.3 Mostrar la lógica en la web
- [ ] Página `/plantilla`: tabla con los jugadores desde PostgreSQL, ordenada con tus `Comparator`s.
- [ ] Página `/clasificacion`: la tabla calculada por tu clase `Clasificacion`.
- [ ] Formulario para fichar un jugador — si el dorsal está ocupado, captura tu `DorsalOcupadoException` y muestra el error en la vista.

---

## 🧪 Extra — Tests (muy recomendado)

- [ ] Test unitario de `getEdad()` con una fecha de nacimiento conocida.
- [ ] Test de que `ficharJugador` lanza `DorsalOcupadoException` con dorsal repetido (`assertThrows`).
- [ ] Test de que `convocarOnce()` siempre incluye un portero.
  - *Concepto: los tests te obligan a diseñar clases pequeñas y desacopladas — es práctica de POO encubierta.*

---

## 📌 Orden recomendado

1. Nivel 1 entero (rápido, asienta las bases).
2. Nivel 2 — dedícale tiempo, es donde más herencia y polimorfismo vas a practicar.
3. Nivel 3 — puedes probarlo todo desde un `main()` de pruebas o desde tests, sin tocar la web.
4. Nivel 4 — cuando la lógica ya funcione, conéctala a Spring y PostgreSQL.
