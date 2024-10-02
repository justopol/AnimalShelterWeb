package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.AddAccountCmd;
import pl.shelter.dto.accounts.PersonalIdDto;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.Admin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdminConverter {
    public static AccountDto toDto(Account account) {
        return new AccountDto(account.getId(),
                account.getVersion(),
                account.getRole(),
                account.getLogin(),
                account.isActive(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                PersonalIdConverter.toDto(account.getPersonId()).getId() );
    }
    public static Admin fromCreateAccountCmdToAdmin(AddAccountCmd cmd) {
        return new Admin(cmd.getLogin(),
                cmd.getPassword(),
                cmd.getEmail(),
                cmd.getFirstName(),
                cmd.getLastName(),
                PersonalIdConverter.fromDto(new PersonalIdDto(cmd.getPersonalId())));
    }
    public static List<AccountDto> toDtoFromAdmin(List<Admin> admins) {
        return (null == admins ? null : admins.stream()
                .filter(Objects::nonNull)
                .map(element -> toDto(element))
                .collect(Collectors.toList())
        );
    }
}
