import java.io.*;
import javax.swing.JFileChooser;
public class ContadorPalabras {
    public static void main(String[] args) {
        JFileChooser seleccion = new JFileChooser();
        int resultado = seleccion.showOpenDialog(null);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se selecciono ningun archivo.");
            return;
        }
        File archivo = seleccion.getSelectedFile();
        int lineas = 0;
        int caracteres = 0;
        int palabras = 0;
        String[] palabrasGuardadas = new String[1000];
        int[] cantidades = new int[1000];
        int cantidadPalabras = 0;
        try {
            BufferedReader lector =
                    new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas++;
                caracteres = caracteres + linea.length();
                String palabra = "";
                for (int i = 0; i < linea.length(); i++) {
                    char caracter = linea.charAt(i);
                    if (Character.isLetterOrDigit(caracter)) {
                        palabra = palabra + caracter;
                    } else {
                        if (!palabra.equals("")) {
                            palabras++;
                            palabra = palabra.toLowerCase();
                            int posicion = -1;
                            for (int j = 0; j < cantidadPalabras; j++) {
                                if (palabrasGuardadas[j]
                                        .equals(palabra)) {
                                    posicion = j;
                                    break;
                                }
                            }
                            if (posicion == -1) {
                                palabrasGuardadas[cantidadPalabras]
                                        = palabra;
                                cantidades[cantidadPalabras] = 1;
                                cantidadPalabras++;
                            } else {
                                cantidades[posicion]++;
                            }
                            palabra = "";
                        }
                    }
                }
                if (!palabra.equals("")) {
                    palabras++;
                    palabra = palabra.toLowerCase();
                    int posicion = -1;
                    for (int j = 0; j < cantidadPalabras; j++) {
                        if (palabrasGuardadas[j]
                                .equals(palabra)) {
                            posicion = j;
                            break;
                        }
                    }
                    if (posicion == -1) {
                        palabrasGuardadas[cantidadPalabras]
                                = palabra;
                        cantidades[cantidadPalabras] = 1;
                        cantidadPalabras++;
                    } else {
                        cantidades[posicion]++;
                    }
                }
            }
            lector.close();
            System.out.println("\n===== RESULTADOS =====");
            System.out.println("Archivo: " + archivo.getName());
            System.out.println("Total de lineas: " + lineas);
            System.out.println("Total de palabras: " + palabras);
            System.out.println(
                    "Total de caracteres: " + caracteres
            );
            if (lineas > 0) {
                double promedio =
                        (double) palabras / lineas;
                System.out.println(
                        "Promedio de palabras por linea: "
                        + promedio
                );
            }
            System.out.println("\n--- PALABRAS MAS FRECUENTES ---");
            int mayor = 0;
            for (int i = 0; i < cantidadPalabras; i++) {
                if (cantidades[i] > mayor) {
                    mayor = cantidades[i];
                }
            }
            for (int i = 0; i < cantidadPalabras; i++) {
                if (cantidades[i] == mayor) {
                    System.out.println(
                            palabrasGuardadas[i]
                            + " -> "
                            + cantidades[i]
                            + " veces"
                    );
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }
}
