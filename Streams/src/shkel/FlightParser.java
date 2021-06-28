package shkel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

/**
 * Class for converting string information to structure.
 */
class FlightParser {
    private boolean debugMode = false;
    private final HashMap<String, Integer> correspondenceTable = new HashMap<>();

    // TODO: get the table from outside
    // replace string with enum ?
    public FlightParser() {
        correspondenceTable.put("flightDate", 2);
        correspondenceTable.put("dayOfMonth", 0);
        correspondenceTable.put("dayOfWeek", 1);
        correspondenceTable.put("uniqueCarrier", 3);
        correspondenceTable.put("originAirportID", 5);
        correspondenceTable.put("destAirportID", 8);
        correspondenceTable.put("tailNum", 4);
        correspondenceTable.put("origin", 6);
        correspondenceTable.put("originStateName", 7);
        correspondenceTable.put("dest", 9);
        correspondenceTable.put("destStateName", 10);
        correspondenceTable.put("depTime", 11);
        correspondenceTable.put("depDelay", 12);
        correspondenceTable.put("wheelsOff", 13);
        correspondenceTable.put("wheelsOn", 14);
        correspondenceTable.put("arrTime", 15);
        correspondenceTable.put("arrDelay", 16);
        correspondenceTable.put("cancelled", 17);
        correspondenceTable.put("cancellationCode", 18);
        correspondenceTable.put("diverted", 19);
        correspondenceTable.put("airTime", 20);
        correspondenceTable.put("distance", 21);
    }

    /**
     * Parse a string in a flight information structure.
     * @param raw plain text
     * @param delimiter the character of which the data is separated
     * @return flight information
     * @throws FlightParserException if error is occur
     */
    public Flight parse(String raw, String delimiter) throws FlightParserException {
        if (raw == null) throw new FlightParserException("The string is not initialized");
        String[] values = raw.split(delimiter);
        return parseRequiredData(values)
                .airTime(parseNullablePositiveInt(values[getFieldNumberByName("airTime")], "airTime"))
                .arrTime(parseNullableLocalTime(values[getFieldNumberByName("arrTime")], "arrTime"))
                .arrDelay(parseNullableInt(values[getFieldNumberByName("arrDelay")], "arrDelay"))
                .cancellationCode(parseNullableCancellationCode(values[getFieldNumberByName(("cancellationCode"))], "cancellationCode"))
                .cancelled(parseNullableBool(values[getFieldNumberByName(("cancelled"))], "cancelled"))
                .dayOfMonth(parseNullableDayOfMonth(values[getFieldNumberByName(("dayOfMonth"))], "dayOfMonth"))
                .dayOfWeek(parseNullableDayOfWeek(values[getFieldNumberByName(("dayOfWeek"))], "dayOfWeek"))
                .depDelay(parseNullableInt(values[getFieldNumberByName("depDelay")], "depDelay"))
                .depTime(parseNullableLocalTime(values[getFieldNumberByName("depTime")], "depTime"))
                .dest(parseNullableString(values[getFieldNumberByName("dest")], "dest"))
                .destStateName(parseNullableString(values[getFieldNumberByName("destStateName")], "destStateName"))
                .distance(parseNullablePositiveInt(values[getFieldNumberByName("distance")], "distance"))
                .diverted(parseNullableBool(values[getFieldNumberByName(("diverted"))], "diverted"))
                .origin(parseNullableString(values[getFieldNumberByName("origin")], "origin"))
                .originStateName(parseNullableString(values[getFieldNumberByName("originStateName")], "originStateName"))
                .tailNum(parseNullableString(values[getFieldNumberByName("tailNum")], "tailNum"))
                .wheelsOff(parseNullableLocalTime(values[getFieldNumberByName("wheelsOff")], "wheelsOff"))
                .wheelsOn(parseNullableLocalTime(values[getFieldNumberByName("wheelsOn")], "wheelsOn"))
                .build();
    }

    /**
     * Parse a string in a flight information structure. Comma is a delimiter.
     * @param raw plain text
     * @return flight information
     * @throws FlightParserException if error is occur
     */
    public Flight parse(String raw) throws FlightParserException {
        return parse(raw, ",");
    }

