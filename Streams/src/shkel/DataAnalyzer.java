package shkel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A set of functions for extracting knowledge from data
 */
public class DataAnalyzer {
    /**
     * The simple Tuples
     * @param <T> any value
     * @param <V> any value
     */
    private static class Pair<T, V> {
        T first;
        V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return first + "," + second;
        }
    }
    private final List<Flight> data;

    /**
     * Check the cancellation status of the flight
     * @param flight the set of data
     * @return is flight was canceled
     */
    private boolean isCanceledFlight(Flight flight) {
        return flight.getCancelled() != null && flight.getCancelled()
                && flight.getTailNum() != null;
    }

    public DataAnalyzer(List<Flight> data) {
        this.data = data;
    }

    /**
     * Which airline has the most canceled flights?
     * @return two-letter air carrier code (UniqueCarrier) and percentage of canceled flights
     */
    public String getPercentOfCancelledFlights() {
        return data.stream()
                .filter(flight ->
                        // canceled flights
                        isCanceledFlight(flight)
                        ||// completed flights
                        (flight.getArrTime() != null && flight.getDepTime() != null)
                )
                .collect(Collectors.groupingBy(
                        Flight::getUniqueCarrier,
                        Collectors.groupingBy(this::isCanceledFlight, Collectors.counting())
                ))
                .entrySet().stream()
                .map(stringMapEntry -> {
                    if (stringMapEntry == null) {
                        return new Pair<>("", 0.);
                    } else {
                        Map<Boolean, Long> value = stringMapEntry.getValue();
                        if (value != null) {
                            Long cancelledCnt = value.get(true);
                            Long notCancelledCnt = value.get(false);
                            long totalCnt = (cancelledCnt == null ? 0 : cancelledCnt) + (notCancelledCnt == null ? 0 : notCancelledCnt);
                            return new Pair<>(
                                    stringMapEntry.getKey() == null ? "" : stringMapEntry.getKey(),
                                    totalCnt == 0 ? 0. : (double) (cancelledCnt == null ? 0 : cancelledCnt) / totalCnt * 100.
                            );
                        } else {
                            return new Pair<>("", 0.);
                        }
                    }
                })
                .max(Comparator.comparingDouble(Pair::getSecond))
                .orElse(new Pair<>("", 0.)).toString();
    }

    /**
     * What is the reason why flights have been canceled most often?
     * @return one-letter code
     */
     public String getMostCommonCancellation() {
         return data.stream()
                 .filter(flight -> flight.getCancellationCode() != null)
                 .collect(Collectors.groupingBy(
                         Flight::getCancellationCode,
                         Collectors.counting()
                 ))
                 .entrySet().stream()
                 .max(Map.Entry.comparingByValue())
                 .map(cancellationCodeLongEntry -> cancellationCodeLongEntry.getKey().toString())
                 .orElse("");
     }

    /**
     * : Which board has flown the most miles?
     * @return full board number
     */
     public String getFurthestTailNum() {
         return data.stream()
                 .filter(flight -> flight.getTailNum() != null && flight.getDistance() != null && !isCanceledFlight(flight))
                 .collect(Collectors.groupingBy(
                         Flight::getTailNum,
                         Collectors.summarizingInt(Flight::getDistance)
                 ))
                 .entrySet().stream()
                 .max(Comparator.comparingLong(o -> o.getValue().getSum()))
                 .map(Map.Entry::getKey)
                 .orElse("");
     }

    /**
     * Count the number of flights at the airport
     * @param stream data about flights
     * @param addedOrigins add arrivals to the calculation
     * @param addedDest add departures to the calculation
     * @return AirportID
     */
     private String getAirportInfo(Stream<Flight> stream, boolean addedOrigins, boolean addedDest) {
         int originAcc = addedOrigins ? 1 : -1;
         int destAcc = addedDest ? 1 : -1;
         return stream
                 // combine two columns in one and calculate the count
                 .collect(Collector.of(
                         HashMap<Integer, Integer>::new,
                         (integerIntegerHashMap, flight) -> {
                             integerIntegerHashMap.put(
                                     flight.getOriginAirportID(),
                                     integerIntegerHashMap.getOrDefault(flight.getOriginAirportID(), 0) + originAcc
                             );
                             integerIntegerHashMap.put(
                                     flight.getDestAirportID(),
                                     integerIntegerHashMap.getOrDefault(flight.getDestAirportID(), 0) + destAcc
                             );
                         },
                         (map1, map2) -> {
                             map1.forEach((key1, value1) -> map2.merge(key1, value1, (key, value) -> value1 + value));
                             return map1;
                         }
                 ))
                 .entrySet().stream()
                 .max(Comparator.comparingInt(Map.Entry::getValue))
                 .map(integerIntegerEntry -> integerIntegerEntry.getKey().toString())
                 .orElse("");
     }

    /**
     * Which airport was the busiest in terms of arrivals and departures?
     * @return AirportID
     */
     public String getBusiestAirport() {
         return getAirportInfo(data.stream().filter(flight -> !isCanceledFlight(flight)), true, true);
     }

    /**
     * : Which airport was the most active source of flights?
     * @return AirportID
     */
    public String getMaxSource() {
        return getAirportInfo(data.stream().filter(flight -> !isCanceledFlight(flight)), true, false);
    }

    /**
     * Which airport was the most active sink of flights?
     * @return AirportID
     */
    public String getMaxSink() {
        return getAirportInfo(data.stream().filter(flight -> !isCanceledFlight(flight)), false, true);
    }

    /**
     * : How many UniqueCarrier ‘AA’ flights were delayed by 60 minutes or more?
     * @return number of flights
     */
    public String getDelays() {
        final String uniqueCarrier = "AA";
        return String.valueOf(data.stream()
                .filter(flight ->
                        !isCanceledFlight(flight)
                        && (flight.getDiverted() == null || !flight.getDiverted())
                        && flight.getUniqueCarrier().equals(uniqueCarrier)
                        && (
                            Integer.max(
                                (flight.getDepDelay() == null ? 0 : flight.getDepDelay()),
                                (flight.getArrDelay() == null ? 0 : flight.getArrDelay())
                            ) >= 60
                        )
                )
                .count());
    }

    /**
     * Which of catch-up delays was the biggest?
     * @return DayOfMonth, DepDelay and TailNum.
     */
    public String getMadeupDelay() {
        return data.stream()
                .filter(flight ->
                        !isCanceledFlight(flight)
                        && (flight.getDiverted() == null || !flight.getDiverted())
                        && flight.getDepDelay() != null && flight.getDepDelay() > 0
                        && (flight.getArrDelay() == null || flight.getArrDelay() <= 0)
                        && flight.getTailNum() != null
                )
                .max(Comparator.comparingInt(Flight::getDepDelay))
                .map(flight -> flight.getDayOfMonth() + "," + flight.getDepDelay() + "," + flight.getTailNum())
                .orElse("");
    }

    /**
     * Which airline has the most planes?
     * @return the career code
     */
    public String getAirline() {
        return data.stream()
                .filter(flight -> flight.getTailNum() != null && flight.getUniqueCarrier() != null)
                .map(flight -> new Pair<>(flight.getUniqueCarrier(), flight.getTailNum()))
                .distinct()
                .collect(Collectors.groupingBy(
                        pair -> pair.first,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

}
