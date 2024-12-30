import java.util.Random;
import java.util.Scanner;

public class Juego2 {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Scanner scanner;
    private Jugador jugadorActual;

    public Juego2() {
        this.tablero = new Tablero();
        this.scanner = new Scanner(System.in);
    }

    public static int contadorVictoriasJugador1 = 0;
    public static int contadorVictoriasJugador2 = 0;
    public static int contadorEmpates = 0;
    public static int partidasJugadas = 0;
    public static int numeroDePartidasObjetivo = 0;

    public void mostrarMenu() {

        String red = "\033[31m";
        String terminaColor = "\u001B[0m";

        System.out.println(red + "Menu:" + terminaColor);
        System.out.println("1. Elegir modalidad");
        System.out.println("2. Configuracion");
        System.out.println("3. Jugar");
        System.out.println("4. Creditos");
        int eleccion;
        Scanner teclado = new Scanner(System.in);
        eleccion = teclado.nextInt();
        switch (eleccion) {
            case 1:
                System.out.println("Elegir modalidad");
                configurarJugadores();
                mostrarMenu();
                break;
            case 2:
                System.out.println("configuracion");
                System.out.println("1. Seleccionar el color de los jugadores");
                System.out.println("2. Seleccionar número de partidas");
                System.out.println("3. Seleccionar orden de jugadores");
                System.out.println("4. Salir");

                int opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        seleccionarColorJugadores();
                        break;
                    case 2:
                        numeroDePartidas();
                        mostrarMenu();
                        break;
                    case 3:
                        seleccionarOrdenJugadores();
                        break;
                    case 4:
                        System.out.println("Adiós");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }

                break;
            case 3:
                System.out.println("Jugar");
                do {
                    jugar(numeroDePartidasObjetivo); // Agrega el parámetro
                    System.out.println("¿Quieres repetir las partidas? (s/n): ");
                    reiniciarTablero();
                    mostrarEstadisticas(partidasJugadas, numeroDePartidasObjetivo);

                } while (scanner.next().equalsIgnoreCase("s"));
                System.out.println("Gracias por jugar. ¡Hasta luego!");
                break;

            case 4:
                System.out.println("Creditos");
                mostrarCreditos();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    public void iniciar() {

        System.out.println("Bienvenido a Conecta 4");
        mostrarNormas();
        mostrarMenu();

    }

    /**
     * Metodo para seleccionar el orden de jugadores dando preferiencia al jugador 1
     */
    public void seleccionarOrdenJugadores() {
        System.out.println("Que orden de jugadores quieres?");
        System.out.println("1. Aleatorio");
        System.out.println("2. Sale Ganador");
        System.out.println("3. Sale Perdedor");
        System.out.println("4. Sale Siempre Jugador 1");

        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Aleatorio");
                Random random = new Random();
                int orden = random.nextInt(2);
                if (orden == 0) {
                    jugadorActual = jugador1;
                } else {
                    jugadorActual = jugador2;
                }
                break;
            case 2:
                System.out.println("Sale Ganador");
                if (contadorVictoriasJugador1 > contadorVictoriasJugador2) {
                    jugadorActual = jugador1;
                } else if (contadorVictoriasJugador2 > contadorVictoriasJugador1) {
                    jugadorActual = jugador2;
                } else {
                    // Si ambos jugadores tienen la misma cantidad de victorias, se selecciona al
                    // jugador 1
                    jugadorActual = jugador1;
                }
                break;
            case 3:
                System.out.println("Sale Perdedor");
                if (contadorEmpates > 0) {
                    if (contadorVictoriasJugador1 < contadorVictoriasJugador2) {
                        jugadorActual = jugador1;
                    } else if (contadorVictoriasJugador2 < contadorVictoriasJugador1) {
                        jugadorActual = jugador2;
                    } else {
                        // Si ambos jugadores tienen la misma cantidad de victorias, se selecciona al
                        // jugador 1
                        jugadorActual = jugador1;
                    }
                } else {
                    // Si no hay empates, se selecciona al jugador 1
                    jugadorActual = jugador1;
                }
                break;
            case 4:
                System.out.println("Sale Siempre Jugador 1");
                jugadorActual = jugador1;
                break;
            default:
                System.out.println("Opción no válida");
        }
        mostrarMenu();

    }

