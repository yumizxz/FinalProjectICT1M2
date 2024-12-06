import java.util.Scanner;

public class FinalProjectGroup2 {

    // ANSI escape codes for colors and background
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW = "\u001B[33m";
    public static final int CONSOLE_WIDTH = 50; // Constant width for center alignment

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (true) {
            loadingScreen();  // Display loading screen at the beginning

            System.out.print(CYAN + "Press Enter to continue..." + RESET); // Prompt user to press Enter
            sc.nextLine(); // Wait for user input

            choice = 0; // Reset choice for the new session

            while (choice != 5) {
                clearScreen();
                printMenu1();
                try {
                    System.out.print(CYAN + "Enter your choice: " + RESET);
                    choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            basicCalculator(sc);
                            break;
                        case 2:
                            circumferenceArea(sc);
                            break;
                        case 3:
                            perimeterArea(sc);
                            break;
                        case 4:
                            convertUnits(sc);
                            break;
                        case 5:
                            printCentered("Exiting...", PURPLE);
                            System.out.println(CYAN + "Press 1 to return to the loading screen or any other key to exit." + RESET);
                            String exitChoice = sc.nextLine();
                            if (exitChoice.equals("1")) {
                                choice = 0; // Reset choice to continue the outer loop
                            } else {
                                sc.close();
                                return; // Exit the program
                            }
                            break;
                        default:
                            flashRedBackground("Invalid choice, please choose again.");
                    }

                } catch (NumberFormatException e) {
                    flashRedBackground("Please enter a valid number.");
                }
            }
        }
    }

    // Method to simulate a loading screen
    private static void loadingScreen() {
        System.out.print(CYAN + "Loading");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.print(".");
                Thread.sleep(500); // Delay to simulate loading
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clear the screen and display the introduction part
        clearScreen();
        printCentered("═════════════════════════════════════════════", PURPLE);
        printCentered("║                                           ║", PURPLE);
        printCentered("║                FINAL PROJECT              ║", PURPLE);
        printCentered("║                                           ║", PURPLE);
        printCentered("═════════════════════════════════════════════", PURPLE);
        System.out.println();  // Add space after the title

        printCentered("Leader: Princess Jmie Macabanti", CYAN);
        printCentered("Members:", CYAN);
        printCentered("Charian Aimver De Luna", CYAN);
        printCentered("Bles Angela Andrada", CYAN);
        printCentered("Lorelyn Juliano Faderanga", CYAN);
        printCentered("Jinneyfer Malicdem", CYAN);
        printCentered("Jhared Abeja", CYAN);
        printCentered("Ezekiel Javellana", CYAN);
        printCentered("Daryl Saldo", CYAN);
        printCentered("Ramel Jr. Aguilo", CYAN);
        printCentered("Aldrin Copada", CYAN);
        printCentered("Seven Refugio", CYAN);

        System.out.println(); // Add space before pressing Enter to continue
        System.out.print(CYAN + "Press Enter to continue..." + RESET);
    }

    // Clear screen (Console-specific)
    private static void clearScreen() {
        // This works for most terminals
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Print centered text
    private static void printCentered(String text, String color) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        String paddedText = " ".repeat(Math.max(0, padding)) + text;
        System.out.println(color + paddedText + RESET);
    }

    // Display the main menu with a box design
    private static void printMenu1() {
        printCentered("═════════════════════════════════════════════", PURPLE);
        printCentered("║                                           ║", PURPLE);
        printCentered("║              APPLICATION MENU             ║", PURPLE);
        printCentered("║                                           ║", PURPLE);
        printCentered("═════════════════════════════════════════════", PURPLE);
        System.out.println(); // Add space between menu title and options

        printMenuBox("Basic Calculator", 1);
        printMenuBox("Area of Circle", 2);
        printMenuBox("Area of Rectangle", 3);
        printMenuBox("Unit Conversions (cm <-> m)", 4);
        printMenuBox("Exit", 5);
    }

    // Method to display each option in a styled box
    private static void printMenuBox(String title, int optionNumber) {
        String menuText = String.format("%d. %-29s", optionNumber, title);
        printCentered("•••••••••••••••••••••••••••••••••••••", PURPLE);
        printCentered(menuText, PURPLE);
        printCentered("•••••••••••••••••••••••••••••••••••••", PURPLE);
    }

    // Flash red background for errors
    private static void flashRedBackground(String message) {
        for (int i = 0; i < 3; i++) {
            clearScreen();
            printCentered(RED_BG + WHITE + message + RESET, "");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Basic Calculator
    private static void basicCalculator(Scanner sc) {
        clearScreen();
        printCentered("Basic Calculator", YELLOW);
        try {
            System.out.println(CYAN + "Enter the first number:" + RESET);
            double firstNumber = Double.parseDouble(sc.nextLine());
            System.out.println(CYAN + "Enter the operator (+, -, *, /):" + RESET);
            char operator = sc.nextLine().charAt(0);
            System.out.println(CYAN + "Enter the second number:" + RESET);
            double secondNumber = Double.parseDouble(sc.nextLine());

            double result = applyOperation(operator, secondNumber, firstNumber);
            clearScreen();
            printCentered("Basic Calculator", YELLOW);
            System.out.println("\nResult: " + result);
            System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
            sc.nextLine();
        } catch (Exception e) {
            flashRedBackground("Invalid input. Please try again.");
            System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
            sc.nextLine();
        }
    }

    private static double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return a / b;
        }
        return 0;
    }

    // Circumference and Area of Circle
    private static void circumferenceArea(Scanner sc) {
        clearScreen();
        printCentered("Circumference and Area of Circle", YELLOW);
        System.out.println(CYAN + "Enter the radius of the circle:" + RESET);
        try {
            double radius = Double.parseDouble(sc.nextLine());
            double circumference = 2 * Math.PI * radius;
            double area = Math.PI * radius * radius;

            clearScreen();
            printCentered("Circumference and Area of Circle", YELLOW);
            System.out.println("\nRadius: " + radius);
            System.out.println(GREEN_BG + WHITE + "Circumference: " + String.format("%.2f", circumference) + RESET);
            System.out.println(GREEN_BG + WHITE + "Area: " + String.format("%.2f", area) + RESET);
            System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
            sc.nextLine();
        } catch (NumberFormatException e) {
            flashRedBackground("Invalid input. Please enter a valid number.");
            System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
            sc.nextLine();
        }
    }

    // Perimeter and Area of Rectangle
        private static void perimeterArea(Scanner sc) {
            clearScreen();
            printCentered("Perimeter and Area of Rectangle", YELLOW);
            System.out.println(CYAN + "Enter the length of the rectangle:" + RESET);
            try {
                double length = Double.parseDouble(sc.nextLine());
                System.out.println(CYAN + "Enter the width of the rectangle:" + RESET);
                double width = Double.parseDouble(sc.nextLine());

                double perimeter = 2 * (length + width);
                double area = length * width;

                clearScreen();
                printCentered("Perimeter and Area of Rectangle", YELLOW);
                System.out.println("\nLength: " + length);
                System.out.println("Width: " + width);
                System.out.println(GREEN_BG + WHITE + "Perimeter: " + String.format("%.2f", perimeter) + RESET);
                System.out.println(GREEN_BG + WHITE + "Area: " + String.format("%.2f", area) + RESET);
                System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
                sc.nextLine();
            } catch (NumberFormatException e) {
                flashRedBackground("Invalid input. Please enter valid numbers.");
                System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
                sc.nextLine();
            }
        }

        // Unit Conversion (cm <-> m)
        private static void convertUnits(Scanner sc) {
            clearScreen();
            printCentered("Unit Conversion (cm <-> m)", YELLOW);
            System.out.println(CYAN + "Enter the value to convert:" + RESET);
            try {
                double value = Double.parseDouble(sc.nextLine());

                System.out.println(CYAN + "Choose conversion type: \n1. cm to m \n2. m to cm" + RESET);
                int conversionChoice = Integer.parseInt(sc.nextLine());

                switch (conversionChoice) {
                    case 1:
                        double cmToMeter = value / 100;
                        clearScreen();
                        printCentered("Unit Conversion (cm <-> m)", YELLOW);
                        System.out.println("\n" + value + " cm = " + cmToMeter + " m");
                        break;
                    case 2:
                        double meterToCm = value * 100;
                        clearScreen();
                        printCentered("Unit Conversion (cm <-> m)", YELLOW);
                        System.out.println("\n" + value + " m = " + meterToCm + " cm");
                        break;
                    default:
                        flashRedBackground("Invalid conversion choice.");
                }
                System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
                sc.nextLine();
            } catch (NumberFormatException e) {
                flashRedBackground("Invalid input. Please enter a valid number.");
                System.out.println("\n" + CYAN + "Press Enter to return to menu..." + RESET);
                sc.nextLine();
            }
        }
    }