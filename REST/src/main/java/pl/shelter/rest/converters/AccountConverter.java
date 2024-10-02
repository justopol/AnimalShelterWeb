package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.EditAccountCmd;
import pl.shelter.rest.model.accounts.Account;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountConverter {

    public static Account fromEditAccountCmd(EditAccountCmd editAccountCmd) {
        return new Account(editAccountCmd.getEmail(),
                editAccountCmd.getFirstName(),
                editAccountCmd.getLastName());
    }
    public static AccountDto toDto(Account account) {
        return new AccountDto(account.getId(),
                account.getVersion(),
              //  account.getRole(),
              //  account.getLogin(),
              //  account.isActive(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                PersonalIdConverter.toDto(account.getPersonId()).getId() );
    }
    public static List<AccountDto> toDto(List<Account> accounts) {
        return (null == accounts ? null : accounts.stream()
                .filter(Objects::nonNull)
                .map(element -> toDto(element))
                .collect(Collectors.toList())
        );
    }
}
