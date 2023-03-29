package service

import client.CityService
import client.CountryService
import dto.City
import dto.Country

class LocationAggregatorService {
    fun getCountries(): List<Country> {
        return CountryService.getCountries()
    }

    fun getCities(): List<City> {
        return CityService.getCities()
    }
}