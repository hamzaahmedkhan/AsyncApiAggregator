package service

import client.CityService
import client.CountryService
import dto.City
import dto.Country
import dto.CountryWithCities
import kotlinx.coroutines.runBlocking

class LocationAggregatorService(
    val countryService: CountryService,
    val cityService: CityService
) {
    suspend fun getCountryWithCities(): List<CountryWithCities> {

        val countries = countryService.getCountries()
        val cities =  cityService.getCities()

        val citiesMap = cities.groupBy { it.countryId }
        val countryWithCitiesList = mutableListOf<CountryWithCities>()
        countries.forEach { countryWithCitiesList.add(CountryWithCities(it.id, it.name, citiesMap[it.id])) }
        return countryWithCitiesList
    }
}