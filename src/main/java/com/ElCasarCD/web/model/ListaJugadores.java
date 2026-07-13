package com.ElCasarCD.web.model;

public class ListaJugadores {


    private Jugador listaJugadores[];
    public int numJugadores;

    public static final int EXITO = 0;
    public static final int ERROR_JugadoNULL = 1;
    public static final int ERROR_DEMASIADOS_JUGADORES = 2;

    public ListaJugadores(Jugador[] listaJugadores, int numJugadores) {
        this.listaJugadores = listaJugadores;
        this.numJugadores = numJugadores;
    }

    


    
}
