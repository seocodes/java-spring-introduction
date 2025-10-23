package br.com.seocodes.task_manager.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID idUser);

    // Seria uma opção pra validar se o usuário pode alterar a Task lá no controller
//    TaskModel findByIdAndIdUser(UUID id, UUID idUser);
}
