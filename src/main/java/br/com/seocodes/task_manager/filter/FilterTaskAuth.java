package br.com.seocodes.task_manager.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.seocodes.task_manager.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

// define a classe como um Bean para o Spring gerenciar o ciclo de vida via Inversion of Control (IoC)
@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    //IMPORTANTE:
    //TODA REQ VAI CHEGAR PRIMEIRO NO FILTRO E SOMENTE DEPOIS NO CONTROLLER
    @Override
    //lembrando: servlet = programa Java que roda em um servidor para estender as funcionalidades de um servidor web (processar reqs, gerar respostas e muito mais)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        if(servletPath.startsWith("/tasks/")){
            //Pegar a auth/autenticação (usuário e senha)
            var authentication = request.getHeader("Authorization");

            // !substring vai "remover" (na verdade começar a string a partir do indíce correspondente ao final daquela string, length)
            //o "Basic" que vem escrito na authentication e deixar somente o código em base64!
            var authEncoded = authentication.substring("Basic".length()).trim();  //.trim() vai remover os espaços em branco

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecoded);  // pra ficar normal e não em byte né

            // Split separa os dois elementos que estavam separados por ":"
            // em dois elementos separados num array
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Validar usuário
            var user = userRepository.findByUsername(username);
            if(user == null){
                response.sendError(401);  //401 = Unauthorized
            } else{
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if(passwordVerify.verified){
                    // Pra passar o ID daqui pro controller ao invés de ter que mandar o ID na requisição também
                    request.setAttribute("idUser", user.getId());

                    // Segue viagem
                    filterChain.doFilter(request, response);
                }
                else{
                    response.sendError(401);
                }
            }
        }else{
            // Segue viagem se for da rota users (pq não tem o que autenticar né)
            filterChain.doFilter(request, response);
        }
    }
}
