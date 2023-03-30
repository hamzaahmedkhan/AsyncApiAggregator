import client.CityService
import client.CountryService
import service.LocationAggregatorService

fun main() {
    val cityService = CityService()
    val countryService = CountryService()

    val aggregator = LocationAggregatorService(countryService, cityService)

    val startTime = System.currentTimeMillis()
    val countries = aggregator.getCountries()
    println("Time after countries ${System.currentTimeMillis()-startTime}")
    val cities = aggregator.getCities()
    val endTime = System.currentTimeMillis()
    println("Time after cities ${System.currentTimeMillis()-startTime}")

    println("Total time taken: ${endTime - startTime} ms")

    for (country in countries) {
        println("ID: ${country.id}, Name: ${country.name}")
    }

    for (city in cities) {
        println("ID: ${city.id}, Name: ${city.name}")
    }
}