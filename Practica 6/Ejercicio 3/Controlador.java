public class Controlador {
    private Modelo modelo;
    private Vista vista;
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    modelo.listarEmpleados();
                    break;
                case 2:
                    int numero = vista.pedirNumero();
                    String nombre = vista.pedirNombre();
                    double sueldo = vista.pedirSueldo();
                    Empleado empleado =
                            new Empleado(numero, nombre, sueldo);
                    modelo.agregarEmpleado(empleado);
                    break;
                case 3:
                    numero = vista.pedirNumero();
                    empleado = modelo.buscarEmpleado(numero);
                    vista.mostrarEmpleado(empleado);
                    break;
                case 4:
                    numero = vista.pedirNumero();
                    modelo.eliminarEmpleado(numero);
                    break;
                case 5:
                    vista.mensaje("Programa finalizado.");
                    break;
                default:
                    vista.mensaje("Opcion no valida.");
            }
        } while (opcion != 5);
    }
}