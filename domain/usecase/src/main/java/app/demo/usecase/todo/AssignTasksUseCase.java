package app.demo.usecase.todo;

import app.demo.domain.auditoria.BusinessLogger;
import app.demo.domain.auditoria.DomainEvent;
import app.demo.domain.common.EventsGateway;
import app.demo.domain.common.UniqueIDGenerator;
import app.demo.domain.common.ex.BusinessException;
import app.demo.domain.lifecoach.LifeCoachGateway;
import app.demo.domain.movimientos.Movimiento;
import app.demo.domain.sesiones.SesionesRepository;
import app.demo.domain.sesiones.Session;
import app.demo.domain.todo.TaskToDo;
import app.demo.domain.todo.TaskToDoOperations;
import app.demo.domain.todo.events.TaskAssigned;
import app.demo.domain.todo.gateway.TaskToDoRepository;
import app.demo.domain.user.User;
import app.demo.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;
import reactor.util.function.Tuple2;

import java.util.Date;
import java.util.List;

import static reactor.core.publisher.Mono.*;
import static reactor.core.publisher.Mono.just;

@RequiredArgsConstructor
public class AssignTasksUseCase {


    private final SesionesRepository sesionesRepository;
    private final BusinessLogger businessLogger;
    private final LifeCoachGateway lifeCoachGateway;


    public Mono<List<Movimiento>> obtenerMovimientos(String idCliente, String idSession) {
        return sesionesRepository.findBy(idSession)
                .map(DomainEvent::createMovimientosEvent)
                .flatMap(domainEvent ->  zip(
                        businessLogger.saveBusinessEvent(domainEvent),
                        lifeCoachGateway.obtenerMovimientos(idCliente).collectList()
                )).map(Tuple2::getT2)
                .doOnSuccess(objects -> {
                    DomainEvent domainEvent = DomainEvent.createMovimientosEvent(objects.size() + "");
                    businessLogger.saveBusinessEvent(domainEvent).subscribe();
                });

    }



}
