package co.edu.poli.ces3.universita.universita.Mode;

public class Students {

    private int id;
    private String nombre;
    private String documento;

    public Students(int id, String nombre, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
    }

    public Students (String documento){
        this.documento = documento;
    }
    public Students(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String toString() {
        return "El estudiante se llama: " +
                this.nombre + " su documento es: " +
                this.documento;
    }
}
