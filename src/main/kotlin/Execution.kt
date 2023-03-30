import client.CityService
import client.CountryService
import service.LocationAggregatorService

suspend fun main() {
    val aggregator = LocationAggregatorService(CountryService(), CityService())

    getData(aggregator)
    insertData(aggregator)
}

private suspend fun getData(aggregator: LocationAggregatorService) {
    val startTime = System.currentTimeMillis()
    val countriesWithCities = aggregator.getCountriesWithCities()
    val endTime = System.currentTimeMillis()

    println("Total time taken to fetch countries and cities: ${endTime - startTime} ms")
    println(countriesWithCities.toString())
}

private suspend fun insertData(aggregator: LocationAggregatorService) {
    val startTime = System.currentTimeMillis()
    aggregator.insertCountriesAndCities()
    val endTime = System.currentTimeMillis()

    println("Total time taken to insert countries and cities: ${endTime - startTime} ms")
}