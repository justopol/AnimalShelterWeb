package pl.shelter.web.security;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;

@Dependent
@Alternative
@Priority(30)
public class PlainTextHashGenerator implements HashGenerator{

    @Override
    public String generateHash(String input) {
        return input;
    }
    
}
