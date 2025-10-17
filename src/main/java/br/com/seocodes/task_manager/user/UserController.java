package br.com.seocodes.task_manager.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
// controller = componente que vai ser uma camada entre a requisição e as demais camadas (service, repository etc)
// recebe a requisição do usuário - primeira camada de acesso
public class UserController {

    @Autowired  // Spring inicializa e gerencia o ciclo de vida do repository
    private UserRepository userRepository;

    /*
    * MÉTODOS DE ACESSO DO HTTP
    * GET - buscar um dado/informação
    * POST - adicionar um dado/informação
    * PUT - alterar um dado/informação
    * DELETE - remover um dado/informação
    * PATCH - alterar somente uma parte da info/dado
    */

    // POST indiica que o navegador tá enviando dados pro servidor
    // nesse caso os dados serão passados no corpo da requisição (como visto abaixo)
    @PostMapping("/")
    // @RequestBody para indiicar que o parâmetro (UserModel user) vai ser preenchido com os dados que vem no corpo da req
    // O Spring converte o corpo da requisição (normalmente JSON) em um objeto UserModel
    public UserModel create(@RequestBody UserModel user){
        var userCreated = userRepository.save(user);
        return userCreated;  //envia pro Insomnia a "preview" do userModel criado (com atributos ID e createdAt também)
    }
}
