# Weather Forecast API with Micronaut

This is a simple weather forecast API built using the Micronaut framework, which allows users to get the weather forecast for a specific day and location. The API makes use of the National Weather Service API to fetch weather data.

## Features

- Retrieve weather forecast for a specific day and location
- Caching of weather data using Micronaut Cache 

## Getting Started

### Prerequisites

- JDK 8 or higher
- Micronaut CLI (optional)

### Installation

1. Clone the repository or download the source code.

```bash
git clone https://github.com/yourusername/weather-forecast-api.git
```

2. Navigate to the project directory.

```bash
cd weather-forecast-api
```

3. Build the project.

```bash
./gradlew build
```

### Running the application

1. Run the application using Gradle.

```bash
./gradlew run
```

Alternatively, you can use Micronaut CLI to run the application.

```bash
mn run
```

2. The API will be available at http://localhost:8080.

## Usage

To get the weather forecast for a specific day and location, make a GET request to the `/weather/forecast` endpoint with the following query parameters:

- `latitude`: Latitude of the location
- `longitude`: Longitude of the location
- `dayForTemperature`: Day for which you want to get the temperature (e.g., "Wednesday Night", "Monday", "Monday Night")

**Example request:**

```bash
GET http://localhost:8080/weather/forecast?latitude=38.8951100&longitude=-77.0363700&dayForTemperature=Wednesday%20Night
```

**Example response:**

```json
{
    "temperature": "Temperature on Wednesday Night: 55.0 F"
}
```

## License

N/A