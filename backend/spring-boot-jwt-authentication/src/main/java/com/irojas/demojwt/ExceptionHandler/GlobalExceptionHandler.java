package com.irojas.demojwt.ExceptionHandler;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex)
    {
        logger.error("EmptyResultDataAccessException occurred", ex);
        return new ResponseEntity<String>("No se encontraron resultados para la consulta",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDataIntegrityViolationException(DataIntegrityViolationException ex)
    {
        logger.error("DataIntegrityViolationException occurred", ex);
        return new ResponseEntity<String>("Violación de integridad de datos",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handlerDataAccessException(DataAccessException ex)
    {
        logger.error("DataAccessException occurred", ex);
        return new ResponseEntity<String>("Error al acceder a la base de datos",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<String> handlerJpaSystemException(JpaSystemException ex)
    {
        logger.error("JpaSystemException occurred", ex);
        return new ResponseEntity<String>("Error en la base de datos",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException ex)
    {
        logger.error("ConstraintViolationException occurred", ex);
        return new ResponseEntity<String>("Violación de constraint en la base de datos",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handlerRuntimeException(RuntimeException ex)
    {
        logger.error("RuntimeException occurred", ex);
        return new ResponseEntity<String>("Error interno del servidor",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}