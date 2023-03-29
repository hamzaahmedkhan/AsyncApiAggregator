import client.CountryService
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
}