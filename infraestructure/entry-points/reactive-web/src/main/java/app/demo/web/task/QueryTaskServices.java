package app.demo.web.task;

import app.demo.domain.todo.TaskToDo;
import app.demo.usecase.todo.QueryTasksUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import app.demo.domain.user.User;

@RestController
@RequestMapping(value = "/lifecoach", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QueryTaskServices {

    private final QueryTasksUseCase useCase;

    @GetMapping(path = "/{id}")
    public Mono<TaskWithUser> findWithDetails(@PathVariable("id") String id){
        return useCase.findTodoWithDetails(id)
            .map(t -> new TaskWithUser(t.getT1(), t.getT2()));
    }

    @GetMapping(path = "/expenses/categories")
    public Flux<String> getAllCategories(){
        return useCase.findAllCategories();
    }

    @GetMapping(path = "/expenses/categories/info")
    public Flux<String> getAllCategories(@RequestParam(name = "from") String from, @RequestParam(name = "to") String to){
        return useCase.findInfoAllCategories(from, to);
    }

    @Data
    @AllArgsConstructor
    private static class TaskWithUser {
        private TaskToDo task;
        private User user;
    }
}
