package br.com.seocodes.task_manager.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        taskModel.setIdUser((UUID) idUser);
        var taskCreated = taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }
}
