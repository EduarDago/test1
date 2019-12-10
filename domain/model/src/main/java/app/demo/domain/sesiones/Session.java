package app.demo.domain.sesiones;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Session {
    private final String idUser;
    private final String id;

}