    /**
     * Parse an array of strings in Flight.Builder with only required fields.
     * @param values the array of plain strings
     * @return builder with initialized required fields
     * @throws FlightParserException if the data is mandatory and cannot be recognized
     */
    private Flight.Builder parseRequiredData(String[] values)  throws FlightParserException {
        if (values.length < 22) {
            throw new FlightParserException("Not enough data");
        }

        String emptyFieldError = "";
        if (isEmptyField(values[getFieldNumberByName("flightDate")])) emptyFieldError += ", flightDate";
        if (isEmptyField(values[getFieldNumberByName("uniqueCarrier")])) emptyFieldError += ", uniqueCarrier";
        if (isEmptyField(values[getFieldNumberByName("originAirportID")])) emptyFieldError += ", originAirportID";
        if (isEmptyField(values[getFieldNumberByName("destAirportID")])) emptyFieldError += ", destAirportID";
        if (!emptyFieldError.equals("")) {
            throw new FlightParserException("Empty fields found : " + emptyFieldError.replaceFirst(", ", ""));
        }

        try {
            return new Flight.Builder(
                    new SimpleDateFormat("MM/dd/yy").parse(values[getFieldNumberByName("flightDate")]),
                    values[getFieldNumberByName("uniqueCarrier")],
                    parsePositiveInt(values[getFieldNumberByName("originAirportID")], "originAirportID"),
                    parsePositiveInt(values[getFieldNumberByName("destAirportID")], "destAirportID")
            );
        } catch (ParseException e) {
            throw new FlightParserException("Wrong date : " + values[0] + " [" + e.getMessage() + "]");
        }
    }

    private boolean isEmptyField(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Parse string in positive integer.
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return positive int value. Not null
     * @throws FlightParserException if the text has a wrong format
     */
    private int parsePositiveInt(String raw, String fieldName) throws FlightParserException {
        try {
           int res = Integer.parseInt(raw);
           if (res < 1) {
               throw new FlightParserException("Wrong " + fieldName + " : " + raw);
           } else {
               return res;
           }
        } catch (NumberFormatException e) {
            throw new FlightParserException("Wrong " + fieldName + " : " + raw + " [" + e.getMessage() + "]");
        }
    }

    /**
     * Parse string in positive integer or null.
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return positive int value or null
     */
    private Integer parseNullablePositiveInt(String raw, String fieldName) {
        try {
            return parsePositiveInt(raw, fieldName);
        } catch (FlightParserException e) {
            if (debugMode) System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Parse string in integer or null
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return integer value or null
     */
    private Integer parseNullableInt(String raw, String fieldName) {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + e.getMessage());
            return null;
        }
    }

    /**
     * Parse string in boolean
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return boolean or null
     */
    private Boolean parseNullableBool(String raw, String fieldName) {
        Integer val = parseNullableInt(raw, fieldName);
        if (val == null || (val != 0 && val != 1)) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + " [value = " + raw + "]");
            return null;
        } else {
            return val != 0;
        }
    }

    /**
     * if the string is empty then convert it to Null.
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return string or null
     */
    private String parseNullableString(String raw, String fieldName) {
        if (raw.isBlank()) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + " [value = " + raw + "]");
            return null;
        } else {
            return raw;
        }
    }

    /**
     * Parse string in CancellationCode
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return CancellationCode or null
     */
    private Flight.CancellationCode parseNullableCancellationCode(String raw, String fieldName) {
        try {
            return Flight.CancellationCode.valueOf(raw.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + " [" + raw + "] " + e.getMessage());
            return null;
        }
    }

    /**
     * Parse string in LocalTime.
     * @param raw plain text in 'HHmm'/'Hmm'/'mm' format
     * @param fieldName will be displayed in the error text
     * @return LocalTime or null
     */
    private LocalTime parseNullableLocalTime(String raw, String fieldName) {
        try {
            return LocalTime.parse("00"+raw.trim(), DateTimeFormatter.ofPattern("Hmm"));
        } catch (DateTimeParseException e) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + " [" + raw + "] " + e.getMessage());
            return null;
        }
    }

    /**
     * Parse string in 1-31 number.
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return the number of day or null
     */
    // we don't check the month
    private Byte parseNullableDayOfMonth(String raw, String fieldName) {
        try {
            byte value = Byte.parseByte(raw);
            if (value < 1 || value > 31) {
                if (debugMode) System.out.println(" Wrong data in field " + fieldName + " [value = " + raw + "]");
                return null;
            } else {
                return value;
            }
        } catch (NumberFormatException e) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + e.getMessage());
            return null;
        }
    }

    /**
     * Parse string in DayOfWeek.
     * @param raw plain text
     * @param fieldName will be displayed in the error text
     * @return DayOfWeek or null
     */
    private DayOfWeek parseNullableDayOfWeek(String raw, String fieldName) {
        Integer val = parseNullableInt(raw, fieldName);
        if (val == null || val < 1 || val > 7) {
            if (debugMode) System.out.println(" Wrong data in field " + fieldName + " [value = " + raw + "]");
            return null;
        } else {
            return DayOfWeek.of(val);
        }
    }

    /**
     * Get the column number by name
     * @param name the field name
     * @return the number of the field
     */
    private int getFieldNumberByName(String name) {
        return correspondenceTable.get(name);
    }
}

/**
 * Exceptions that occurred when parsing flight data
 */
class FlightParserException extends Exception {
    public FlightParserException(String errorMessage) {
        super(errorMessage);
    }
}