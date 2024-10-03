package pl.shelter.web.producers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import java.lang.reflect.Member;
import java.util.logging.Logger;

@ApplicationScoped
public class LoggerProducer {

    @Produces
    @Dependent
    public Logger getLogger(InjectionPoint ip) {
        Member member = ip.getMember();
        String loggerName = member.getDeclaringClass().getName();
        return Logger.getLogger(loggerName);
    }
}
