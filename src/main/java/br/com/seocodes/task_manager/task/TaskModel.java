package br.com.seocodes.task_manager.task;

import br.com.seocodes.task_manager.user.UserModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity(name="tb_tasks")
public class TaskModel {

    @Id // define como Primary Key
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private UUID idUser;

    @Column(length = 50) // limita o tamanho de caracteres do título pra 50
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
