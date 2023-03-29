import client.CityService
import client.CountryService
import dto.City
import dto.Country
import io.mockk.every
import io.mockk.mockkObject
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

    @Test
    fun testGetCountries() {
        val expectedCountries = listOf(
            Country("US", "United States"),
            Country("CA", "Canada"),
            Country("PK", "Pakistan")
        )

        every { CountryService.getCountries() } returns expectedCountries

        val aggregator = LocationAggregatorService()
        val countries = aggregator.getCountries()

        assertEquals(expectedCountries.size, countries.size)

        for (i in expectedCountries.indices) {
            assertEquals(expectedCountries[i].id, countries[i].id)
            assertEquals(expectedCountries[i].name, countries[i].name)
        }
    }

    @Test
    fun testGetCities() {
        val expectedCities = listOf(
            City("NYC", "New York City", "US"),
            City("LAX", "Los Angeles", "US"),
            City("TOR", "Toronto", "CA"),
            City("MTL", "Montreal", "CA"),
            City("LHR", "London", "GB"),
            City("MAN", "Manchester", "GB"),
            City("LHE", "Lahore", "PK"),
            City("KHI", "Karachi", "PK"),
            City("ISB", "Islamabad", "PK")
        )

        every { CityService.getCities() } returns expectedCities

        val aggregator = LocationAggregatorService()
        val cities = aggregator.getCities()

        assertEquals(expectedCities.size, cities.size)

        for (i in expectedCities.indices) {
            assertEquals(expectedCities[i].id, cities[i].id)
            assertEquals(expectedCities[i].name, cities[i].name)
            assertEquals(expectedCities[i].countryId, cities[i].countryId)
        }
    }
}