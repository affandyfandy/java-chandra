package com.finaltask7;

import java.util.*;

import java.util.stream.Collectors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class appManager {
    private static appManager instance;
    private static List<Employee> employees = new ArrayList<>();
    private Boolean useOpenCSV;
    private final Scanner scanner;

    private appManager() {
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);
        useOpenCSV = false;
    };

    public static synchronized appManager getInstance() {
        if (instance == null) {
            instance = new appManager();
        }
        return instance;
    }

    public void start() {
        printHeader();
        showMenu();
    }

    private void printHeader() {
        System.out.println("===========================");
        System.out.println("*      List Employee      *");
        System.out.println("===========================");
        System.out.println();
    }

    private void showMenu() {
        String[] options = {
                "1- Break",
                "2- Choose File Manual/OpenCSV(.csv)",
                "3- Add new Employee",
                "4- Filter",
                "5- Export",

        };

        while (true) {
            System.out.println("\nMenu Options:");
            for (String option : options) {
                System.out.println(option);
            }
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Exiting...");
                    return;
                case "2":
                    chooseFileMenu();
                    break;
                case "3":
                    addEmployee();
                    break;
                case "4":
                    printFilteredEmployees();
                    break;
                case "5":
                    exportFilteredEmployees();
                    break;
                default:

                    break;
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    private void chooseFileMenu() {
        String[] fileOptions = {
                "1- Open Csv by OpenCSV",
                "2- Manual Open Csv",
        };

        while (true) {
            System.out.println("\nChoose File Options:");
            for (String option : fileOptions) {
                System.out.println(option);
            }
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    useOpenCSV = true;
                    importData();
                    return;
                case "2":
                    useOpenCSV = false;
                    importData();
                    return;

            }
        }
    }

    public void importData() {
        if (useOpenCSV == true) {
            System.out.println("Importing data using OpenCSV");
            System.out.print("Insert Link Path: ");

            String urlPath = scanner.nextLine();
            fileUtils.readCSVOpenCsv(urlPath, employees);
        } else {
            System.out.println("Importing data using Manual CSV...");
            System.out.print("Insert Link Path: ");

            String urlPath1 = scanner.nextLine();
            fileUtils.readCSVManual(urlPath1, employees);
        }
    }

    private void addEmployee() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter Employee details:");
        System.out.print("Employee ID: ");
        String id = scanner.nextLine();
        boolean duplicate = false;
        for (Employee employee : employees) {
            if (!employee.getID().equals(id)) {
                duplicate = true;
                System.out.println("ID already exist \n");
                break;
            }
        }

        if (!duplicate) {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("DOB (dd/MM/yyyy): ");
            String dobString = scanner.nextLine();
            LocalDate dob = null;
            try {
                dob = LocalDate.parse(dobString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
                return;
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Department: ");
            String department = scanner.nextLine();

            Employee newEmployee = new Employee(id, name, dob, address, department);
            System.out.println("Employee added successfully.");
            employees.add(newEmployee);
        }

    }

    public List<Employee> filterEmployees() {
        List<Employee> filterEmployee;
        String[] options1 = {
                "1- Filter By ID",
                "2- Filter By Name",
                "3- Filter By Year",
                "4- Filter By Department",
                "5- Show All",

        };

        while (true) {
            System.out.println("\nFilter Option: ");
            for (String option : options1) {
                System.out.println(option);
            }
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String filterOne = scanner.nextLine();
                    filterEmployee = employees.stream()
                            .filter(employee -> employee.getID().startsWith(filterOne))
                            .collect(Collectors.toList());
                    break;
                case "2":
                    String filterTwo = scanner.nextLine();
                    filterEmployee = employees.stream()
                            .filter(employee -> employee.getName().contains(filterTwo))
                            .collect(Collectors.toList());
                    break;
                case "3":
                    int filterThree = scanner.nextInt();
                    scanner.nextLine();
                    filterEmployee = employees.stream()
                            .filter(employee -> employee.getDateBirth().getYear() == filterThree)
                            .collect(Collectors.toList());
                    break;
                case "4":
                    String filterFour = scanner.nextLine();
                    filterEmployee = employees.stream()
                            .filter(employee -> employee.getDepartment().contains(filterFour))
                            .collect(Collectors.toList());
                    break;
                case "5":
                    filterEmployee = new ArrayList<>(employees);
                    filterEmployee.sort(Comparator.comparing(Employee::getDateBirth));
                    return filterEmployee;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            filterEmployee.sort(Comparator.comparing(Employee::getDateBirth));
            return filterEmployee;
        }
    }

    public void printFilteredEmployees() {
        for (Employee employee : filterEmployees()) {
            try {
                System.out.println(
                        "--------------------------------------------------------------------------------------------");
                System.out.printf("%-5s | %-20s | %-12s | %-30s | %-20s\n", employee.getID(),
                        employee.getName(),
                        employee.getDateBirth(), employee.getAddress(), employee.getDepartment());
            } catch (Exception e) {
            }
        }
    }

    public void exportFilteredEmployees() {

        System.out.print("Save as (.csv): ");
        String fileName = scanner.nextLine();
        fileUtils.writeCSVOpen(filterEmployees(), fileName);
        System.out.println("Export Success..");
    }
}
