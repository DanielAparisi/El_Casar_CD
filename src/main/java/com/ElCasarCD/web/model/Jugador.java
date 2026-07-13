package com.ElCasarCD.web.model;

public class Jugador {
    private String nombre;
    private String apellido;
    private int edad;
    private String posicion;
    private int dorsal;

    public Jugador(String nombre, String apellido, int edad, String posicion, int dorsal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.posicion = posicion;
        this.dorsal = dorsal;
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getApellido(){
        return this.apellido;
    }
    public int getEdad(){
        return this.edad;
    }
    public String getPosicion(){
        return this.posicion;
    }
    public int getDorsal(){
        return this.dorsal;
    }

    public void setNombre(String nombre){
            this.nombre = nombre;
    }
    public void setApellido(String apellido){
         this.apellido = apellido;
    }
    public void setEdad(int edad){
       this.edad = edad;
    }
    public void setPosiccion( String posicion){
        this.posicion = posicion;
    }
    public void setDorsal(int dorsal){
        this.dorsal = dorsal;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    public boolean esMayorDeEdad() {
        return this.edad >= 18;
    }

}

