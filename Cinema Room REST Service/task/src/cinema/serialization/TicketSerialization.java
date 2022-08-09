package cinema.serialization;

import cinema.domain.Ticket;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TicketSerialization implements JsonSerializer<Ticket> {


    @Override
    public JsonElement serialize(Ticket ticket,
                                 Type typeOfSrc,
                                 JsonSerializationContext context) {
        JsonObject seatObj = new JsonObject();
        seatObj.addProperty("row", ticket.getRow());
        seatObj.addProperty("column", ticket.getColumn());
        seatObj.addProperty("price", ticket.getPrice());
        return seatObj;
    }
}
