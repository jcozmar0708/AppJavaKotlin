package com.jcozmar0708.appjavakotlin.Miscelanea;

public class Alumno {
    private final int id;
    private final String nombre;
    private final float mates;
    private final float lengua;
    private final float informatica;
    private final float ingles;
    private final float media;

    public Alumno(int id, String nombre, float mates, float lengua, float informatica, float ingles, float media) {
        this.id = id;
        this.nombre = nombre;
        this.mates = mates;
        this.lengua = lengua;
        this.informatica = informatica;
        this.ingles = ingles;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getMates() {
        return mates;
    }

    public float getLengua() {
        return lengua;
    }

    public float getInformatica() {
        return informatica;
    }

    public float getIngles() {
        return ingles;
    }

    public float getMedia() {
        return media;
    }
}
