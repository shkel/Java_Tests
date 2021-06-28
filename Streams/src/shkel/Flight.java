package shkel;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Flight informationю It may be incomplete.
 */
class Flight {
    enum CancellationCode {
        A, // Carrier's decision.
        B, // Meteorological conditions
        C, // Order of the Air Transport Service
        D, // Security order
    }

    // required fields
    private Date flightDate;
    private String uniqueCarrier; //уникальный код авиаперевозчика (AA, BY)
    private int originAirportID; // идентификатор аэропорта происхождения (назначается министерством)
    private int destAirportID; //идентификатор аэропорта назначения (назначается министерством)

    // optionals fields
    private String tailNum; // unique tail number (N786AA)
    private Byte dayOfMonth; // 1 -31
    private DayOfWeek dayOfWeek; // 1-7
    private String origin; // departure airport (BOS)
    private String originStateName; // place of departure
    private String dest; // arrival airport
    private String destStateName; // place of arrival
    private LocalTime depTime; // departure time (local, hhmm) (859)
    private Integer depDelay; // the difference in minutes between the planned and actual departure time. For departures ahead of schedule, this indicator has a negative sign.
    private LocalTime wheelsOff; // (local time, hhmm)
    private LocalTime wheelsOn; // (local time, hhmm)
    private LocalTime arrTime; // arrival time (hhmm)
    private Integer arrDelay; // difference in minutes between planned and actual arrival time. For arrivals ahead of schedule, this indicator has a negative sign.
    private Boolean cancelled;
    private CancellationCode cancellationCode;
    private Boolean diverted;
    private Integer airTime; // Flight time (minutes)
    private Integer distance; // Distance between airports (km)

    public static class Builder {
        // required fields
        private Date flightDate;
        private String uniqueCarrier;
        private int originAirportID;
        private int destAirportID;

        // optionals fields
        private String tailNum;
        private Byte dayOfMonth = null;
        private DayOfWeek dayOfWeek;
        private String origin;
        private String originStateName;
        private String dest;
        private String destStateName;
        private LocalTime depTime;
        private Integer depDelay;
        private LocalTime wheelsOff;
        private LocalTime wheelsOn;
        private LocalTime arrTime;
        private Integer arrDelay;
        private Boolean cancelled;
        private CancellationCode cancellationCode;
        private Boolean diverted;
        private Integer airTime;
        private Integer distance;

        public Builder(Date flightDate, String uniqueCarrier, int originAirportID, int destAirportID) {
            this.flightDate = flightDate;
            this.uniqueCarrier = uniqueCarrier;
            this.originAirportID = originAirportID;
            this.destAirportID = destAirportID;
        }

        public Builder tailNum(String tailNum) {
            this.tailNum = tailNum;
            return this;
        }

        public Builder dayOfMonth(Byte dayOfMonth) {
            this.dayOfMonth = dayOfMonth;
            return this;
        }

        public Builder dayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder originStateName(String originStateName) {
            this.originStateName = originStateName;
            return this;
        }

        public Builder dest(String dest) {
            this.dest = dest;
            return this;
        }

        public Builder destStateName(String destStateName) {
            this.destStateName = destStateName;
            return this;
        }

        public Builder depTime(LocalTime depTime) {
            this.depTime = depTime;
            return this;
        }

        public Builder depDelay(Integer depDelay) {
            this.depDelay = depDelay;
            return this;
        }

        public Builder wheelsOff(LocalTime wheelsOff) {
            this.wheelsOff = wheelsOff;
            return this;
        }

        public Builder wheelsOn(LocalTime wheelsOn) {
            this.wheelsOn = wheelsOn;
            return this;
        }

        public Builder arrTime(LocalTime arrTime) {
            this.arrTime = arrTime;
            return this;
        }

        public Builder arrDelay(Integer arrDelay) {
            this.arrDelay = arrDelay;
            return this;
        }

        public Builder cancelled(Boolean cancelled) {
            this.cancelled = cancelled;
            return this;
        }

        public Builder cancellationCode(CancellationCode cancellationCode) {
            this.cancellationCode = cancellationCode;
            return this;
        }

        public Builder diverted(Boolean diverted) {
            this.diverted = diverted;
            return this;
        }

        public Builder airTime(Integer airTime) {
            this.airTime = airTime;
            return this;
        }

        public Builder distance(Integer distance) {
            this.distance = distance;
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }
    }

