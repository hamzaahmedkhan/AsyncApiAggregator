package service

import client.CityService
import client.CountryService
import dto.CountryDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LocationAggregatorService(private val countryService: CountryService, private val cityService: CityService) {

    fun getCountriesWithCities(): List<CountryDetail> {
        val countries = runBlocking { countryService.getCountries() }
        val cities = runBlocking { cityService.getCities().groupBy { it.countryId } }
        return countries.map { country ->
            val countryCities = cities[country.id] ?: emptyList()
            CountryDetail(country.id, country.name, countryCities)
        }
    }
}