### FPS (Frames per second)
FPS = 60 = 60 imagenes estaticas por segundo = cada imagen distinta va a hacer como si el personaje se estuviera moviendo

### Game Loop
Tareas principales dentro del game loop:
1. UPDATE: update character information (es la info del caracter que se va a ir actualizando a medida que el usuario haga acciones via inputs como teclado, mouse etc.) such as character position
2. DRAW: draw the screen with the updated information. (via metodo built-in en java llamado paintComponent(g) de JPanel que te permite pintar cosas en él)

Si vas a usar FPS=30 este loop (UPDATE+DRAW) se va a repetir 30 veces por segundo.

Existen dos tecnicas mas utilizadas para contruir game loops decentes.
Tecnica 1: Usando el metodo sleep(): se duerme el thread el tiempo pendiente posterior a que haya hecho el update y draw y antes del siguiente repintado.
Tecnica 2: Metodo de delta o tambien conocido como metodo accumulator.

```java
// GAME LOOP USANDO SLEEP
long drawInterval = ONE_SECOND_IN_NANO / FPS; // The allocated time for a single loop is 0.01666 seconds
while(running) {
            long nextDrawTime = System.nanoTime() + drawInterval;

            // 1. UPDATE: update character information sucha as character position
            firstLevelScene.update();
            // 2. DRAW: draw the screen with the updated information
            firstLevelScene.repaint();

            long remainingTime = nextDrawTime - System.nanoTime();
            long remainingTimeAsMilliseconds = remainingTime / 1000000;
            if(remainingTimeAsMilliseconds < 0) { // Este caso se puede dar si el metodo repaint le lleva mas tiempo del estimado para que se pinte dentro del ciclo de loop actaul entonces no lo vas a dormir al thread.
                remainingTimeAsMilliseconds = 0;
            }
            sleep(remainingTimeAsMilliseconds);
        }
```


```java
// GAME LOOP USANDO DELTA
double delta = 0;
        double drawInterval = ONE_SECOND_IN_NANO / FPS; // The allocated time for a single loop is 0.01666 seconds
        long lastTime = System.nanoTime();
        long currentTime;
        while(running) {
        currentTime = System.nanoTime();
        delta += (currentTime  - lastTime) / drawInterval;
        lastTime = currentTime;
        if(delta >= 1) {
        // 1. UPDATE: update character information sucha as character position
        firstLevelScene.update();
        // 2. DRAW: draw the screen with the updated information
        firstLevelScene.repaint();
        delta--;
        }
        }
```
Al implementar un gameloop en Java, tanto la técnica de sleep como la de delta time tienen sus ventajas y desventajas, pero la elección depende del tipo de juego y de tus necesidades.

1. Usar sleep() (Gameloop con espera fija):
   Este enfoque usa un ciclo que espera un tiempo fijo entre actualizaciones (por ejemplo, 16ms para 60 FPS). Se usa Thread.sleep() para controlar la velocidad del loop.

Ventajas:
Simplicidad: Fácil de implementar y entender.
Consistencia: El juego corre a una tasa fija de frames por segundo (FPS), lo que lo hace predecible.
Desventajas:
Inexactitud: Thread.sleep() no es 100% preciso, ya que la precisión del temporizador depende del sistema operativo. Esto puede generar ligeras variaciones en la tasa de FPS.
Eficiencia limitada: En sistemas más rápidos o lentos, el juego no se ajusta a las fluctuaciones de rendimiento, lo que puede llevar a retrasos o falta de fluidez.
2. Delta Time (Gameloop dependiente del tiempo):
   Esta técnica ajusta el comportamiento del juego basándose en el tiempo real transcurrido entre frames. En lugar de esperar un tiempo fijo, calculas el tiempo (delta) entre dos iteraciones del loop y escalas las actualizaciones del juego en función de ese delta.

Ventajas:
Escalabilidad: Se adapta a diferentes sistemas y cargas, lo que garantiza que el juego se mantenga fluido incluso si el FPS fluctúa.
Suavidad: El movimiento y las actualizaciones se escalan con el tiempo transcurrido, lo que permite animaciones más fluidas independientemente de la velocidad de la máquina.
Control preciso: Evita problemas de desincronización en hardware variado.
Desventajas:
Mayor complejidad: Necesitas manejar la integración del delta time en todos los aspectos del juego (movimientos, física, animaciones).
Riesgo de inestabilidad: Si el delta entre frames es demasiado grande (en caso de caídas severas de FPS), puede causar que los objetos se muevan de forma abrupta.
¿Cuál es mejor?
Para la mayoría de los juegos, especialmente si el hardware en el que se ejecutará el juego puede variar o si buscas fluidez en las animaciones, delta time es generalmente la mejor opción. Es más adaptable a diferentes circunstancias y ayuda a mantener el juego estable, independientemente de la velocidad de procesamiento.

Sin embargo, si estás haciendo un juego sencillo y no necesitas una gran precisión o adaptabilidad, el uso de sleep() puede ser suficiente.


### repaint() versus paintComponent()
paintComponent() es el metodo encargado de pintar el componente en pantalla con la configuracion que vos le definas.
Es propio de todos los componentes de swing. Se llama automaticamente, cuando se necesita renderizar el componente es decir
vos nunca vas a invocarlo directamente, sino lo que vos vas a usar es el metodo repaint() el cual te permite 
crear un evento de repintado es decir con esto dicho metodo repaint va a terminar llamando el mismo el paintComponent de mi
componente. 

### Objeto Graphics (y Graphics2D)
Es como tu lapiz o pincel para pintar sobre el lienzo. Por otro lado Graphics2D es una version mas sofisticada
con mas capacidades por ejemplo te provee mas control sobre figuras geometricas, coordinar transformaciones, manejo de colores
y layout de texto.

### Metodo dispose
Dispose el contexto grafico y libera cualquier recurso del sistema que lo esta usando.

### Si al mover las flechas tu character desparace de la pantalla
Esto pasa porque el cpu esta procesando millones de veces por segundo el gameloop entonces por mas que vos
apretes un toquecito la tecla eso implica que a la posicion actual le sume miles de pixeles por la velocidad en la 
que esta procesando el cpu el gameloop y te desaparece de la pantalla.
Solucion a esto setear que el gameloop se actualize a una tasa fija de FPS. Para ello necesitamos saber la
hora actual y que tiempo a pasado entre justo antes de llamarse al metodo update y luego inmediatamente de haber terminado
el metodo repaint().

### System.nanoTime() versus System.currentTimeMillis()
Ambos dan el tiempo actual de la jvm. (current system time)
El metodo nanoTime() (1.000.000.000 nanosegundos = 1 segundo) es mas preciso que el metodo currentTimeMillis() (1000 milisegundos = 1 segundo).

long drawInterval = ONE_SECOND_IN_NANO / FPS; // The allocated time for a single loop is 0.01666 seconds
Quiere decir que cada vuelta de loop le va a tomar 0.01666 segundos para actualizar y repintar teniendo en cuenta que lo configuraste a 60FPS.


## TODO List
Crear una clase con mas jerarquia tal como AnimatedBaseEntity el cual va a contener un AnimatedSpriteHandler
para que encapsule lo relacionado a BaseEntities que sean animadas y que de ahi herenden Player y Enemy