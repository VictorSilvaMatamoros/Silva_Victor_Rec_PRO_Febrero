public class Jugador {
    private final String nombre;
    private final char simbolo;
    private String color;

    public Jugador(String nombre, char simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.color = "\u001B[0m"; 
    }

    public Jugador(String nombre, char simbolo, String color) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.color = color;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public char getSimbolo() {
        return simbolo;
    }
}