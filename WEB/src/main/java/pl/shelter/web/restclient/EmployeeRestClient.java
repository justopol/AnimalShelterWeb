package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.AddAccountCmd;

@ApplicationScoped
public class EmployeeRestClient extends AbstractRestClient<AccountDto> {

    public EmployeeRestClient() {
        super(AccountDto.class);
    }

    @Override
    protected WebTarget getTarget() { return super.getBaseTarget().path("employees"); };

    public void createEmployee(AddAccountCmd newAccount) {
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newAccount));
    }

}
