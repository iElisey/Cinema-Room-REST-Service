package cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;
    @JsonIgnore
    private final List<Seat> reservedSeats;

    @JsonIgnore
    private String password;

    public Cinema(int totalColumns, int totalRows, String password) {

        this.totalColumns = totalColumns;
        this.totalRows = totalRows;
        this.availableSeats = generateSeats();
        this.reservedSeats = new ArrayList<>();
        this.password=password;
    }


    private List<Seat> generateSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                seats.add(new Seat(i, j));
            }
        }
        return seats;
    }

    public List<Seat> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Seat buyingSeat) {
        reservedSeats.add(buyingSeat);
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Seat buyingSeat) {
        availableSeats.removeIf(availableSeat -> availableSeat.getRow() == buyingSeat.getRow() && availableSeat.getColumn() == buyingSeat.getColumn());

    }
    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }


    @Override
    public String toString() {
        return "Cinema{" +
                "totalRows=" + totalRows +
                ", totalColumns=" + totalColumns + "\n" +
                ", availableSeats=" + availableSeats +  "\n" +
                ", reservedSeats=" + reservedSeats +  "\n" +
                '}' + "\n";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}