package com.salesianostriana.dam.di.playerpro;


public class ItemCancion {
    String grupo, tituloCancion, album;
    int caratula, duracion;

    public ItemCancion(String grupo, String tituloCancion, String album, int caratula, int duracion) {
        this.grupo = grupo;
        this.tituloCancion = tituloCancion;
        this.album = album;
        this.caratula = caratula;
        this.duracion = duracion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTituloCancion() {
        return tituloCancion;
    }

    public void setTituloCancion(String tituloCancion) {
        this.tituloCancion = tituloCancion;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getCaratula() {
        return caratula;
    }

    public void setCaratula(int caratula) {
        this.caratula = caratula;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
