import java.util.Random;

public class IA extends Jugador {
    private Random random;

    public IA(String nombre, char simbolo) {
        super(nombre, simbolo); // Llama al constructor de la clase Jugador
        this.random = new Random();
    }

    public int elegirColumna(Tablero tablero) {
        int columna;
        do {
            columna = random.nextInt(7); // Genera un número aleatorio entre 0 y 6
        } while (tablero.estaLlena(columna)); // Repite si la columna está llena
        return columna; // Devuelve la columna válida
    }
}