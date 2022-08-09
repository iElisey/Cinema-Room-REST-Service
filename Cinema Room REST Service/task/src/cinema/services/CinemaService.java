package cinema.services;

import cinema.domain.*;
import cinema.exceptions.BuiyngException;
import cinema.exceptions.UnauthorizedException;

import java.util.UUID;

public class CinemaService {

    private final Cinema cinema;

    private final Stats stats;

    public CinemaService() {
        cinema = new Cinema(9, 9, "super_secret");
        stats = new Stats(0, cinema.getTotalColumns() * cinema.getTotalRows(), 0);
    }

    public Cinema getSeats() {
        return cinema;
    }

    public Seat buyTicket(Seat seat) {
        for (Seat reservedSeat : cinema.getReservedSeats()) {
            if (reservedSeat.getColumn() == seat.getColumn() && seat.getRow() == reservedSeat.getRow()) {
                throw new BuiyngException("The ticket has been already purchased!");
            }
        }
        if (seat.getRow() > 9 || seat.getRow() < 1 || (seat.getColumn() > 9 || seat.getColumn() < 1)) {
            throw new ArrayIndexOutOfBoundsException("The number of a row or a column is out of bounds!");

        } else {
            seat.setPrice();
            seat.setToken(UUID.randomUUID());
            seat.setTicket(new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice()));
            stats.setCurrent_income(seat.getPrice());
            stats.setNumber_of_purchased_tickets(1);
            stats.minusNumber_of_available_seats(1);

            cinema.setReservedSeats(seat);
            cinema.setAvailableSeats(seat);

            return seat;
        }
    }

    public ReturnedTicket returnTicket(String token) {
        if (cinema.getReservedSeats().size() != 0) {
            for (Seat reservedSeat : cinema.getReservedSeats()) {
                System.out.println(reservedSeat.getToken().toString());
                if (reservedSeat.getToken().toString().equalsIgnoreCase(token)) {
                    cinema.getReservedSeats().remove(reservedSeat);
                    stats.minusCurrent_income(reservedSeat.getPrice());
                    stats.setNumber_of_available_seats(1);
                    stats.minusNumber_of_purchased_tickets(1);
                    return new ReturnedTicket(reservedSeat.getRow(), reservedSeat.getColumn(), reservedSeat.getPrice());
                }
            }
            throw new BuiyngException("Wrong token!");
        }
        throw new BuiyngException("Wrong token!");
    }

    public Stats returnStats(String password) {
        System.out.println(password);
        if (password.equals(cinema.getPassword()) && !password.isEmpty()) {
            return stats;
        } else {
            throw new UnauthorizedException("The password is wrong!");
        }
    }
}