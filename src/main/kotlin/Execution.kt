import service.LocationAggregatorService

fun main() {
    val aggregator = LocationAggregatorService()

    val startTime = System.currentTimeMillis()
    fetchCountries(aggregator)
    fetchCities(aggregator)
    val endTime = System.currentTimeMillis()

    println("Total time taken to fetch countries and cities: ${endTime - startTime} ms")

}

private fun fetchCountries(aggregator: LocationAggregatorService) {
    val startTime = System.currentTimeMillis()
    val countries = aggregator.getCountries()
    val endTime = System.currentTimeMillis()

    println("Total time taken to fetch countries: ${endTime - startTime} ms")

    for (country in countries) {
        println("ID: ${country.id}, Name: ${country.name}")
    }
}

private fun fetchCities(aggregator: LocationAggregatorService) {
    val startTime = System.currentTimeMillis()
    val cities = aggregator.getCities()
    val endTime = System.currentTimeMillis()

    println("Total time taken to fetch cities: ${endTime - startTime} ms")

    for (city in cities) {
        println("ID: ${city.id}, Name: ${city.name}")
    }
}