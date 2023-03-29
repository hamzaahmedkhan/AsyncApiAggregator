import service.LocationAggregatorService

fun main() {
    val aggregator = LocationAggregatorService()

    val startTime = System.currentTimeMillis()
    val countriesWithCities = aggregator.getCountriesWithCities()
    val endTime = System.currentTimeMillis()

    println("Total time taken to fetch countries and cities: ${endTime - startTime} ms")
    println(countriesWithCities.toString())
}