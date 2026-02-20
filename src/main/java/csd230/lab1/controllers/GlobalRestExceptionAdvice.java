package csd230.lab1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalRestExceptionAdvice {

    private ResponseEntity<Map<String, Object>> notFoundResponse(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBookNotFound(BookNotFoundException ex) {
        return notFoundResponse(ex.getMessage());
    }

    @ExceptionHandler(MagazineNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMagazineNotFound(MagazineNotFoundException ex) {
        return notFoundResponse(ex.getMessage());
    }

    @ExceptionHandler(DiscMagNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDiscMagNotFound(DiscMagNotFoundException ex) {
        return notFoundResponse(ex.getMessage());
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTicketNotFound(TicketNotFoundException ex) {
        return notFoundResponse(ex.getMessage());
    }
}
