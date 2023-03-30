package client

import dto.Country

class CountryService {
    fun getCountries(): List<Country> {
        // Simulate a 2.5 second delay for a third-party API call
        Thread.sleep(2500)

        return listOf(
            Country("US", "United States"),
            Country("CA", "Canada"),
            Country("PK", "Pakistan")
        )
    }
}