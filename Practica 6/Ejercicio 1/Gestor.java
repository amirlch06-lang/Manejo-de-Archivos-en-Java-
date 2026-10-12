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
    public void modificar(String nombre, Scanner scanner) {
        Personaje personaje = buscar(nombre);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        int vida;
        int ataque;
        int defensa;
        int alcance;
        do {
            System.out.print("Nueva vida: ");
            vida = scanner.nextInt();
        } while (vida <= 0);
        do {
            System.out.print("Nuevo ataque: ");
            ataque = scanner.nextInt();
        } while (ataque <= 0);
        do {
            System.out.print("Nueva defensa: ");
            defensa = scanner.nextInt();
        } while (defensa <= 0);
        do {
            System.out.print("Nuevo alcance: ");
            alcance = scanner.nextInt();
        } while (alcance <= 0);
        personaje.setVida(vida);
        personaje.setAtaque(ataque);
        personaje.setDefensa(defensa);
        personaje.setAlcance(alcance);
        guardar();
        System.out.println("Personaje modificado.");
    }
    public void eliminar(String nombre) {
        int posicion = -1;
        for (int i = 0; i < cantidad; i++) {
            if (personajes[i].getNombre().equalsIgnoreCase(nombre)) {
                posicion = i;
                break;
            }
        }
        if (posicion == -1) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        for (int i = posicion; i < cantidad - 1; i++) {
            personajes[i] = personajes[i + 1];
        }
        cantidad--;
        guardar();
        System.out.println("Personaje eliminado.");
    }
    public void guardar() {
        try {
            PrintWriter salida = new PrintWriter(archivo);
            for (int i = 0; i < cantidad; i++) {
                salida.println(personajes[i]);
            }
            salida.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
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
                personajes[cantidad] = new Personaje(nombre, vida, ataque, defensa, alcance);
                cantidad++;
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo.");
        }
    }
}