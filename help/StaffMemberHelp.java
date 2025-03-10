package help;
import entity.HourlySalaryEmployee;
import entity.SalariedEmployee;
import entity.StaffMember;
import entity.Volunteer;
import helperclass.HelperClass;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static helperclass.HelperClass.*;
public class StaffMemberHelp {
    static int rowSet = 0;
    static CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
    static List<StaffMember> staffMembers = new ArrayList<>();
    static int id  =  4;
    public static void addStaffMember() {
        staffMembers.add(new Volunteer(1, "John Doe", "123 Main St", 6.0));
        staffMembers.add(new HourlySalaryEmployee(2, "Charlie White", "789 Cedar St", 45, 16.75));
        staffMembers.add(new SalariedEmployee(3, "Alice Johnson", "123 Maple St", 55000, 5000));
        staffMembers.add(new HourlySalaryEmployee(4, "Alice Green", "123 Oak St", 40, 15.50));
    }
    public static void insertStaffMembers() {
        Scanner scanner = new Scanner(System.in);
        HelperClass.menuTypeOfStaffMember();
        System.out.print("Enter type of staff member: ");
        String n = validateInput(new Scanner(System.in), "type", "\\d+", "Enter type of staff member:");
        try {
            switch (n) {
                case "1":
                    int ids = ++id;
                    System.out.println("ID: " + (ids));
                    System.out.print("Enter name: ");
                    String name = validateInput(scanner, "name", "[A-Za-z ]+", "Enter name: ");

                    System.out.print("Enter Address: ");
                    String address = validateInput(scanner, "address", "[A-Za-z0-9 ,.-]+", "Enter Address: ");

                    System.out.print("Enter Salary: ");
                    double salary = Double.parseDouble(validateInput(scanner, "salary", "\\d+(\\.\\d+)?", "Enter Salary: "));

                    Volunteer newVolunteer = new Volunteer(ids, name, address, salary);
                    System.out.println("**** Your add successfully for add to list**** ");
                    staffMembers.add(newVolunteer);
                    break;

                case "2":
                    int ides = ++id;
                    System.out.println("ID: " + (ides));

                    System.out.print("Enter name: ");
                    String salariesEmployee = validateInput(scanner, "name", "[A-Za-z ]+", "Enter name: ");

                    System.out.print("Enter Address: ");
                    String addressEmployee = validateInput(scanner, "address", "[A-Za-z0-9 ,.-]+", "Enter Address: ");

                    System.out.print("Enter Salary: ");
                    double salaryEmployee = Double.parseDouble(validateInput(scanner, "salary", "\\d+(\\.\\d+)?", "Enter Salary: "));

                    System.out.print("Enter Bonus: ");
                    double bonus = Double.parseDouble(validateInput(scanner, "bonus", "\\d+(\\.\\d+)?", "Enter Bonus: "));

                    SalariedEmployee salariedEmployee = new SalariedEmployee(ides, salariesEmployee, addressEmployee, salaryEmployee, bonus);
                    staffMembers.add(salariedEmployee);
                    break;

                case "3":
                    int hourlySalaryId = ++id;
                    System.out.println("ID: " + (hourlySalaryId));

                    System.out.print("Enter name: ");
                    String hourlyEmployee = validateInput(scanner, "name", "[A-Za-z ]+", "Enter name: ");

                    System.out.print("Enter Address: ");
                    String addressHourlyEmployee = validateInput(scanner, "address", "[A-Za-z0-9 ,.-]+", "Enter Address: ");

                    System.out.print("Enter HourWork: ");
                    int hourWork = Integer.parseInt(validateInput(scanner, "hour work", "\\d+", "Enter HourWork: "));

                    System.out.print("Enter Rate: ");
                    double rate = Double.parseDouble(validateInput(scanner, "rate", "\\d+(\\.\\d+)?", "Enter Rate: "));

                    HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(hourlySalaryId, hourlyEmployee, addressHourlyEmployee, hourWork, rate);
                    staffMembers.add(hourlySalaryEmployee);
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public static String validateInput(Scanner scanner, String fieldName, String regex, String prompt) {
        String input = scanner.nextLine();
        while (!Pattern.matches(regex, input)) {
            System.out.println("Invalid " + fieldName + "! Please enter a valid " + fieldName + ".");
            System.out.print(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
    public static void addStaffMemberRow(Table t, StaffMember staffMember) {
        String role, id, name, address, salary = "---", bonus = "---", hoursWorked = "---", rate = "---", pay;
        switch (staffMember) {
            case Volunteer volunteer -> {
                role = "Volunteer";
                id = String.valueOf(volunteer.getId());
                name = volunteer.getName();
                address = volunteer.getAddress();
                salary = String.valueOf(volunteer.getSalary());
                pay = "$" + volunteer.pay();
            }
            case HourlySalaryEmployee hourlySalaryEmployee -> {
                role = "HourlySalaryEmployee";
                id = String.valueOf(hourlySalaryEmployee.getId());
                name = hourlySalaryEmployee.getName();
                address = hourlySalaryEmployee.getAddress();
                hoursWorked = String.valueOf(hourlySalaryEmployee.getHourWorked());
                rate = "$" + hourlySalaryEmployee.getRate();
                pay = "$" + hourlySalaryEmployee.pay();
            }
            case SalariedEmployee salariedEmployee -> {
                role = "SalariedEmployee";
                id = String.valueOf(salariedEmployee.getId());
                name = salariedEmployee.getName();
                address = salariedEmployee.getAddress();
                salary = String.valueOf(salariedEmployee.getSalary());
                bonus = String.valueOf(salariedEmployee.getBonus());
                pay = "$" + salariedEmployee.pay();
            }
            case null, default -> {
                return; // Skip unknown staff types
            }
        }

        t.addCell(HelperClass.yellow + role + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + id + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + name + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + address + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + salary + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + bonus + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + hoursWorked + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + rate + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + pay + HelperClass.reset, cellStyle);
    }
    public static void paginationStaffMembers(List<StaffMember> staffMembers, String title) {
        int PAGE_SIZE = (rowSet == 0) ? 3 : rowSet;
        int totalItems = staffMembers.size();
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
        int currentPage = 1;

        while (true) {
            currentPage = Math.max(1, Math.min(currentPage, totalPages));
            Table t = createTableHeader(title + " - Page " + currentPage + "/" + totalPages);

            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, totalItems);

            for (int i = startIndex; i < endIndex; i++) {
                addStaffMemberRow(t, staffMembers.get(i));  // Now this method is properly defined!
            }
            System.out.println(t.render());
            System.out.print("1. Next page\t\t\t");
            System.out.print("2. Previous page\t\t\t");
            System.out.print("3. First page\t\t\t");
            System.out.print("4. Last page\t\t\t");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = new Scanner(System.in).nextLine();
            switch (choice) {
                case "1":
                    if (currentPage < totalPages) currentPage++;
                    else System.out.println(red + "You are already on the last page." + reset);
                    break;
                case "2":
                    if (currentPage > 1) currentPage--;
                    else System.out.println(red + "You are already on the first page." + reset);
                    break;
                case "3":
                    currentPage = 1;
                    break;
                case "4":
                    currentPage = totalPages;
                    break;
                case "5":
                    return;
                default:
                    System.out.println(red + "Invalid option! Please try again." + reset);
            }
        }
    }
    public static void printPaginationStaffMembers() {
            if (staffMembers.isEmpty()) {
                System.out.println(red + "No staff members available." + reset);
                return;
            }
            paginationStaffMembers(staffMembers, "INFORMATION OF ALL STAFF MEMBERS");
    }
    public static void upDateStaffMembers() {
        try {
            System.out.println("=======*" + "Update Employee" + "*=======");
            System.out.print("Enter ID to search for Update: ");
            String idInput = validateInput(new Scanner(System.in), "ID", "\\d+", "Enter ID to search for Update: ");
            int id = Integer.parseInt(idInput);
                StaffMember staff = staffMembers.stream()
                        .filter(staffs -> staffs.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (staff != null) {
                    if (staff instanceof Volunteer volun) {
                        displayVolunteerInfo(volun, "Update Employee Volunteer");
                        updateVolunteerInfo(volun);
                    } else if (staff instanceof HourlySalaryEmployee hourly) {
                        displayHourlyEmployeeInfo(hourly, "Update Employee HourlySalaryEmployee");
                        updateHourlyEmployeeInfo(hourly);
                    } else if (staff instanceof SalariedEmployee sal) {
                        displaySalariedEmployee(sal, "Update Employee SalariedEmployee");
                        upDateSalariedEmployee(sal);
                    }
                } else {
                    System.out.println("Not found");
                }
        }catch (Exception e) {
            System.out.println("Is in Valid");
        }

    }
    public static void removeStaffMembers() {
        System.out.print("Enter ID to remove from list :");
        String idInput = validateInput(new Scanner(System.in), "ID", "\\d+", "Enter ID to remove from list : ");
        int id = Integer.parseInt(idInput);
        boolean removeSuccess = staffMembers.removeIf(staff -> {
            boolean b = staff.getId() == id;
            return b;
        });
        if (removeSuccess) {
            System.out.println("Removed " + id + " from list");
        }else {
            System.out.println("Not found");
        }

    }
}
