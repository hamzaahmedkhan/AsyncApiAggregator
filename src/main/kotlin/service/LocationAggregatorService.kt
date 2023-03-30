package service

import client.CityService
import client.CountryService
import dto.City
import dto.Country

class LocationAggregatorService(
    val countryService: CountryService,
    val cityService: CityService
) {
    fun getCountries(): List<Country> {
        return countryService.getCountries()
    }

    fun getCities(): List<City>{
        return cityService.getCities()
    }
}