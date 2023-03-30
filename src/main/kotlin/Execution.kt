import client.CityService
import client.CountryService
import service.LocationAggregatorService

fun main() {
    val cityService = CityService()
    val countryService = CountryService()

    val aggregator = LocationAggregatorService(countryService, cityService)

    val startTime = System.currentTimeMillis()
    println("Start time ${System.currentTimeMillis()-startTime}")

    val countryWithCities = aggregator.getCountryWithCities()

    val endTime = System.currentTimeMillis()
    println("End time ${System.currentTimeMillis()-startTime}")

    println("Total time taken: ${endTime - startTime} ms")

    for (countries in countryWithCities) {
        println("ID: ${countries.id}, Name: ${countries.name}")
    }
}