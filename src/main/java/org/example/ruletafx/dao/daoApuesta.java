package org.example.ruletafx.dao;

import org.example.ruletafx.domain.Casilla;
import org.example.ruletafx.domain.Tablero;

import java.util.List;

public interface daoApuesta {
    public List<Casilla> apostarNumero(int numero, double apuesta, Tablero tab);
    public List<Casilla> apostarFila(int fila, double apuesta, Tablero tab);
    public List<Casilla> apostarDocena(int docena, double apuesta, Tablero tab);
    public List<Casilla> apostarColor(boolean color, double apuesta, Tablero tab);
    public List<Casilla> apostarMayor(boolean mayor, double apuesta, Tablero tab);
    public List<Casilla> apostarPar(boolean par, double apuesta,Tablero tab);
    public List<Casilla> apostarHuerfanos(boolean huerfanos, double apuesta, Tablero tab);
    public List<Casilla> borrarDuplicados(List<Casilla> casillasApostadas);
    public double cobrarGanancias();
    public boolean iniciarSesion(String nombre, String contrasena);
    public boolean registrarse(String id, String nombre, String contrasena);
    public int resultadoTirada();
    public void terminarApuesta();

}
