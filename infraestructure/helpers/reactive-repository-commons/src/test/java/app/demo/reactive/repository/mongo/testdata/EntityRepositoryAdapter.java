package app.demo.reactive.repository.mongo.testdata;

import app.demo.reactive.repository.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EntityRepositoryAdapter extends AdapterOperations<DomainEntity, DataEntity, String, DataRepository> {

    public EntityRepositoryAdapter(DataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, DomainEntity.DomainEntityBuilder.class).build());
    }
}
