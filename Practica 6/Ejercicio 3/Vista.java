import java.util.Scanner;
public class Vista {
    private Scanner scanner;
    public Vista() {
        scanner = new Scanner(System.in);
    }
    public int mostrarMenu() {
        System.out.println("\n===== SISTEMA DE EMPLEADOS =====");
        System.out.println("1. Listar empleados");
        System.out.println("2. Agregar empleado");
        System.out.println("3. Buscar empleado");
        System.out.println("4. Eliminar empleado");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");
        return scanner.nextInt();
    }
    public int pedirNumero() {
        System.out.print("Ingrese numero: ");
        return scanner.nextInt();
    }
    public String pedirNombre() {
        System.out.print("Ingrese nombre: ");
        return scanner.next();
    }
    public double pedirSueldo() {
        System.out.print("Ingrese sueldo: ");
        return scanner.nextDouble();
    }
    public void mostrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
        } else {
            System.out.println(empleado);
        }
    }
    public void mensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