    /**
     * Metodo para seleccionar el color de los jugadores asegurnadome
     * de que no sean iguales
     */
    public void seleccionarColorJugadores() {
        System.out.println("Selecciona el color para el nombre del jugador 1:");
        System.out.println("1. Rojo");
        System.out.println("2. Azul");
        System.out.println("3. Verde");

        int opcion1 = scanner.nextInt();
        String color1 = "";
        switch (opcion1) {
            case 1:
                color1 = "\u001B[31m"; // Rojo
                break;
            case 2:
                color1 = "\u001B[34m"; // Azul
                break;
            case 3:
                color1 = "\u001B[32m"; // Verde
                break;
            default:
                System.out.println("Opción no válida");
        }

        System.out.println("Selecciona el color para el nombre del jugador 2:");
        System.out.println("1. Rojo");
        System.out.println("2. Azul");
        System.out.println("3. Verde");

        int opcion2 = scanner.nextInt();
        String color2 = "";
        while (opcion2 == opcion1) {
            System.out.println("No puedes elegir el mismo color que el jugador 1. Elige otro color:");
            System.out.println("1. Rojo");
            System.out.println("2. Azul");
            System.out.println("3. Verde");
            opcion2 = scanner.nextInt();
        }
        switch (opcion2) {
            case 1:
                color2 = "\u001B[31m"; // Rojo
                break;
            case 2:
                color2 = "\u001B[34m"; // Azul
                break;
            case 3:
                color2 = "\u001B[32m"; // Verde
                break;
            default:
                System.out.println("Opción no válida");
        }

        jugador1.setColor(color1);
        jugador2.setColor(color2);
        mostrarMenu();

    }

    public int numeroDePartidas() {
        System.out.println("Cuantas partidas quieres jugar?");
        Scanner teclado = new Scanner(System.in);
        numeroDePartidasObjetivo = teclado.nextInt();
        return numeroDePartidasObjetivo;

    }

    public void reiniciarTablero() {
        tablero = new Tablero();
    }

    public void mostrarNormas() {
        System.out.println("\u001B[32mNormas del juego:\u001B[0m");
        System.out.println("\u001B[32m Elegir las opciones 1 y 2 antes de jugar. \u001B[0m");
        System.out.println("\u001B[32m1. El juego se juega en un tablero de 6 filas y 7 columnas.\u001B[0m");
        System.out.println("\u001B[32m2. Los jugadores colocan fichas en las columnas del tablero.\u001B[0m");
        System.out.println("\u001B[32m3. El primer jugador que conecta 4 fichas en cualquier direccion gana.\u001B[0m");
        mostrarMenu();
    }

    public void mostrarCreditos() {
        System.out.println("Desarrollado por: Victor Silva Matamoros");
    }

