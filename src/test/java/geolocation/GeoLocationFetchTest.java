package geolocation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeoLocationFetchTest {

    @Test
    void testFetchLocationByName_ValidCityState() {
        String result = GeoLocationFetch.fetchLocationByName("New York", "NY");
        assertNotNull(result, "Response should not be null");
        assertTrue(result.contains("Place:") && result.contains("Latitude:") && result.contains("Longitude:"),
                "Response should contain formatted location details");
    }

    @Test
    void testFetchLocationByZip_ValidZip() {
        String result = GeoLocationFetch.fetchLocationByZip("10001");
        assertNotNull(result, "Response should not be null");
        assertTrue(result.contains("Place:") && result.contains("Latitude:") && result.contains("Longitude:"),
                "Response should contain formatted location details");
    }

    @Test
    void testFetchLocationByName_ExcludedState() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                GeoLocationFetch.fetchLocationByName("Honolulu", "HI")
        );
        assertEquals("State HI is not allowed.", exception.getMessage());
    }

    @Test
    void testFetchLocationByName_InvalidCityState() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                GeoLocationFetch.fetchLocationByName("FakeCity", "ZZ")
        );
        assertTrue(exception.getMessage().contains("No results found"));
    }

    @Test
    void testFetchLocationByZip_InvalidZip() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                GeoLocationFetch.fetchLocationByZip("00000")
        );
        assertTrue(exception.getMessage().contains("Error fetching data"));
    }
}