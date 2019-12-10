package app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@SpringBootApplication
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    //Descomentar para lanzar acción de prueba al iniciar la App
//    @Bean
//    public CommandLineRunner runner(CreateTasksUseCase useCase, AssignTasksUseCase assignTasksUseCase, CompleteTasksUseCase completeUseCase) throws InterruptedException {
//        return args -> useCase.createNew("Test1", "Desc 1")
//            .flatMap(task -> assignTasksUseCase.assignTask(task.getId(), "56"))
//            .block();
//    }
/*
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            headers.set("Request-Tracker", "52391922-a746-4354-a29c-a7f115e96446");
            headers.set("Session-Tracker", "85391922-a756-4376-a65c-a7f115e95446");
            headers.set("Channel", "LCA");
            headers.set("User-Key", "2366112");
            headers.set("Accept", "application/json");


/*
            Map<String, Object> map = new HashMap<>();
            map.put("Request-Tracker", "52391922-a746-4354-a29c-a7f115e96446");
            map.put("Session-Tracker", "85391922-a756-4376-a65c-a7f115e95446");
            map.put("Channel", "LCA");
            map.put("User-Key", "2366112");
            map.put("Accept", "application/json");
    *
            // build the request
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            try {
                String uri = "https://external-dev.apps.ambientesbc.com/lifecoach/v1/expenses/categories";
                //HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
                ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
                log.info(result.getBody());
            }
            catch(Exception e){
                log.info(e.toString());
            }
        };
    }
*/
}

