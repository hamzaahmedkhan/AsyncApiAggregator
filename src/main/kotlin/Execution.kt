import client.CityService
import client.CountryService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import service.LocationAggregatorService

fun main() {
    runBlocking {

        val aggregator = LocationAggregatorService(CountryService(), CityService())

        val startTime = System.currentTimeMillis()
        val countriesWithCities = aggregator.getCountriesWithCities()
        val endTime = System.currentTimeMillis()

        println("Total time taken to fetch countries and cities: ${endTime - startTime} ms")
        println(countriesWithCities.toString())
    }
}