    /**
     * Metodo que configura los jugadores poniendoles nombre o eligiendo contra la IA
     */
    private void configurarJugadores() {
        System.out.print("¿Quieres jugar contra otro jugador (1) o contra la IA (2)? ");
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            System.out.print("Nombre del Jugador 1: ");
            String nombre1 = scanner.next();
            jugador1 = new Jugador(nombre1, 'X');
            System.out.print("Nombre del Jugador 2: ");
            String nombre2 = scanner.next();
            jugador2 = new Jugador(nombre2, 'O');
        } else {
            System.out.print("Nombre del Jugador: ");
            String nombre = scanner.next();
            jugador1 = new Jugador(nombre, 'X');
            jugador2 = new IA("IA", 'O');
        }
    }


    /**
     * Metodo que muestra las estadisticas
     * @param partidasJugadas
     * @param numeroDePartidasObjetivo
     */
    public void mostrarEstadisticas(int partidasJugadas, int numeroDePartidasObjetivo) {
        System.out.println("Estadisticas:");
        System.out.println("Victorias Jugador 1: " + contadorVictoriasJugador1);
        System.out.println("Victorias Jugador 2: " + contadorVictoriasJugador2);
        System.out.println("Empates: " + contadorEmpates);
        if (partidasJugadas == numeroDePartidasObjetivo) {
            ganadorPorVictorias(contadorVictoriasJugador1, contadorVictoriasJugador2);
        }
    }

    /**
     * Metodo que muestra el ganador segun las victorias
     * @param victoriasJugador1
     * @param victoriasJugador2
     */
    public void ganadorPorVictorias(int victoriasJugador1, int victoriasJugador2) {
        if (victoriasJugador1 == victoriasJugador2) {
            System.out.println("EMPATE, REPETIR LAS PARTIDAS PARA VER QUIEN GANA.");
            return;
        }

        if (victoriasJugador1 > victoriasJugador2) {
            System.out.println("El ganador es: " + jugador1.getNombre());
        } else {
            System.out.println("El ganador es: " + jugador2.getNombre());
        }

    }

    /**
     * Metodo que simula el juego
     * Recibe por parametro el numero de partidas a jugar y segun si se juega contra
     * otro jugador o la IA actua en consecuencia
     * @param numeroDePartidasObjetivo
     */
    private void jugar(int numeroDePartidasObjetivo) {
        // Verifica si se ha seleccionado el numero de partidas
        if (numeroDePartidasObjetivo == 0) {
            System.out.println("Debe seleccionar cuantas partidas quiere jugar y a los jugadores primero");
            mostrarMenu();
        } else {
        // Repite el juego para el número de partidas objetivo seleccionado
            for (int i = 0; i < numeroDePartidasObjetivo; i++) {
                jugadorActual = jugador1; // Comienza el Jugador 1
                // Verifica si se ha seleccionado un jugador antes de comenzar el juego
                if (jugadorActual == null) {
                    System.out.println(
                            "No se ha seleccionado ningun jugador.\nPor favor, seleccione una modalidad antes de jugar.");
                    mostrarMenu();
                }
                boolean juegoEnCurso = true;
            // Simula el juego hasta que se produzca un ganador o un empate
                while (juegoEnCurso) {
                    tablero.mostrar();
             // Verifica si el jugador actual es una IA para realizar una jugada automática
                    if (jugadorActual instanceof IA) {
                        int columna = ((IA) jugadorActual).elegirColumna(tablero);
                        System.out.println(
                                jugadorActual.getColor() + "La IA ha elegido la columna: " + columna + "\u001B[0m");
                        if (tablero.colocarFicha(columna, jugadorActual.getSimbolo())) {
                            if (tablero.verificarGanador(jugadorActual.getSimbolo())) {
                                tablero.mostrar();
                                contadorVictoriasJugador2++;
                                System.out
                                        .println(jugadorActual.getColor() + "¡Felicidades " + jugadorActual.getNombre()
                                                + "! Has ganado.\u001B[0m");
                                juegoEnCurso = false;
                                reiniciarTablero();
                            } else if (tablero.estaLleno()) {
                                tablero.mostrar();
                                contadorEmpates++;
                                System.out.println(jugadorActual.getColor() + "¡Es un empate!\u001B[0m");
                                juegoEnCurso = false;
                                reiniciarTablero();
                            } else {
                                jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
                            }
                        }
                    } else {
                        System.out.print(jugadorActual.getColor() + jugadorActual.getNombre()
                                + ", elige una columna (0-6): " + "\u001B[0m");
                        int columna = scanner.nextInt();
                        if (tablero.colocarFicha(columna, jugadorActual.getSimbolo())) {
                            if (tablero.verificarGanador(jugadorActual.getSimbolo())) {
                                tablero.mostrar();
                                System.out.println(jugadorActual.getColor() + "¡Felicidades "
                                        + jugadorActual.getNombre() + "! Has ganado.\u001B[0m");
                                if (jugadorActual == jugador1) {
                                    contadorVictoriasJugador1++;

                                } else {
                                    contadorVictoriasJugador2++;
                                }
                                juegoEnCurso = false;
                                reiniciarTablero();

                            } else if (tablero.estaLleno()) {
                                tablero.mostrar();
                                System.out.println(jugadorActual.getColor() + "¡Es un empate!\u001B[0m");
                                contadorEmpates++;
                                juegoEnCurso = false;
                                reiniciarTablero();

                            } else {
                                jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
                            }
                        } else {
                            System.out.println(
                                    jugadorActual.getColor() + "Columna llena o inválida. Intenta de nuevo.\u001B[0m");
                        }
                    }
                }
                partidasJugadas++;
            }
        }
    }
}