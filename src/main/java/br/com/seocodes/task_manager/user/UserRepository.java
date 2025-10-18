package br.com.seocodes.task_manager.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Repository = camada que fornece métodos que fazem comunicação com o banco de dados
// <UserModel, UUID> define a entidade e o tipo de ID dela
public interface UserRepository extends JpaRepository<UserModel, UUID>{
    UserModel findByUsername(String username);
}
