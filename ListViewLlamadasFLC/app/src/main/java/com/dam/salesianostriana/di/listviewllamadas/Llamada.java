package com.dam.salesianostriana.di.listviewllamadas;

public class Llamada {


    private int foto, numero;
    private String nombre, fechaHora, tipoLlamada;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoLlamada() {
        return tipoLlamada;
    }

    public void setTipoLlamada(String tipoLlamada) {
        this.tipoLlamada = tipoLlamada;
    }

    public Llamada(int foto, int numero, String nombre, String fechaHora, String tipoLlamada) {
        this.foto = foto;
        this.numero = numero;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.tipoLlamada = tipoLlamada;


    }
}
