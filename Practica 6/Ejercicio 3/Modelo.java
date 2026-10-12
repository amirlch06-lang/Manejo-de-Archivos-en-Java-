import java.io.*;
import java.util.Scanner;
public class Modelo {
    private Empleado[] empleados;
    private int cantidad;
    private String archivo;
    public Modelo(String archivo) {
        this.archivo = archivo;
        empleados = new Empleado[100];
        cantidad = 0;
        leerEmpleados();
    }
    public void leerEmpleados() {
        try {
            File archivoFile = new File(archivo);
            if (!archivoFile.exists()) {
                return;
            }
            Scanner entrada = new Scanner(archivoFile);
            while (entrada.hasNext()) {
                int numero = entrada.nextInt();
                String nombre = entrada.next();
                double sueldo = entrada.nextDouble();
                empleados[cantidad] = new Empleado(numero, nombre, sueldo);
                cantidad++;
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo.");
        }
    }
    public void agregarEmpleado(Empleado empleado) {
        if (buscarEmpleado(empleado.getNumero()) != null) {
            System.out.println("El empleado ya existe.");
            return;
        }
        empleados[cantidad] = empleado;
        cantidad++;
        guardar();
        System.out.println("Empleado agregado correctamente.");
    }
    public Empleado buscarEmpleado(int numero) {
        for (int i = 0; i < cantidad; i++) {
            if (empleados[i].getNumero() == numero) {
                return empleados[i];
            }
        }
        return null;
    }
    public void eliminarEmpleado(int numero) {
        int posicion = -1;
        for (int i = 0; i < cantidad; i++) {
            if (empleados[i].getNumero() == numero) {
                posicion = i;
                break;
            }
        }
        if (posicion == -1) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        for (int i = posicion; i < cantidad - 1; i++) {
            empleados[i] = empleados[i + 1];
        }
        cantidad--;
        guardar();
        System.out.println("Empleado eliminado correctamente.");
    }
    public void listarEmpleados() {
        if (cantidad == 0) {
            System.out.println("No hay empleados.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println(empleados[i]);
        }
    }
    public void guardar() {
        try {
            PrintWriter salida = new PrintWriter(archivo);
            for (int i = 0; i < cantidad; i++) {
                salida.println(
                        empleados[i].getNumero() + " " +
                        empleados[i].getNombre() + " " +
                        empleados[i].getSueldo()
                );
            }
            salida.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
        }
    }
}
