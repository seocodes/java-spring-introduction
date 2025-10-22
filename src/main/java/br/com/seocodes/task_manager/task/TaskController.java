package br.com.seocodes.task_manager.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
//obs: todas as explicações relevantes de controller, repository e model estão na camada User!
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    // O HttpServletRequest é pra pegar o ID que ele setta de atrib. lá no filter
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        System.out.println("Chegou no controller"); // Só pra mostrar que passa no Filter antes do Controller
        var idUser = request.getAttribute("idUser");

        // Validação de data
        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As datas de início/conclusão devem ser APÓS a data atual!");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término");
        }

        taskModel.setIdUser((UUID) idUser);
        var taskCreated = taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);

    }

    @GetMapping("/")
    public List<TaskModel> listTasks(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        return taskRepository.findByIdUser((UUID) idUser);
    }

    // /tasks/589439-abshgfdsa-43222 -> id, variável no path/dentro da rota
    // no Spring, usamos o @PathVariable para pegar essa variável/informação do path
    @PutMapping("/{id}")  // nome da variável que vamos receber - {id} é um parâmetro, o @PathVariable vai substituí-lo pelo o que vai estar no path
    public TaskModel update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        taskModel.setId(id);
        return taskRepository.save(taskModel);
    }
}
