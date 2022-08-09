package cinema.controllers;

import cinema.domain.*;
import cinema.services.CinemaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


class Token {
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

@RestController
public class CinemaController {

    CinemaService cinemaService = new CinemaService();


    @GetMapping("/seats")
    @JsonView(Views.RowColumnPrice.class)
    public Cinema getSeats() {
        return cinemaService.getSeats();
    }

    @PostMapping("/purchase")
    @JsonView(Views.Full.class)
    public ResponseEntity<Seat> buyTicket(@RequestBody Seat seat) {
        return ResponseEntity.ok(cinemaService.buyTicket(seat));
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnedTicket> returnTicket(@RequestBody Token token) {
        return ResponseEntity.ok(cinemaService.returnTicket(token.getToken()));
    }

    @PostMapping("/stats")
    public ResponseEntity<Stats> returnStats(@RequestParam(required = false) String password) {
        String passwordText;
        passwordText = Objects.requireNonNullElse(password, "");
        return ResponseEntity.ok(cinemaService.returnStats(passwordText));
    }
}
