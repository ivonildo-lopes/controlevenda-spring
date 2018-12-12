package com.arquitetura.error;


import org.assertj.core.util.ArrayWrapperList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.arquitetura.log.service.LogExceptionService;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    private LogExceptionService logService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = {TransactionSystemException.class})
    public ApiErrorResponse handleTxException(TransactionSystemException ex) {
        try {
            Throwable rollback = ex.getCause();
            if (rollback instanceof RollbackException) {
                Throwable t = rollback.getCause();
                if (t instanceof ConstraintViolationException) {
                    Set<ConstraintViolation<?>> exp = ((ConstraintViolationException) t).getConstraintViolations();
                    List<String> errors = exp.stream().map(v -> v.getMessage()).collect(Collectors.toList());
                    return new ApiErrorResponse(500, 5001, errors);
                }
            }
            saveLog(ex);
            return new ApiErrorResponse(500, 5002, "Um erro inesperado ocorreu.");
        }catch (Exception e){
            return new ApiErrorResponse(500, 5002, "Um erro inesperado ocorreu.");
        }

    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
        if(ex instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> exp = ((ConstraintViolationException) ex).getConstraintViolations();
            List<String> errors = exp.stream().map(v -> v.getMessage()).collect(Collectors.toList());
            return new ApiErrorResponse(400, 4001, errors);
        }
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    @ExceptionHandler(value = { OptimisticLockException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(OptimisticLockException ex) {
        return new ApiErrorResponse(400, 4001, "O registro já foi atualizado anteriormente. Por favor, consulte o registro novamente antes de atualizar.");
    }

//    @ExceptionHandler(value = { BadCredentialsException.class })
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ApiErrorResponse badCredentialsException(BadCredentialsException ex) {
//        return new ApiErrorResponse(403, 4003, ex.getMessage());
//    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }

    @ExceptionHandler(value = { BadValueException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse badValueException(BadValueException ex) {
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

//    @ExceptionHandler(value = { AccessDeniedException.class })
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ApiErrorResponse accessException(AccessDeniedException ex) {
//        return new ApiErrorResponse(401, 4013, ex.getMessage());
//    }

//    @ExceptionHandler(value = { NoContentException.class })
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ApiErrorResponse accessException(NoContentException ex) {
//        return new ApiErrorResponse(204, 204, ex.getMessage());
//    }

    @ExceptionHandler(value = { ObjectOptimisticLockingFailureException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse accessException(ObjectOptimisticLockingFailureException ex) {
        return new ApiErrorResponse(404, 4001, "Registro desatualizado");
    }

//    @ExceptionHandler(value = { ClientLicitaWebException.class })
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiErrorResponse unknownException(ClientLicitaWebException ex) {
//        ApiErrorResponse error = new ApiErrorResponse(500, 5002, ex.getMessage());
//        saveLog(ex);
//        return error;
//    }
    
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError validacaoDtoError(MethodArgumentNotValidException ex) {
        ValidationError err = new ValidationError(400, 4001,Arrays.asList("Erro de validação"));
        
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
        	err.addErro(error.getField(), error.getDefaultMessage());
        }
        
        return err;
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Throwable ex) {
        ApiErrorResponse error = new ApiErrorResponse(500, 5002, "Um erro inesperado ocorreu.");
        saveLog(ex);
        return error;
    }
    public void saveLog(Throwable ex){
        logService.saveLog(ex);
        LOGGER.error(ex.getMessage());
    }
}