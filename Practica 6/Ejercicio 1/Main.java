import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor("personajes.txt");
        int opcion;
        do {
            System.out.println("\n--- GESTOR DE PERSONAJES ---");
            System.out.println("1. Agregar personaje");
            System.out.println("2. Mostrar personajes");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    int vida;
                    int ataque;
                    int defensa;
                    int alcance;
                    do {
                        System.out.print("Vida: ");
                        vida = scanner.nextInt();
                    } while (vida <= 0);
                    do {
                        System.out.print("Ataque: ");
                        ataque = scanner.nextInt();
                    } while (ataque <= 0);
                    do {
                        System.out.print("Defensa: ");
                        defensa = scanner.nextInt();
                    } while (defensa <= 0);
                    do {
                        System.out.print("Alcance: ");
                        alcance = scanner.nextInt();
                    } while (alcance <= 0);
                    Personaje personaje =
                        new Personaje(nombre, vida, ataque, defensa, alcance);
                    gestor.agregar(personaje);
                    break;
                case 2:
                    gestor.mostrar();
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Nombre del personaje: ");
                    nombre = scanner.nextLine();
                    gestor.modificar(nombre, scanner);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Nombre del personaje: ");
                    nombre = scanner.nextLine();
                    gestor.eliminar(nombre);
                    break;
                case 5:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }
}
