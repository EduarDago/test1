package app.demo.domain.common;

import reactor.core.publisher.Mono;

/**
 * Interfaz propia del dominio para emitir eventos completamente definidos por las necesidades del dominio
 * El tipo Event se puede enriquecer en la medida que sea necesario.
 */
public interface EventsGateway {
    Mono<Void> emit(Event event);
}
