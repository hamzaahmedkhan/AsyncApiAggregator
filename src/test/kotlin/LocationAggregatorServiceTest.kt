import client.CityService
import client.CountryService
import dto.City
import dto.Country
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.LocationAggregatorService

class LocationAggregatorServiceTest {
    private lateinit var countryService: CountryService
    private lateinit var cityService: CityService

    @BeforeEach
    fun setup() {
        countryService = mockk()
        cityService = mockk()
    }

    @Test
    fun testGetCountriesWithCities() = runBlocking<Unit> {
        val expectedCountries = listOf(
            Country("US", "United States"),
            Country("CA", "Canada"),
            Country("PK", "Pakistan")
        )
        val expectedCities = listOf(
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
        val aggregator = LocationAggregatorService(countryService, cityService)

        coEvery { countryService.getCountries() } returns expectedCountries
        coEvery { cityService.getCities() } returns expectedCities

        val countriesWithCities = aggregator.getCountriesWithCities()

        assertEquals(expectedCountries.size, countriesWithCities.size)

        for (i in expectedCountries.indices) {
            assertEquals(expectedCountries[i].id, countriesWithCities[i].id)
            assertEquals(expectedCountries[i].name, countriesWithCities[i].name)
            assertEquals(expectedCities.filter { it.countryId == expectedCountries[i].id }, countriesWithCities[i].cities)
        }
    }
}