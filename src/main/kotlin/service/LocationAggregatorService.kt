package service

import client.CityService
import client.CountryService
import dto.City
import dto.Country
import dto.CountryDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class LocationAggregatorService(private val countryService: CountryService, private val cityService: CityService) {

    fun getCountriesWithCities(): Flow<List<CountryDetail>> = flow {
        val countries = countryService.getCountries()
        val cities = cityService.getCities().groupBy { it.countryId }

        val countryDetails = countries.map { country ->
            val countryCities = cities[country.id] ?: emptyList()
            CountryDetail(country.id, country.name, countryCities)
        }
        emit(countryDetails)
    }
}
