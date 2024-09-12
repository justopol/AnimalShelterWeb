package pl.shelter.rest.utils.security;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import pl.shelter.rest.model.accounts.Account;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class JwtGenerator {

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String issuer;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.audiences")
    private String audiences;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.publickey.location")
    private String publicKeyLocation;

    @Inject
    @ConfigProperty(name = "pl.kurs.cr.rest.security.jwt_timeout_minutes", defaultValue = "15")
    private int jwtTimeoutMinutes;

    private String jwk;

    @PostConstruct
    public void loadJwk() {
        try {
            URL resourceUri = getClass().getClassLoader().getResource(publicKeyLocation);
            jwk = Files.readString(Path.of(resourceUri.toURI()), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateJwt(Account account) {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .keyID("rsakey")
                .build();
        LocalDateTime issueTime = LocalDateTime.now();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(jwtTimeoutMinutes);
        List<String> groups = new ArrayList<>();
        groups.add(account.getRole());
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer(issuer)
                .issueTime(Date.from(issueTime.atZone(ZoneId.systemDefault()).toInstant()))
                .expirationTime(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .subject(account.getLogin())
                .jwtID(UUID.randomUUID().toString())
                .audience(audiences)
                .claim("groups", groups)
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);
        try {
            RSAKey rsaKey = RSAKey.parse(jwk);
            jwt.sign(new RSASSASigner(rsaKey));
            return jwt.serialize();
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
