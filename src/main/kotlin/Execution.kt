import client.CityService
import client.CountryService
import service.LocationAggregatorService

suspend fun main() {
    val aggregator = LocationAggregatorService(CountryService(), CityService())

    val startTime = System.currentTimeMillis()
    val data = aggregator.getCountriesWithCities().collect {
        it
    }

    val endTime = System.currentTimeMillis()

    println("Total time taken to fetch countries and cities: ${endTime - startTime} ms")
    println(data.toString())

}