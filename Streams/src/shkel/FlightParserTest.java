package shkel;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FlightParserTest {
    FlightParser parser = new FlightParser();

    @Test
    void parse() {
        Exception exception = assertThrows(FlightParserException.class, () -> parser.parse(null, ","));
        assertEquals("The string is not initialized", exception.getMessage());
        exception = assertThrows(FlightParserException.class, () -> parser.parse(",,,,,,,,", ","));
        assertEquals("Not enough data", exception.getMessage());
        assertDoesNotThrow(() -> parser.parse("3;1;10/3/16;AA;N786AA;10721;BOS;Massachusetts;12478;JFK;New York;556;-4;623;703;709;-6;0;;0;40;187", ";"));
    }

    @Test
    void parseDefaultDelimiter() {
        assertDoesNotThrow(() -> parser.parse("3,1,10/3/16,AA,N786AA,10721,BOS,Massachusetts,12478,JFK,New York,556,-4,623,703,709,-6,0,,0,40,187"));
        Exception exception = assertThrows(FlightParserException.class, () -> parser.parse("3;1;10/3/16;AA;N786AA;10721;BOS;Massachusetts;12478;JFK;New York;556;-4;623;703;709;-6;0;;0;40;187"));
        assertEquals("Not enough data", exception.getMessage());
        try {
            assertEquals(
                    new Flight.Builder(new Date(116, Calendar.OCTOBER, 3), "AA", 10721, 12478)
                            .airTime(40)
                            .arrDelay(-6)
                            .arrTime(LocalTime.of(7, 9))
                            .cancelled(false)
                            .dayOfMonth((byte) 3)
                            .dayOfWeek(DayOfWeek.MONDAY)
                            .depDelay(-4)
                            .depTime(LocalTime.of(5, 56))
                            .dest("JFK")
                            .destStateName("New York")
                            .distance(187)
                            .diverted(false)
                            .origin("BOS")
                            .originStateName("Massachusetts")
                            .tailNum("N786AA")
                            .wheelsOff(LocalTime.of(6, 23))
                            .wheelsOn(LocalTime.of(7, 3))
                            .build(),
                    parser.parse("3,1,10/3/16,AA,N786AA,10721,BOS,Massachusetts,12478,JFK,New York,556,-4,623,703,709,-6,0,,0,40,187")
            );
        } catch (FlightParserException e) {
            System.out.println(e.getMessage());
        }
    }
}