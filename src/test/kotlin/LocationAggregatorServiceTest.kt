import client.CityService
import client.CountryService
import dto.City
import dto.Country
import dto.CountryDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.LocationAggregatorService

class LocationAggregatorServiceTest {
    @MockK
    private lateinit var countryService: CountryService
    @MockK
    private lateinit var cityService: CityService
    private lateinit var locationAggregatorService: LocationAggregatorService

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        locationAggregatorService = LocationAggregatorService(countryService, cityService)
    }

    @Test
    fun `test getCountriesWithCities`() = runBlocking {
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

        coEvery { countryService.getCountries() } returns expectedCountries
        coEvery { cityService.getCities() } returns expectedCities

        val actualResult = locationAggregatorService.getCountriesWithCities()

        val expectedResult = listOf(
            CountryDetail(
                "US", "United States",
                listOf(
                    City("1", "New York", "US"),
                    City("2", "Los Angeles", "US"),
                    City("3", "Chicago", "US")
                )
            ),
            CountryDetail(
                "CA", "Canada",
                listOf(
                    City("4", "Toronto", "CA"),
                    City("5", "Montreal", "CA"),
                    City("6", "Vancouver", "CA")
                )
            ),
            CountryDetail(
                "PK", "Pakistan",
                listOf(
                    City("7", "Lahore", "PK"),
                    City("8", "Karachi", "PK"),
                    City("9", "Islamabad", "PK")
                )
            )
        )

        assertEquals(expectedResult, actualResult)
    }
}