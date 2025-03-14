package geolocation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import static utils.GeoLocationUtil.*;

public class GeoLocationFetch {

    public static String fetchLocationByName(String city, String state) {
        if (EXCLUDED_STATES.contains(state)) {
            throw new IllegalArgumentException("State " + state + " is not allowed.");
        }

        Response response = RestAssured.given()
                .queryParam("q", city + "," + state + ",US")
                .queryParam("appid", API_KEY)
                .queryParam("limit", 1)
                .get(BASE_URL_NAME);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error fetching data: " + response.getStatusCode());
        }

        JsonPath jsonPath = response.jsonPath();
        if (jsonPath.getList("$").isEmpty()) {
            throw new RuntimeException("No results found for " + city + ", " + state);
        }

        return String.format("Place: %s, Latitude: %s, Longitude: %s",
                jsonPath.getString("[0].name"),
                jsonPath.getString("[0].lat"),
                jsonPath.getString("[0].lon"));
    }

    public static String fetchLocationByZip(String zipCode) {
        Response response = RestAssured.given()
                .queryParam("zip", zipCode + ",US")
                .queryParam("appid", API_KEY)
                .get(BASE_URL_ZIP);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error fetching data: " + response.getStatusCode());
        }

        JsonPath jsonPath = response.jsonPath();
        return String.format("Place: %s, Latitude: %s, Longitude: %s",
                jsonPath.getString("name"),
                jsonPath.getString("lat"),
                jsonPath.getString("lon"));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar geoloc-util.jar \"City, State\" \"ZipCode\"");
            return;
        }

        for (String loc : args) {
            try {
                if (loc.matches("\\d+")) { // ZIP Code
                    System.out.println(fetchLocationByZip(loc));
                } else if (loc.contains(",")) { // City, State
                    String[] parts = loc.split(",");
                    System.out.println(fetchLocationByName(parts[0].trim(), parts[1].trim()));
                } else {
                    System.err.println("Invalid input format: " + loc);
                }
            } catch (Exception e) {
                System.err.println("Error processing " + loc + ": " + e.getMessage());
            }
        }
    }
}
