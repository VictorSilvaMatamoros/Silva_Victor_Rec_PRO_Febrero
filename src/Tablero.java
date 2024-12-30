public class Tablero {
    private final char[][] tablero;
    private final int FILAS = 6;
    private final int COLUMNAS = 7;

    public Tablero() {
        tablero = new char[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = ' '; // Inicializamos el tablero vacío
            }
        }
    }

    
    public void mostrar() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print("|" + tablero[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }
    public boolean estaLlena(int columna) {
        for (int i = 0; i < FILAS; i++) {
            if (tablero[i][columna] == ' ') {
                return false; // La columna no está llena
            }
        }
        return true; // La columna está llena
    }
    public boolean colocarFicha(int columna, char simbolo) {
        if (columna < 0 || columna >= COLUMNAS) {
            return false; // Columna inválida
        }
        for (int i = FILAS - 1; i >= 0; i--) {
            if (tablero[i][columna] == ' ') {
                tablero[i][columna] = simbolo; // Coloca la ficha
                if (simbolo == tablero[i][columna]) {
                    return true; // La ficha se colocó correctamente
                } else {
                    return false; // La ficha no se colocó correctamente
                }
            }
        }
        return false; // Columna llena
    }

    public boolean verificarGanador(char simbolo) {
        // Verificar horizontal, vertical y diagonal
        return verificarHorizontal(simbolo) || verificarVertical(simbolo) || verificarDiagonal(simbolo);
    }
    public boolean estaLleno() {
        for (int i = 0; i < COLUMNAS; i++) {
            if (tablero[0][i] == ' ') { // Si hay al menos un espacio vacío en la fila superior
                return false;
            }
        }
        return true; // No hay espacios vacíos, el tablero está lleno
    }

    private boolean verificarHorizontal (char simbolo) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS - 3; j++) {
                if (tablero[i][j] == simbolo && tablero[i][j + 1] == simbolo &&
                    tablero[i][j + 2] == simbolo && tablero[i][j + 3] == simbolo) {
                    return true; // Ganador horizontal
                }
            }
        }
        return false;
    }

    private boolean verificarVertical(char simbolo) {
        for (int j = 0; j < COLUMNAS; j++) {
            for (int i = 0; i < FILAS - 3; i++) {
                if (tablero[i][j] == simbolo && tablero[i + 1][j] == simbolo &&
                    tablero[i + 2][j] == simbolo && tablero[i + 3][j] == simbolo) {
                    return true; // Ganador vertical
                }
            }
        }
        return false;
    }

    private boolean verificarDiagonal(char simbolo) {
        // Verificar diagonal de izquierda a derecha
        for (int i = 0; i < FILAS - 3; i++) {
            for (int j = 0; j < COLUMNAS - 3; j++) {
                if (tablero[i][j] == simbolo && tablero[i + 1][j + 1] == simbolo &&
                    tablero[i + 2][j + 2] == simbolo && tablero[i + 3][j + 3] == simbolo) {
                    return true; // Ganador diagonal
                }
            }
        }
        // Verificar diagonal de derecha a izquierda
        for (int i = 0; i < FILAS - 3; i++) {
            for (int j = 3; j < COLUMNAS; j++) {
                if (tablero[i][j] == simbolo && tablero[i + 1][j - 1] == simbolo &&
                    tablero[i + 2][j - 2] == simbolo && tablero[i + 3][j - 3] == simbolo) {
                    return true; // Ganador diagonal
                }
            }
        }
        return false;
    }
}
