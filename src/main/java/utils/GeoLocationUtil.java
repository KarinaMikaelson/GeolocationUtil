package utils;

import java.util.Arrays;
import java.util.List;

public class GeoLocationUtil {
    public static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
    public static final String BASE_URL_NAME = "http://api.openweathermap.org/geo/1.0/direct";
    public static final String BASE_URL_ZIP = "http://api.openweathermap.org/geo/1.0/zip";
    public static final List<String> EXCLUDED_STATES = Arrays.asList("HI", "AK");
}
