package shkel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        byte headerLength = 1;
        List<Flight> pureData = new ArrayList<>();
        Path path = Paths.get("flights.csv");
        FlightParser parser = new FlightParser();
        FormattedOutput writer = new FormattedOutput();

        try (Stream<String> lineStream = Files.lines(path)) {
            pureData = lineStream
                    .skip(headerLength)
                    .map(s -> {
                        try {
                            return parser.parse(s);
                        } catch (FlightParserException e) {
                            System.out.println("The row is filtered. " + e.getMessage());
                            System.out.println(s);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        DataAnalyzer analyzer = new DataAnalyzer(pureData);
        writer.addAnswer(1, analyzer.getPercentOfCancelledFlights());
        writer.addAnswer(2, analyzer.getMostCommonCancellation());
        writer.addAnswer(3, analyzer.getFurthestTailNum());
        writer.addAnswer(4, analyzer.getBusiestAirport());
        writer.addAnswer(5, analyzer.getMaxSource());
        writer.addAnswer(6, analyzer.getMaxSink());
        writer.addAnswer(7, analyzer.getDelays());
        writer.addAnswer(8, analyzer.getMadeupDelay());
        writer.addAnswer(9, analyzer.getAirline());
        writer.writeAnswers();

        System.out.println("The carrier with the largest aircraft fleet is " + analyzer.getAirline());

        System.out.println(analyzer.getPercentOfCancelledFlights());
        System.out.println(analyzer.getDelays());
        System.out.println(analyzer.getMadeupDelay());
    }

}
