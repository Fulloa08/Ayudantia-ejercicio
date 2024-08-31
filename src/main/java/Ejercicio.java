

public class Ejercicio {
    public static void main(String[] args) {
        String[][] entradasEvento = new String[10][5];

        cargarMatriz(entradasEvento, "Juan Pérez", "25", "VIP", "2", "true");
        cargarMatriz(entradasEvento, "Ana Gómez", "30", "VIP", "1", "true");
        cargarMatriz(entradasEvento, "Carlos López", "22", "General", "0", "true");
        cargarMatriz(entradasEvento, "Laura Martínez", "27", "General", "0", "false");
        cargarMatriz(entradasEvento, "Pedro Sánchez", "35", "VIP", "3", "true");
        cargarMatriz(entradasEvento, "Sofía Rodríguez", "17", "General", "0", "true");
        cargarMatriz(entradasEvento, "Luis Fernández", "40", "General", "0", "true");
        cargarMatriz(entradasEvento, "María Álvarez", "28", "VIP", "2", "true");
        cargarMatriz(entradasEvento, "José Martínez", "33", "General", "0", "false");
        cargarMatriz(entradasEvento, "Isabel Pérez", "24", "VIP", "1", "true");

        System.out.println("El usuario que busca tiene entrada tipo: " + verificarBoleto(entradasEvento,"Ana Gómez"));

        System.out.println("El usuario que busca pasa por la verificación de edad: " + verificarEdad(entradasEvento, "Ana Gómez"));

        System.out.println("la validación de que algún usuario VIP no haya excedido el número máximo permitido de 2 invitados es: " + validarInvitados(entradasEvento, "VIP"));

        int aforovip = aforoDisponible(entradasEvento,"VIP",10 );
        System.out.println("el aforo disponible en la sala VIP es de: " + aforovip + " personas");

        int aforogeneral = aforoDisponible(entradasEvento,"General",7 );
        System.out.println("el aforo disponible en la sala General es de: " + aforogeneral + " personas");

        System.out.println("permitir entrada persona: " + permitirEntrada(entradasEvento, "Ana Gómez","30", "VIP",10));

        System.out.println("se ingreso una persona para cambiar su estado de ingreso a: " + ingresarPersona(entradasEvento,"Laura Martínez","27"));

        System.out.println("se removió una persona y se cambió su estado a: " + removerPersona(entradasEvento,"VIP", "Juan Pérez"));












    }

    //true cuando la fila especificada cumpla con que tiene 18 o mas años de edad, devuelve false cuando no es así
    public static boolean verificarEdad(String entradasEvento[][], String nombre){
        for (int i = 0; i < entradasEvento.length; i++){
            if(entradasEvento[i][0] != null && entradasEvento[i][0].equals(nombre)){
                int edadPersona = Integer.parseInt(entradasEvento[i][1]);
                if (edadPersona >= 18){
                    return true;
                }

            }
        }
        return false;
    }

    //devuelve "general", "vip" o "False" según la busqueda de una persona en una fila especificada
    public static String verificarBoleto(String entradasEvento[][] , String nombre){
        for (int i = 0; i < entradasEvento.length; i++){
            if(entradasEvento[i][0] != null && entradasEvento[i][0].equals(nombre)){
                if (entradasEvento[i][2].equals("VIP")){
                    return "VIP";

                } else if (entradasEvento[i][2].equals("General")){
                    return "General";
                }
            }
        }
        return "False";
    }

    //este metodo lo que hace es verificar que el usario "Vip" no exceda el numero de invitados que es solo de 2 invitados.
    public static boolean validarInvitados(String entradasEvento[][], String tipoEntrada){
        for (int i = 0; i < entradasEvento.length; i++){
            if (entradasEvento[i][2] != null &&  entradasEvento[i][2].equals(tipoEntrada)){
               int invitados = Integer.parseInt(entradasEvento[i][3]);

               if(tipoEntrada.equals("VIP") && invitados > 2){
                   return false;
               }
            }

        }
        return true;
    }

    // este metodo devuelve un entero con el aforo restante para alguna sala especificada "general" o "vip", debe contabilizar a los invitados ya admitidos
    public static int aforoDisponible(String entradasEvento[][], String tipoSala, int capacidadTotal){
        int contador  = 0;

        for (int i = 0; i < entradasEvento.length; i++){
            if (entradasEvento[i][2] != null && entradasEvento[i][2].equals(tipoSala)){
                if (entradasEvento[i][4] != null && entradasEvento[i][4].equals("true")) {
                    contador += 1;
                    contador += Integer.parseInt(entradasEvento[i][3]);

                }
            }
        }

        int resta = capacidadTotal - contador;

        if(resta <= 0){
            return 0;
        }


        return resta;
    }

    //este metodo permite cambiar el estado de una persona ingresada de false a true
    public static boolean ingresarPersona(String entradasEvento[][], String nombre, String edad){
        for (int i = 0; i < entradasEvento.length; i++) {
            if (entradasEvento[i][0] != null && entradasEvento[i][0].equals(nombre)) {
                if ("false".equals(entradasEvento[i][4])) {
                    entradasEvento[i][4] = "true";
                    return true;
                }
            }
        }
        return false;
    }

    //debe validaar si una persona en una fila especificada es apta para entrar al evento, verificando que cumpla con que es mayor de edad,
    //tener una entrada valida "general", "vip". verificar que no exceda el aforo máximo de la sala en la que tiene la entrada. si aforo
    // completo, devuelve false, aunque cumpla con los requisitos.
    public static boolean permitirEntrada(String entradasEvento[][], String nombre, String edad, String tipoSala, int capacidadTotal){
        for (int i = 0; i < entradasEvento.length; i++) {
            if (ingresarPersona(entradasEvento,nombre,edad)){
                if (aforoDisponible(entradasEvento, tipoSala, capacidadTotal) >= 1 ){
                    return true;
                } else {
                    entradasEvento[i][4] = "false";
                    return false;
                }
            }
        }
        return false;

    }

    //este metodo debe permitir eliminar a una persona especifica de alguna sala. si es vip, debe borrar los invitados que trae consigo
    //se cambia sus estado de persona ingresada de true a false

    public static boolean removerPersona( String entradasEvento[][], String nombre, String entrada){
        for (int i = 0; i < entradasEvento.length; i++) {
            if (entradasEvento[i][2] != null && entradasEvento[i][0].equals(nombre) && entradasEvento[i][2].equals(entrada)) {

                entradasEvento[i][4] = "false";

                if (entradasEvento[i][2].equals("VIP")) {
                    int numInvitados = Integer.parseInt(entradasEvento[i][3]);

                    for (int j = 0; j < numInvitados; j++) {
                        if (i + j < entradasEvento.length) {
                            for (int k = 0; k < entradasEvento.length; k++) {
                                entradasEvento[i + j][k] = null;
                            }
                        }
                    }
                } else {
                    for(int k= 0; k < entradasEvento.length; k++){
                        entradasEvento[i][k] = null;
                    }

                }
                return true;
            }
        }
        return false;
    }

    public static String[][]  cargarMatriz(String entradasEvento[][], String nombre, String edad, String entrada, String invitados, String ingresado){
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


