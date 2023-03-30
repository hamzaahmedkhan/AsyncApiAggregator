package service

import client.CityService
import client.CountryService
import dto.CountryDetail
import kotlinx.coroutines.*

class LocationAggregatorService(private val countryService: CountryService, private val cityService: CityService) {

    suspend fun getCountriesWithCities(): List<CountryDetail> = coroutineScope{
        val countriesDeferred = async { countryService.getCountries() }
        val citiesDeferred = async { cityService.getCities() }

        val countries = countriesDeferred.await()
        val cities = citiesDeferred.await().groupBy { it.countryId }

        return@coroutineScope countries.map { country ->
            val countryCities = cities[country.id] ?: emptyList()
            CountryDetail(country.id, country.name, countryCities)
        }
    }


    suspend fun insertCountriesAndCities(): List<CountryDetail> = coroutineScope{
        CoroutineScope(Dispatchers.IO).launch {
            countryService.insert()
        }

        CoroutineScope(Dispatchers.IO).launch {
            cityService.insert()
        }
        return@coroutineScope emptyList()
    }
}