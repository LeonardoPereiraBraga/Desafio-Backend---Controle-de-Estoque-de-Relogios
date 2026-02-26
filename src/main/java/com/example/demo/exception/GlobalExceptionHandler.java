package com.example.demo.exception;

import com.example.demo.dto.ErroCampo;
import com.example.demo.dto.RestError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestError> handleEnumError(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {

        if (ex.getRequiredType() != null && ex.getRequiredType().isEnum()) {

            RestError error = new RestError(
                    Instant.now().toString(),
                    400,
                    "Requisição inválida",
                     ex.getName() + " inválido: " + ex.getValue(),
                    request.getRequestURI(),
                    List.of()
            );

            return ResponseEntity.badRequest().body(error);
        }

        throw ex;
    }

    @ExceptionHandler(RelogioNaoEncontrado.class)
    private ResponseEntity<RestError> relogioNaoEncontradoHandler(RelogioNaoEncontrado exception, WebRequest request){
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
        String caminho = ((ServletWebRequest) request).getRequest().getRequestURI();

        String timestamp = Instant.now().toString();
        Map<String, String> pathVariables =
                (Map<String, String>) httpServletRequest.getAttribute(
                        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String id = pathVariables != null ? pathVariables.get("id") : null;


        RestError restError = new RestError(timestamp,404,"Requisição inválida","Relógio não encontrado: " + id,caminho,List.of());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        RestError restErrors = new RestError();
         List<ErroCampo> ErrosCampo = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            ErroCampo erroCampo = new ErroCampo(fieldName, message);
            ErrosCampo.add(erroCampo);
        });
        restErrors.setTimestamp(Instant.now().toString());
        restErrors.setStatus(status.value());
        restErrors.setErro("Requisição inválida");
        restErrors.setMensagem("Erro de validação");
        restErrors.setErrosDeCampo(ErrosCampo);
        restErrors.setCaminho(servletWebRequest.getRequest().getRequestURI());

        return new ResponseEntity<>(restErrors, HttpStatus.BAD_REQUEST);
    }

}
