package service

import client.CityService
import client.CountryService
import dto.City
import dto.Country
import dto.CountryWithCities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class LocationAggregatorService(
    val countryService: CountryService,
    val cityService: CityService
) {
    suspend fun getCountryWithCities(): List<CountryWithCities> = coroutineScope {

        val countriesDeferred = async { countryService.getCountries() }
        val citiesDeferred = async { cityService.getCities() }

        val countries = countriesDeferred.await()
        val cities = citiesDeferred.await()


        val citiesMap = cities.groupBy { it.countryId }
        return@coroutineScope countries.map {
            CountryWithCities(it.id, it.name, citiesMap[it.id])
        }

    }
}