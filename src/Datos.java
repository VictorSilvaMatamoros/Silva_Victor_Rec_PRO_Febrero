class Datos {
    private String campo1;
    private String campo2;

    public Datos(String campo1, String campo2) {
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

    public String getCampo1() {
        return campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    @Override
    public String toString() {
        return "Datos{campo1='" + campo1 + "', campo2='" + campo2 + "'}";
    }
}