package org.example.ruletafx.dao;

import org.example.ruletafx.commons.Constantes;
import org.example.ruletafx.domain.ApuestasUsuario;
import org.example.ruletafx.domain.Tirada;
import org.example.ruletafx.domain.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ficheros {
    public static boolean escribirFicheroApuestas(String fichero, List<ApuestaImplementacion> lista){
        PrintWriter escribir = null;
        boolean a = false;
        try{
            escribir = new PrintWriter(fichero);
        }
        catch(FileNotFoundException e){
            System.out.println(Constantes.MENSAJE_ERROR_ESCRITURA);
        }
        for(int i = 0; i< lista.size(); i++){
            escribir.println(lista.get(i).toStringFicheroApuesta());
            a = true;
        }
        escribir.close();
        return a;
    }
    public static List<ApuestaImplementacion> leerFicheroApuestas(String fichero){
        List<ApuestaImplementacion> lista = new ArrayList<>();
        String Linea = null;
        try{
            Scanner sc = new Scanner(new File(fichero));
            while(sc.hasNextLine()){
                Linea = sc.nextLine();
                String[] partes = Linea.split("-");
                String id = partes[0];
                ApuestaImplementacion apuesta = new ApuestaImplementacion();
                apuesta.setId(id);
                lista.add(apuesta);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(Constantes.MENSAJE_ERROR_LECTURA);
        }
        return lista;
    }
    public static boolean escribirFicheroUsuario(String fichero, List<Usuario> lista){
        PrintWriter escribir = null;
        boolean a = false;
        try{
            escribir = new PrintWriter(new FileWriter(fichero,true));
        }
        catch(FileNotFoundException e){
            System.out.println(Constantes.MENSAJE_ERROR_ESCRITURA);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Usuario usuario : lista) {
            escribir.println(usuario.toStringFicheroUsuario());
            a = true;
        }
        escribir.close();
        return a;
    }
    public static List<Usuario> leerFicheroUsuario(String fichero){
        List<Usuario> lista = new ArrayList<>();
        String Linea = null;
        try{
            Scanner sc = new Scanner(new File(fichero));
            while(sc.hasNextLine()){
                Linea = sc.nextLine();
                String[] partes = Linea.split("-");
                String id = partes[0];
                String nombre = partes[1];
                String contraseña = partes[2];
                Usuario usuario = new Usuario(id, nombre, contraseña);
                lista.add(usuario);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(Constantes.MENSAJE_ERROR_LECTURA);
        }
        return lista;
    }
    public static boolean escribirFicheroTirada(String fichero, List<Tirada> lista){
        PrintWriter escribir = null;
        boolean a = false;
        try{
            escribir = new PrintWriter(new FileWriter(fichero,true));
        }catch(FileNotFoundException e){
            System.out.println(Constantes.MENSAJE_ERROR_ESCRITURA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i< lista.size(); i++){
            escribir.println(lista.get(i).toStringFicheroTirada());
            a = true;
        }
        escribir.close();
        return a;
    }
    public static boolean escribirFicheroGanancias(String fichero, List<ApuestasUsuario> lista) {
        PrintWriter escribir = null;
        boolean a = false;
        try {
            escribir = new PrintWriter(fichero);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < lista.size(); i++) {
            escribir.println(lista.get(i).toStringFicheroGanancias());
            a = true;
        }
        escribir.close();
        return a;
    }
}
