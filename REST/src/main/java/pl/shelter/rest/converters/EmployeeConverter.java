package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.AddEmployeeCmd;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.model.accounts.PersonalId;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeConverter {
    public static AccountDto toDto(Employee employee) {
        return new AccountDto(
                employee.getId(),
                employee.getVersion(),
                employee.getRole(),
                employee.getLogin(),
                employee.isActive(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPersonId().toDtoString());
    }

    public static List<AccountDto> toDto(List<Employee> employees) {
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

}
