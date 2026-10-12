import java.io.*;
import java.util.Scanner;
public class Gestor {
    private Personaje[] personajes;
    private int cantidad;
    private String archivo;
    public Gestor(String archivo) {
        this.archivo = archivo;
        personajes = new Personaje[100];
        cantidad = 0;
        cargar();
    }
    public void agregar(Personaje personaje) {
        if (buscar(personaje.getNombre()) != null) {
            System.out.println("El personaje ya existe.");
            return;
        }
        personajes[cantidad] = personaje;
        cantidad++;
        guardar();
        System.out.println("Personaje agregado.");
    }
    public Personaje buscar(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (personajes[i].getNombre().equalsIgnoreCase(nombre)) {
                return personajes[i];
            }
        }
        return null;
    }
    public void mostrar() {
        if (cantidad == 0) {
            System.out.println("No hay personajes.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println(personajes[i]);
        }
    }
    public void ordenar(int opcion) {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = i + 1; j < cantidad; j++) {
                int valor1 = obtenerValor(personajes[i], opcion);
                int valor2 = obtenerValor(personajes[j], opcion);
                if (valor1 < valor2) {
                    Personaje auxiliar = personajes[i];
                    personajes[i] = personajes[j];
                    personajes[j] = auxiliar;
                }
            }
        }
        System.out.println("Personajes ordenados.");
        mostrar();
    }
    public int obtenerValor(Personaje personaje, int opcion) {
        switch (opcion) {
            case 1:
                return personaje.getVida();
            case 2:
                return personaje.getAtaque();
            case 3:
                return personaje.getDefensa();
            case 4:
                return personaje.getAlcance();
            default:
                return 0;
        }
    }
    public void cargarPersonajes() {
        agregar(new Personaje("Mago", 5, 7, 2, 6));
        agregar(new Personaje("Tanque", 10, 3, 8, 2));
        agregar(new Personaje("Asesino", 4, 9, 3, 5));
        System.out.println("Personajes cargados.");
    }
    public void actualizarAtributo(String nombre, Scanner scanner) {
        Personaje personaje = buscar(nombre);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        System.out.println("1. Vida");
        System.out.println("2. Ataque");
        System.out.println("3. Defensa");
        System.out.println("4. Alcance");
        System.out.print("Seleccione el atributo: ");
        int opcion = scanner.nextInt();
        int valor;
        do {
            System.out.print("Nuevo valor: ");
            valor = scanner.nextInt();
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor que cero.");
            }
        } while (valor <= 0);
        switch (opcion) {
            case 1:
                personaje.setVida(valor);
                break;
            case 2:
                personaje.setAtaque(valor);
                break;
            case 3:
                personaje.setDefensa(valor);
                break;
            case 4:
                personaje.setAlcance(valor);
                break;
            default:
                System.out.println("Opcion no valida.");
                return;
        }
        guardar();
        System.out.println("Atributo actualizado.");
    }
    public void estadisticas() {
        if (cantidad == 0) {
            System.out.println("No hay personajes.");
            return;
        }
        int sumaVida = 0;
        int sumaAtaque = 0;
        int sumaDefensa = 0;
        int sumaAlcance = 0;
        for (int i = 0; i < cantidad; i++) {
            sumaVida = sumaVida + personajes[i].getVida();
            sumaAtaque = sumaAtaque + personajes[i].getAtaque();
            sumaDefensa = sumaDefensa + personajes[i].getDefensa();
            sumaAlcance = sumaAlcance + personajes[i].getAlcance();
        }
        System.out.println("\n--- ESTADISTICAS ---");
        System.out.println("Total de personajes: " + cantidad);
        System.out.println(
                "Promedio de vida: " + (double) sumaVida / cantidad
        );
        System.out.println(
                "Promedio de ataque: " + (double) sumaAtaque / cantidad
        );
        System.out.println(
                "Promedio de defensa: " + (double) sumaDefensa / cantidad
        );
        System.out.println(
                "Promedio de alcance: " + (double) sumaAlcance / cantidad
        );
    }
    public void importar(String nombreArchivo) {
        try {
            File archivoExterno = new File(nombreArchivo);
            if (!archivoExterno.exists()) {
                System.out.println("El archivo no existe.");
                return;
            }
            Scanner entrada = new Scanner(archivoExterno);
            while (entrada.hasNext()) {
                String nombre = entrada.next();
                int vida = entrada.nextInt();
                int ataque = entrada.nextInt();
                int defensa = entrada.nextInt();
                int alcance = entrada.nextInt();
                Personaje personaje =
                        new Personaje(
                                nombre,
                                vida,
                                ataque,
                                defensa,
                                alcance
                        );
                agregar(personaje);
            }
            entrada.close();
            System.out.println("Personajes importados.");
        } catch (Exception e) {
            System.out.println("Error al importar.");
        }
    }
    public void subirNivel(String nombre) {
        Personaje personaje = buscar(nombre);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        personaje.subirNivel();
        guardar();
        System.out.println("El personaje subio de nivel.");
    }
    public void eliminar(String nombre) {
        Perdonaje personaje = buscar(nombre);
        if (personaje == null) {
            System.out.println("Personaje no encontrado");
            return;
        }
        for (int i = 0; i < cantidad; i++){
            if (personajes [i] == personaje) {
                for (int j = i; j < cantidad - 1; j++){
                    personajes[j] = personajes[j + 1];
                }
                personajes[cantidad - 1] = null;
                cantidad--;
                guardar();
                System.out.println("Perdonaje eliminado.")
                    return;
            }
        }
    }
    public void guardar() {
        try {
            PrintWriter salida = new PrintWriter(archivo);
            for (int i = 0; i < cantidad; i++) {
                salida.println(personajes[i]);
            }
            salida.close();
        } catch (IOException e) {
            System.out.println("Error al guardar.");
        }
    }
    public void cargar() {
        try {
            File archivoFile = new File(archivo);
            if (!archivoFile.exists()) {
                return;
            }
            Scanner entrada = new Scanner(archivoFile);
            while (entrada.hasNext()) {
                String nombre = entrada.next();
                int vida = entrada.nextInt();
                int ataque = entrada.nextInt();
                int defensa = entrada.nextInt();
                int alcance = entrada.nextInt();
                int nivel = 1;
                if (entrada.hasNextInt()) {
                    nivel = entrada.nextInt();
                }
                Personaje personaje =
                        new Personaje(
                                nombre,
                                vida,
                                ataque,
                                defensa,
                                alcance
                        );
                for (int i = 1; i < nivel; i++) {
                    personaje.subirNivel();
                }
                personajes[cantidad] = personaje;
                cantidad++;
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo.");
        }
    }
}
