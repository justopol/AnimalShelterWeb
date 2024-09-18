package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.EmployeeDto;
import pl.shelter.dto.accounts.AddEmployeeCmd;
import pl.shelter.dto.accounts.EditEmployeeCmd;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.model.accounts.PersonalId;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeConverter {
    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getVersion(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPersonId().toDtoString());
    }

    public static List<EmployeeDto> toDto(List<Employee> employees) {
        return null == employees ? null : employees.stream()
                .filter(Objects::nonNull)
                .map(EmployeeConverter::toDto)
                .collect(Collectors.toList());
    }

    public static Employee fromAddEmployeeCmd(AddEmployeeCmd addEmployeeCmd) {
        return new Employee(addEmployeeCmd.getLogin(),
                addEmployeeCmd.getPassword(),
                addEmployeeCmd.getEmail(),
                addEmployeeCmd.getFirstName(),
                addEmployeeCmd.getLastName(),
                PersonalId.valueOf(addEmployeeCmd.getPersonalId()));
    }

    public static Employee fromEditEmployeeCmd(EditEmployeeCmd editEmployeeCmd) {
        return new Employee(editEmployeeCmd.getEmail(),
                editEmployeeCmd.getFirstName(),
                editEmployeeCmd.getLastName());
    }
}
