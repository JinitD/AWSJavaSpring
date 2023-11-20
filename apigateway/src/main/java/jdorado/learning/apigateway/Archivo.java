package jdorado.learning.apigateway;

public class Archivo {
    private String name;
    private String base64;
    private String contenType;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getContenType() {
        return contenType;
    }

    public void setContenType(String contenType) {
        this.contenType = contenType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Archivo [name=" + name + ", base64=" + base64 + ", contenType=" + contenType + "]";
    }

   

}
