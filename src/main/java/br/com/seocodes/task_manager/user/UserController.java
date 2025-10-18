package br.com.seocodes.task_manager.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    // @RequestBody para indicar que o parâmetro (UserModel user) vai ser preenchido com os dados que vem no corpo da req
    // O Spring converte o corpo da requisição (normalmente JSON) em um objeto UserModel
    public ResponseEntity create(@RequestBody UserModel userModel){ // ResponseEntity = permite manipular a resposta HTTP, incluindo body, header e STATUS! Mais flexível
        var user = userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            //MENSAGEM DE ERRO NO BODY E STATUS CODE DE ERRO
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }
        var userCreated = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
