package app.demo.domain.auditoria;

import reactor.core.publisher.Mono;

public interface BusinessLogger {
    Mono<DomainEvent> saveBusinessEvent(DomainEvent domainEvent);
}
