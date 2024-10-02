package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.AddAccountCmd;

@ApplicationScoped
public class AdminRestClient extends AbstractRestClient<AccountDto> {

    public AdminRestClient() {
        super(AccountDto.class);
    }

    @Override
    protected WebTarget getTarget() { return super.getBaseTarget().path("admins"); };

    public void createAdmin(AddAccountCmd newAccount) {
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newAccount));
    }

}
