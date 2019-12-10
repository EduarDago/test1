package app.demo.domain.sesiones;

import reactor.core.publisher.Mono;

public interface SesionesRepository {

    Mono<Session> findBy(String idSession);

}
