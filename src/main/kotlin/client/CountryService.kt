package client

import dto.Country
import kotlinx.coroutines.delay

class CountryService {
    suspend fun getCountries(): List<Country> {
        // Simulate a 2.5 second delay for a third-party API call
        delay(1500)

        return listOf(
            Country("US", "United States"),
            Country("CA", "Canada"),
            Country("PK", "Pakistan")
        )
    }
}