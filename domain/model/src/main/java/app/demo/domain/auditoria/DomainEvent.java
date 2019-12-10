package app.demo.domain.auditoria;

import app.demo.domain.sesiones.Session;
import lombok.Builder;

import java.util.Date;

@Builder
public class DomainEvent {

    private final String idSession;
    private final String idUser;
    private final String txCode;
    private final Date timestamp;

    public static DomainEvent createMovimientosEvent(Session session) {
        return DomainEvent.builder()
                .idSession(session.getId())
                .idUser(session.getIdUser())
                .txCode("765756")
                .timestamp(new Date()).build();
    }

    public static DomainEvent createMovimientosEvent(String idTX) {
        return DomainEvent.builder()
                //.idSession(session.getId())
                //.idUser(session.getIdUser())
                .txCode(idTX)
                .timestamp(new Date()).build();
    }




}
