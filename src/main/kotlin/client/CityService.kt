package client

import dto.City
import kotlinx.coroutines.delay

class CityService {
    private val cities = listOf(
        City("1", "New York", "US"),
        City("2", "Los Angeles", "US"),
        City("3", "Chicago", "US"),
        City("4", "Toronto", "CA"),
        City("5", "Montreal", "CA"),
        City("6", "Vancouver", "CA"),
        City("7", "Lahore", "PK"),
        City("8", "Karachi", "PK"),
        City("9", "Islamabad", "PK")
    )

    suspend fun getCities(): List<City> {
        // Simulate a 4 second delay in the API call
        delay(4000)
        return cities
    }
}