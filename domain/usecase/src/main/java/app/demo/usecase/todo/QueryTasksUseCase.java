package app.demo.usecase.todo;

import app.demo.domain.todo.TaskToDo;
import app.demo.domain.todo.gateway.TaskToDoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import app.demo.domain.common.StringUtils;
import app.demo.domain.user.User;
import app.demo.domain.user.gateway.UserGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.time.Duration;

import static reactor.core.publisher.Flux.defer;
import static reactor.core.publisher.Mono.just;

import static reactor.core.publisher.Mono.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RequiredArgsConstructor
public class QueryTasksUseCase {

    public static final String GITHUB_API_BASE_URL = "https://external-dev.apps.ambientesbc.com/lifecoach/v1/expenses";
    private final TaskToDoRepository tasks ;
    private final UserGateway usersGateway ;

    //Caché de 30 segundos para optimizar las consultas a la lista de todas las tareas (Opcional)
    private final Flux<TaskToDo> allTasks = defer(this::doFindAll).cache(Duration.ofSeconds(30));

    private final WebClient webClient;

    private static final Logger log = LoggerFactory.getLogger(QueryTasksUseCase.class);

    public Flux<String> findAllCategories() {
        return webClient.get().uri("/categories/actions/config").retrieve().bodyToFlux(String.class);
    }

    public Flux<String> findInfoAllCategories(String from, String to) {
        return webClient.get().uri("/categories").retrieve().bodyToFlux(String.class);
    }

    /*
    public Flux<String> findAll() {
        //return allTasks;
        //return Mono.just(s.toUpperCase())))
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        headers.set("Request-Tracker", "52391922-a746-4354-a29c-a7f115e96446");
        headers.set("Session-Tracker", "85391922-a756-4376-a65c-a7f115e95446");
        headers.set("Channel", "LCA");
        headers.set("User-Key", "2366112");
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String solve = "";
        try {
            String uri = "https://external-dev.apps.ambientesbc.com/lifecoach/v1/expenses/categories";
            //HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            solve = result.getBody();
        }
        catch(Exception e){
            solve = e.toString();
        }
        return Flux.just("", solve);
    }

     */

    public Mono<Tuple2<TaskToDo, User>> findTodoWithDetails(String id) {
        return tasks.findById(id).flatMap(task ->
            !StringUtils.isEmpty(task.getAssignedUserId()) ?
                zip(just(task), usersGateway.findById(task.getAssignedUserId())) :
                zip(just(task), just(User.builder().build())));
    }

    private Flux<TaskToDo> doFindAll() {
        return tasks.findAll();
    }
}
