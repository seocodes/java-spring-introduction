package br.com.seocodes.task_manager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Customização de erros
@ControllerAdvice  // anotação para definir classes globais no momento de tratamento de exceções
// toda exceção que ocorrer vai passar por aqui, e se a exceção for de um tipo que tratamos aqui, fará a ação que declararmos
public class ExceptionHandlerController {
    // pra interceptar a exceção de tipo HttpMessageNotReadable e retornar uma resposta personalizada
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
