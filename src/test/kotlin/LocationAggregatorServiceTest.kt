import client.CityService
import client.CountryService
import dto.City
import dto.Country
import dto.CountryDetail
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.LocationAggregatorService

class LocationAggregatorServiceTest {

    @BeforeEach
    fun setup() {
        mockkObject(CountryService)
        mockkObject(CityService)
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun testGetCountriesWithCities() {
        val expectedCountriesWithCities = listOf(
            CountryDetail("US", "United States", listOf(
                City("1", "New York", "US"),
                City("2", "Los Angeles", "US"),
                City("3", "Chicago", "US")
            )),
            CountryDetail("CA", "Canada", listOf(
                City("4", "Toronto", "CA"),
                City("5", "Montreal", "CA"),
                City("6", "Vancouver", "CA")
            )),
            CountryDetail("PK", "Pakistan", listOf(
                City("7", "Lahore", "PK"),
                City("8", "Karachi", "PK"),
                City("9", "Islamabad", "PK")
            ))
        )

        every { CountryService.getCountries() } returns listOf(
            Country("US", "United States"),
            Country("CA", "Canada"),
            Country("PK", "Pakistan")
        )

        every { CityService.getCities() } returns listOf(
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

        val aggregator = LocationAggregatorService()
        val countriesWithCities = aggregator.getCountriesWithCities()

        assertEquals(expectedCountriesWithCities.size, countriesWithCities.size)

        for (i in expectedCountriesWithCities.indices) {
            val expectedCountry = expectedCountriesWithCities[i]
            val country = countriesWithCities[i]

            assertEquals(expectedCountry.id, country.id)
            assertEquals(expectedCountry.name, country.name)

            val expectedCities = expectedCountry.cities
            val cities = country.cities

            assertEquals(expectedCities.size, cities.size)

            for (j in expectedCities.indices) {
                val expectedCity = expectedCities[j]
                val city = cities[j]

                assertEquals(expectedCity.id, city.id)
                assertEquals(expectedCity.name, city.name)
                assertEquals(expectedCity.countryId, city.countryId)
            }
        }
    }
}