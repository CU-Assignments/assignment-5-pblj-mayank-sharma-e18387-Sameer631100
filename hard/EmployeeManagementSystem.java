import java.io.*;
import java.util.*;

public class EmployeeManagementSystem {
    static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addEmployee(scanner, employees);
                    saveEmployees(employees);
                    System.out.println("Employee added successfully!");
                    break;
                case 2:
                    displayEmployees(employees);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    static void addEmployee(Scanner scanner, List<Employee> employees) {
        try {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            Employee emp = new Employee(id, name, designation, salary);
            employees.add(emp);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // clear buffer
        }
    }

    static void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static List<Employee> loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
