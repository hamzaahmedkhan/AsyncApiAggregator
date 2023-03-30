import client.CityService
import client.CountryService
import dto.City
import dto.Country
import dto.CountryWithCities
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
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

    fun mockCountries() {
        val expectedCountries = listOf(
            Country("PK", "Pakistan")
        )

        every { countryService.getCountries() } returns expectedCountries
    }

    fun mockCities() {
        val expectedCities = listOf(
            City("7", "Lahore", "PK"),
            City("8", "Karachi", "PK"),
            City("9", "Islamabad", "PK")
        )

        every { cityService.getCities() } returns expectedCities
    }

    @Test
    fun `get countryWithCities`() {

        val expectedCountryWithCities = listOf(
            CountryWithCities(
                "PK", "Pakistan", listOf(
                    City("7", "Lahore", "PK"),
                    City("8", "Karachi", "PK"),
                    City("9", "Islamabad", "PK")
                )
            )
        )
        mockCountries()
        mockCities()

        val aggregator = LocationAggregatorService(countryService, cityService)
        val countryWithCities = aggregator.getCountryWithCities()

        assertEquals(expectedCountryWithCities, countryWithCities)
    }
}