package org.example.ruletafx.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApuestasUsuario {
    private String id;
    private double ganancia;
    private String user_id;
    private LocalDate fecha = LocalDate.now();
    //private List apuestasUsuarios = new ArrayList<>();

    /*public void registrarApuesta(String userId, double ganancia) {
        ApuestasUsuario apuesta = new ApuestasUsuario();
        apuesta.setUser_id(userId);
        apuesta.setGanancia(ganancia);
        apuestasUsuarios.add(String.valueOf(apuesta));
    }*/


    public String toStringFicheroGanancias(){
       StringBuilder sb = new StringBuilder();
       sb.append(user_id).append("-").append(ganancia).append("-").append(fecha).append("\n");
       return sb.toString();
    }
}
