import client.CityService
import client.CountryService
import dto.City
import dto.Country
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.LocationAggregatorService

class LocationAggregatorServiceTest {
    @MockK
    private lateinit var countryService: CountryService
    @MockK
    private lateinit var cityService: CityService

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetCountries() {
        val expectedCountries = listOf(
            Country("US", "United States"),
            Country("CA", "Canada"),
            Country("PK", "Pakistan")
        )

        every { countryService.getCountries() } returns expectedCountries

        val aggregator = LocationAggregatorService(countryService,cityService)
        val countries = aggregator.getCountries()

        assertEquals(expectedCountries.size, countries.size)

        for (i in expectedCountries.indices) {
            assertEquals(expectedCountries[i].id, countries[i].id)
            assertEquals(expectedCountries[i].name, countries[i].name)
        }
    }

    @Test
    fun `get cities`(){
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

        every { cityService.getCities() } returns expectedCities

        val aggregator = LocationAggregatorService(countryService,cityService)
        val cities = aggregator.getCities()

        assertEquals(expectedCities.size, cities.size)
    }
}