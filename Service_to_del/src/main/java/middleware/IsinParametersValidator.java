package middleware;

// TODO: rest validator
// https://stackoverflow.com/questions/59422883/spring-boot-custom-validation-in-request-params
public class IsinParametersValidator {

    public boolean isValidParameters(String isin, Double bid, Double ask) {
        return isin != null
                && bid != null
                && ask != null
                && isin.length() == 12
                && bid < ask;
    }
}
