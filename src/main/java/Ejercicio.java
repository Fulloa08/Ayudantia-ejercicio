import java.util.Scanner;

public class Ejercicio {
    public static void main(String[] args) {
        String[][] entradasEvento = new String[10][5];





    ingresarPersona();
    }

    public boolean verificarEdad(){

    }

    public String verificarBoleto(){

    }

    public void validadInvitados(){

    }

    public int aforoDisponible(){

    }

    public boolean ingresarPersona(){

    }

    public boolean permitirEntrada(){

    }

    public boolean removerPersonal(){

    }

    public String[][]  cargarMatriz(String entradasEvento[][], String nombre, String edad, String entrada, String invitados, String ingresado){
        for(int i = 0; i < entradasEvento.length; i++){
            if (entradasEvento[i][0] == null){
                entradasEvento[i][0] = nombre;
                entradasEvento[i][1] = edad;
                entradasEvento[i][2] = entrada;
                entradasEvento[i][3] = invitados;
                entradasEvento[i][4] = ingresado;
                break;
            }
        }
        return entradasEvento;

    }
}
