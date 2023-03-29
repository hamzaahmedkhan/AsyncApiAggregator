package service

import client.CityService
import client.CountryService
import dto.CountryDetail

class LocationAggregatorService(private val countryService: CountryService, private val cityService: CityService) {

    suspend fun getCountriesWithCities(): List<CountryDetail> {

        val countries = countryService.getCountries()
        val cities = cityService.getCities().groupBy { it.countryId }
        return countries.map { country ->
            val countryCities = cities[country.id] ?: emptyList()
            CountryDetail(country.id, country.name, countryCities)
        }
    }
}