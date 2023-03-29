import service.LocationAggregatorService

fun main() {
    val aggregator = LocationAggregatorService()

    val startTime = System.currentTimeMillis()
    val countries = aggregator.getCountries()
    val endTime = System.currentTimeMillis()

    println("Total time taken: ${endTime - startTime} ms")

    for (country in countries) {
        println("ID: ${country.id}, Name: ${country.name}")
    }
}