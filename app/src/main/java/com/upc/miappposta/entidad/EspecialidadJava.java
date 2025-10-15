package com.upc.miappposta.entidad;

public class EspecialidadJava {
    private String nombre;
    private int id;
    private int iconoResId;

    public EspecialidadJava(String nombre, int id, int iconoResId) {
        this.nombre = nombre;
        this.id = id;
        this.iconoResId = iconoResId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }


    public int getIconoResId() {
        return iconoResId;
    }
}

