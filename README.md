GeolocationUtil
A simple command-line utility that fetches geolocation details (latitude, longitude, and place name) for a given city/state or zip code using the OpenWeather Geocoding API.

Features
	•	✅ Accepts multiple locations as input.
	•	✅ Supports both city/state (Example: "New York, NY") and zip code (Example: "90210").
	•	✅ Fetches latitude, longitude, and place name from OpenWeather API.
	•	✅ Built using Java 22, Maven, and RestAssured (for API testing).
	•	✅ Includes integration tests using JUnit.


Installation

1. Clone the Repository
git clone https://github.com/KarinaMikaelson/GeolocationUtil.git
cd GeolocationUtil

2. Build the Project
Make sure you have Java 17+ and Maven installed.
mvn clean package

Usage

Run 
After building the project
You can pass either a City, State or a ZIP Code as input arguments.

Example with one input:
java -jar target/geoloc-util.jar "New York, NY"

Example with more that one input:
java -jar target/geoloc-util.jar "San Diego, CA" "10001" "90017"


Example Output
Place: New York, Latitude: 40.712727, Longitude: -74.00601

Place: San Diego, Latitude: 32.71742, Longitude: -117.16277
Place: New York, Latitude: 40.7484, Longitude: -73.9967
Place: Los Angeles, Latitude: 34.0559, Longitude: -118.2666


Project Structure

GeolocationUtil/
│── src/
│   ├── main/
│   │   ├── java/com/
│   │   │   ├── geolocation/  
│   │   │   │   ├── GeoLocationFetch.java   # Main class to fetch geolocation data
│   │   │   ├── utils/
│   │   │   │   ├── GeoLocationUtil.java    # Utility class for API configurations
│   ├── test/
│   │   ├── java/com/
│   │   │   ├── geolocation/  
│   │   │   │   ├── GeoLocationFetchTest.java  # Integration tests for geolocation fetch
│── pom.xml                # Maven build file with dependencies and plugins
│── README.md              # Instructions on how to install, configure, and use the tool


Testing
Run integration tests using JUnit:
mvn test


License
This project is open-source under the MIT License.
