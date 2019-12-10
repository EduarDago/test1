package app.demo.domain.lifecoach;

import app.demo.domain.movimientos.Movimiento;
import reactor.core.publisher.Flux;

public interface LifeCoachGateway {

    Flux<Movimiento> obtenerMovimientos(String idUser);

}