    private Flight(Builder builder) {
        flightDate = builder.flightDate;
        uniqueCarrier = builder.uniqueCarrier;
        originAirportID = builder.originAirportID;
        destAirportID = builder.destAirportID;
        tailNum = builder.tailNum;
        dayOfMonth = builder.dayOfMonth;
        dayOfWeek = builder.dayOfWeek;
        origin = builder.origin;
        originStateName = builder.originStateName;
        dest = builder.dest;
        destStateName = builder.destStateName;
        depTime = builder.depTime;
        depDelay = builder.depDelay;
        wheelsOff = builder.wheelsOff;
        wheelsOn = builder.wheelsOn;
        arrTime = builder.arrTime;
        arrDelay = builder.arrDelay;
        cancelled = builder.cancelled;
        cancellationCode = builder.cancellationCode;
        diverted = builder.diverted;
        airTime = builder.airTime;
        distance = builder.distance;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public String getUniqueCarrier() {
        return uniqueCarrier;
    }

    public int getOriginAirportID() {
        return originAirportID;
    }

    public int getDestAirportID() {
        return destAirportID;
    }

    public String getTailNum() {
        return tailNum;
    }

    public Byte getDayOfMonth() {
        return dayOfMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOriginStateName() {
        return originStateName;
    }

    public String getDest() {
        return dest;
    }

    public String getDestStateName() {
        return destStateName;
    }

    public LocalTime getDepTime() {
        return depTime;
    }

    public Integer getDepDelay() {
        return depDelay;
    }

    public LocalTime getWheelsOff() {
        return wheelsOff;
    }

    public LocalTime getWheelsOn() {
        return wheelsOn;
    }

    public LocalTime getArrTime() {
        return arrTime;
    }

    public Integer getArrDelay() {
        return arrDelay;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public CancellationCode getCancellationCode() {
        return cancellationCode;
    }

    public Boolean getDiverted() {
        return diverted;
    }

    public Integer getAirTime() {
        return airTime;
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightDate=" + new SimpleDateFormat("dd.MM.yyyy").format(flightDate) +
                ", uniqueCarrier='" + uniqueCarrier + '\'' +
                ", tailNum='" + tailNum + '\'' +
                ", originAirportID=" + originAirportID +
                ", destAirportID=" + destAirportID +
                ", dayOfMonth=" + dayOfMonth +
                ", dayOfWeek=" + dayOfWeek +
                ", origin='" + origin + '\'' +
                ", originStateName='" + originStateName + '\'' +
                ", dest='" + dest + '\'' +
                ", destStateName='" + destStateName + '\'' +
                ", depTime=" + depTime +
                ", depDelay=" + depDelay +
                ", wheelsOff=" + wheelsOff +
                ", wheelsOn=" + wheelsOn +
                ", arrTime=" + arrTime +
                ", arrDelay=" + arrDelay +
                ", cancelled=" + cancelled +
                ", cancellationCode=" + cancellationCode +
                ", diverted=" + diverted +
                ", airTime=" + airTime +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return originAirportID == flight.originAirportID &&
                destAirportID == flight.destAirportID &&
                flightDate.equals(flight.flightDate) &&
                uniqueCarrier.equals(flight.uniqueCarrier) &&
                Objects.equals(tailNum, flight.tailNum) &&
                Objects.equals(dayOfMonth, flight.dayOfMonth) &&
                dayOfWeek == flight.dayOfWeek &&
                Objects.equals(origin, flight.origin) &&
                Objects.equals(originStateName, flight.originStateName) &&
                Objects.equals(dest, flight.dest) &&
                Objects.equals(destStateName, flight.destStateName) &&
                Objects.equals(depTime, flight.depTime) &&
                Objects.equals(depDelay, flight.depDelay) &&
                Objects.equals(wheelsOff, flight.wheelsOff) &&
                Objects.equals(wheelsOn, flight.wheelsOn) &&
                Objects.equals(arrTime, flight.arrTime) &&
                Objects.equals(arrDelay, flight.arrDelay) &&
                Objects.equals(cancelled, flight.cancelled) &&
                cancellationCode == flight.cancellationCode &&
                Objects.equals(diverted, flight.diverted) &&
                Objects.equals(airTime, flight.airTime) &&
                Objects.equals(distance, flight.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightDate, uniqueCarrier, originAirportID, destAirportID, tailNum, dayOfMonth, dayOfWeek, origin, originStateName, dest, destStateName, depTime, depDelay, wheelsOff, wheelsOn, arrTime, arrDelay, cancelled, cancellationCode, diverted, airTime, distance);
    }
}