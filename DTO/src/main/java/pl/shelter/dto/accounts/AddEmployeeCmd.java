package pl.shelter.dto.accounts;

public class AddEmployeeCmd extends AddAccountCmd {
    public AddEmployeeCmd() {
    }

    public AddEmployeeCmd(String login, String password, String email, String firstName, String lastName, String personalId) {
        super(login, password, email, firstName, lastName, personalId);
    }
}
