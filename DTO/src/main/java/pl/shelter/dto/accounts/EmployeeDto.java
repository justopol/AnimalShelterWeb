package pl.shelter.dto.accounts;

import java.util.UUID;

public class EmployeeDto extends AccountDto {
    public EmployeeDto() {
    }

    public EmployeeDto(UUID id, long version, String firstName, String lastName, String email, String personId) {
        super(id, version, firstName, lastName, email, personId);
    }
}
