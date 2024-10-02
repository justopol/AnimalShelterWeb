package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.EditAccountCmd;
import pl.shelter.rest.model.accounts.Account;

public class AccountConverter {

    public static Account fromEditAccountCmd(EditAccountCmd editAccountCmd) {
        return new Account(editAccountCmd.getEmail(),
                editAccountCmd.getFirstName(),
                editAccountCmd.getLastName());
    }
}
