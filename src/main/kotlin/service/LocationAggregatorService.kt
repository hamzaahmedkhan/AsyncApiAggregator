package service

import client.CityService
import client.CountryService
import dto.CountryDetail

class LocationAggregatorService {
    fun getCountriesWithCities(): List<CountryDetail> {
        val countries = CountryService.getCountries()
        val cities = CityService.getCities().groupBy { it.countryId }

        return countries.map { country ->
            val countryCities = cities[country.id]!!
            CountryDetail(country.id, country.name, countryCities)
        }
    }
}