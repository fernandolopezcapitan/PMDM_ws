package com.salesianostriana.dam.pmdm.reproductor2;


public class ItemCancion {
    String artista, tituloCancion, album, duracion;
    int caratula;

    public ItemCancion(String grupo, String tituloCancion, String album, int caratula, String duracion) {
        this.artista = grupo;
        this.tituloCancion = tituloCancion;
        this.album = album;
        this.caratula = caratula;
        this.duracion = duracion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
