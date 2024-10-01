package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AddAccountCmd;
import pl.shelter.dto.accounts.AddEmployeeCmd;
import pl.shelter.dto.accounts.EmployeeDto;

@ApplicationScoped
public class EmployeeRestClient extends AbstractRestClient<EmployeeDto> {

    public EmployeeRestClient() {
        super(EmployeeDto.class);
    }

    @Override
    protected WebTarget getTarget() { return super.getBaseTarget().path("employees"); };

    public void createEmployee(AddAccountCmd newAccount) {
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newAccount));
    }

}
