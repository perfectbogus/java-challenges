package dev.perfectbogus.functional.salary.report;

public class Employee {
    private final String name;
    private final String department;
    private final double salary;
    private final int yearsOfExperience;

    public Employee(String name, String department, double salary, int yearsOfExperience) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
