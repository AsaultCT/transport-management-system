import java.util.Scanner;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el primer numero: ");
        double a = sc.nextDouble();

        System.out.print("Ingrese el segundo numero: ");
        double b = sc.nextDouble();

        System.out.println("\n--- Resultados ---");
        System.out.println("Suma:       " + (a + b));
        System.out.println("Resta:      " + (a - b));
        System.out.println("Multiplicacion: " + (a * b));

        if (b != 0) {
            System.out.println("Division:   " + (a / b));
        } else {
            System.out.println("Division:   No se puede dividir por cero");
        }

        sc.close();
    }

