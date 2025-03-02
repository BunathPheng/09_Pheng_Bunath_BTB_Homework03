package helperclass;
import entity.HourlySalaryEmployee;
import entity.SalariedEmployee;
import entity.Volunteer;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class HelperClass {
    static CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String reset = "\u001B[0m";
    public static final String yellow = "\u001B[33m";
    public static final String purple = "\u001B[35m";
    public static void printMenu() {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        t.setColumnWidth(0, 10, 10);
        t.setColumnWidth(1, 10, 10);
        t.setColumnWidth(2, 10, 10);
        t.setColumnWidth(3, 10, 10);
        t.addCell(green + "1. Insert Employee" + reset, cellStyle, 5 );
        t.addCell(green + "2. Update Employee" + reset, cellStyle, 5);
        t.addCell(green + "3. Display Employee" + reset, cellStyle , 5);
        t.addCell(green + "4. Remove Employee" + reset, cellStyle,5);
        t.addCell(green + "5. Exit" + reset, cellStyle , 5);
        System.out.println(t.render());
    }
    public static void printSymbol(int count)
    {
        System.out.println("=".repeat(count));
    }
    public static Table createTableHeader(String title) {
        Table t = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        t.setColumnWidth(0, 30, 30);
        t.setColumnWidth(1, 10, 10);
        t.setColumnWidth(2, 30, 30);
        t.setColumnWidth(3, 20, 20);
        t.setColumnWidth(4, 10, 10);
        t.setColumnWidth(5, 10, 10);
        t.setColumnWidth(6, 10, 10);
        t.setColumnWidth(7, 10, 10);
        t.setColumnWidth(8, 10, 10);
        t.addCell(green + title + reset, cellStyle, 10);
        t.addCell(green + "Type" + reset, cellStyle);
        t.addCell(green + "ID" + reset, cellStyle);
        t.addCell(green + "Name" + reset, cellStyle);
        t.addCell(green + "Address" + reset, cellStyle);
        t.addCell(green + "Salary" + reset, cellStyle);
        t.addCell(green + "Bonus" + reset, cellStyle);
        t.addCell(green + "Hour" + reset, cellStyle);
        t.addCell(green + "Rate" + reset, cellStyle);
        t.addCell(green + "Pay" + reset, cellStyle);
        return t;
    }
    public static String getValidInput(String prompt, String pattern, String errorMessage) {
        System.out.print(prompt);
        String input = new Scanner(System.in).nextLine().trim();
        while (!input.matches(pattern)) {
            System.out.println(red + errorMessage + reset);
            System.out.print(prompt);
            input = new Scanner(System.in).nextLine().trim();
        }
        return input;
    }
    public static void menuTypeOfStaffMember() {
        System.out.println("=".repeat(10) + "Choose Type" +"=".repeat(10));
        System.out.println("1. Volunteer");
        System.out.println("2. Salaries Employee");
        System.out.println("3. Hourly Employee");
        System.out.println("4. Exit");
    }
    public static void displayUpdateMenu(String type) {
        System.out.println("Choose one column to update:");
        System.out.println("1. Name");
        System.out.println("2. Address");
        if(type.equals("Volunteer"))
        {
            System.out.println("3. Salary");
            System.out.println("4. Cancel");
        }else if(type.equals("SalariedEmployee"))
        {
            System.out.println("3. Salary");
            System.out.println("4. Bonus");
            System.out.println("5. Cancel");
        }
        else if(type.equals("HourlySalaryEmployee")) {
            System.out.println("3. Hour");
            System.out.println("4. Bonus");
            System.out.println("5. Cancel");
        }
        System.out.print("=> Select Column Number: ");
    }
    public static void displayVolunteerInfo(Volunteer volun, String headerText) {
        Table t = HelperClass.createTableHeader(headerText);
        t.addCell(HelperClass.yellow + "Volunteer" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + volun.getId() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + volun.getName() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + volun.getAddress() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + volun.getSalary() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "$" + volun.pay() + HelperClass.reset, cellStyle);
        System.out.println(t.render());
    }
    public static void updateVolunteerInfo(Volunteer volun) {
        while (true) {
            displayUpdateMenu("Volunteer");
            int column = new Scanner(System.in).nextInt();
            switch (column) {
                case 1:
                    System.out.print("=> Change name: ");
                    String name = new Scanner(System.in).nextLine();
                    volun.setName(name);
                    break;
                case 2:
                    System.out.print("=> Change Address: ");
                    String address = new Scanner(System.in).nextLine();
                    volun.setAddress(address);
                    break;
                case 3:
                    System.out.print("=> Change Salary: ");
                    double salary = new Scanner(System.in).nextDouble();
                    volun.setSalary(salary);
                    break;
                case 4:
                    return;
            }

            displayVolunteerInfo(volun, "Updated Employee");
            System.out.println("Employee updated successfully!");
        }
    }
    public static void displayHourlyEmployeeInfo(HourlySalaryEmployee hourly, String headerText) {
        Table t = HelperClass.createTableHeader(headerText);
        t.addCell(HelperClass.yellow + "HourlySalaryEmployee" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + hourly.getId() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + hourly.getName() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + hourly.getAddress() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + hourly.getHourWorked() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "$" + hourly.getRate() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "$" + hourly.pay() + HelperClass.reset, cellStyle);
        System.out.println(t.render());
    }
    public static void updateHourlyEmployeeInfo(HourlySalaryEmployee hourly) {
        while (true) {
            displayUpdateMenu("HourlySalaryEmployee");
            int column = new Scanner(System.in).nextInt();
            switch (column) {
                case 1:
                    System.out.print("=> Change name: ");
                    String name = new Scanner(System.in).nextLine();
                    hourly.setName(name);
                    break;
                case 2:
                    System.out.print("=> Change Address: ");
                    String address = new Scanner(System.in).nextLine();
                    hourly.setAddress(address);
                    break;
                case 3:
                    System.out.print("=> Change Hours Worked: ");
                    int hours = new Scanner(System.in).nextInt();
                    hourly.setHourWorked(hours);
                    break;
                case 4:
                    System.out.print("=> Change Hours Rate: ");
                    double rate  = new Scanner(System.in).nextDouble();
                    hourly.setRate(rate);
                    break;
                case 5:
                    return;
            }

            displayHourlyEmployeeInfo(hourly, "Updated Employee");
            System.out.println("Employee updated successfully!");
        }
    }
    public static void  displaySalariedEmployee(SalariedEmployee salaried , String headerText) {
        Table t = HelperClass.createTableHeader(headerText);
        t.addCell(HelperClass.yellow + "SalariedEmployee" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + salaried.getId() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + salaried.getName() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + salaried.getAddress() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + salaried.getSalary() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + salaried.getBonus() + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "---" + HelperClass.reset, cellStyle);
        t.addCell(HelperClass.yellow + "$" + salaried.pay() + HelperClass.reset, cellStyle);
        System.out.println(t.render());
    }
    public static void upDateSalariedEmployee(SalariedEmployee salariedEmployee) {
        while (true) {
            displayUpdateMenu("SalariedEmployee");
            int column = new Scanner(System.in).nextInt();
            switch (column) {
                case 1:
                    System.out.print("=> Change name: ");
                    String name = new Scanner(System.in).nextLine();
                    salariedEmployee.setName(name);
                    break;
                case 2:
                    System.out.print("=> Change Address: ");
                    String address = new Scanner(System.in).nextLine();
                    salariedEmployee.setAddress(address);
                    break;
                case 3:
                    System.out.print("=> Change Salary : ");
                    double salary = new Scanner(System.in).nextDouble();
                    salariedEmployee.setSalary(salary);
                    break;
                case 4:
                    System.out.print("=> Change Bonus : ");
                    double bonus = new Scanner(System.in).nextDouble();
                    salariedEmployee.setSalary(bonus);
                    break;
                case 5:
                    return;
            }
            displaySalariedEmployee(salariedEmployee, "Updated Employee");
            System.out.println("Employee updated successfully!");
        }
    }
}
