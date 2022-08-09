package cinema.domain;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Objects;
import java.util.UUID;

public class Seat {

    @JsonView(Views.Full.class)
    private UUID token;

    @JsonView(Views.RowColumnPrice.class)

    private int row;
    @JsonView(Views.RowColumnPrice.class)
    private int column;
    @JsonView(Views.RowColumnPrice.class)
    private int price;

    @JsonView(Views.Full.class)
    private Ticket ticket;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = selectPrice();
        this.ticket = new Ticket(row, column, price);
    }


    private int selectPrice() {
        return row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = selectPrice();
    }

    @Override
    public String toString() {
        return "Seat{" +
                "token=" + token +
                ", row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", ticket=" + ticket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column && price == seat.price && Objects.equals(token, seat.token) && Objects.equals(ticket, seat.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, row, column, price, ticket);
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}