package dev.perfectbogus.functional.hr;

import java.util.List;

public record Employee(
        String employeeId,
        String name,
        String department,
        String role,
        double salary,
        int yearsOfExperience,
        EmployeeStatus status,
        List<String> skills
) {
}
