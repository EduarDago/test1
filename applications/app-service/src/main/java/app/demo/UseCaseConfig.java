package app.demo;

import app.demo.domain.todo.gateway.TaskToDoRepository;
import app.demo.usecase.todo.*;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import app.demo.domain.common.EventsGateway;
import app.demo.domain.user.gateway.UserGateway;
import app.demo.domain.user.gateway.UserScoreGateway;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateTasksUseCase createTasksUseCase(TaskToDoRepository tasks, EventsGateway eventGateway) {
        return new CreateTasksUseCase(tasks, eventGateway);
    }
/*
    @Bean
    public AssignTasksUseCase assignTasksUseCase(TaskToDoRepository tasks, UserGateway users, EventsGateway eventsGateway) {
        return new AssignTasksUseCase(tasks, users, eventsGateway);
    }
*/
    @Bean
    public CompleteTasksUseCase completeTasksUseCase(TaskToDoRepository tasks, EventsGateway eventsGateway, UserScoreGateway userScoreGateway) {
        return new CompleteTasksUseCase(tasks, eventsGateway, userScoreGateway);
    }

    @Bean
    public ReAssignUserTasksUseCase reAssignUserTasksUseCase(TaskToDoRepository tasks) {
        return new ReAssignUserTasksUseCase(tasks);
    }

    @Bean
    public QueryTasksUseCase queryTasksUseCase(TaskToDoRepository tasks, UserGateway usersGateway) {
        WebClient webClient = WebClient.builder()
                .baseUrl(QueryTasksUseCase.GITHUB_API_BASE_URL)
                .defaultHeader("Request-Tracker", "52391922-a746-4354-a29c-a7f115e96446")
                .defaultHeader("Session-Tracker", "85391922-a756-4376-a65c-a7f115e95446")
                .defaultHeader("Channel", "LCA")
                .defaultHeader("User-Key", "2366112")
                .defaultHeader("Accept", "application/json")
                .build();
        return new QueryTasksUseCase(tasks, usersGateway, webClient);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

}
