package br.com.seocodes.task_manager.filter;

//lembrando: servlet = programa Java que roda em um servidor para estender as funcionalidades de um servidor web (processar reqs, gerar respostas e muito mais)
import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

// define a clase como um Bean para o Spring gerenciar o ciclo de vida via Inversion of Control (IoC)
@Component
public class FilterTaskAuth implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        //IMPORTANTE, TODA REQUISIÇÃO VAI CHEGAR PRIMEIRO NO FILTRO E SOMENTE DEPOIS NO CONTROLLER
        System.out.println("Chegou no filtro.");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
