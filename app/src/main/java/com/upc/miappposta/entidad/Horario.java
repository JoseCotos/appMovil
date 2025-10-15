package com.upc.miappposta.entidad;

public class Horario {
    private String hora;
    private boolean isDisponible;
    private boolean isSelected;

    public Horario(String hora, boolean isDisponible) {
        this.hora = hora;
        this.isDisponible = isDisponible;
        this.isSelected = false;
    }

    public String getHora() { return hora; }
    public boolean isDisponible() { return isDisponible; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
