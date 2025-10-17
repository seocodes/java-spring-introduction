package br.com.seocodes.task_manager.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

// Getters e Setters de tudo são feitos automaticamente (Lombok)
@Data
// Garante que os objetos dessa classe sejam conhecidos lá no banco como elementos (linhas) de uma tabela
// literalmente, os atributos viram colunas lá no DB
@Entity(name="tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    // define que a coluna correspondente ao campo username no banco vai ser "usuario"
    //@Column(name = "usuario")
    private String username;

    private String name;
    private String password;

    @CreationTimestamp  // coluna createdAt já vai ser preenchida automaticamente no banco de dados
    private LocalDateTime createdAt;
}


