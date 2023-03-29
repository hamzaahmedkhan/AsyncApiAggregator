package service

import client.CountryService
import dto.Country

class LocationAggregatorService {
    fun getCountries(): List<Country> {
        return CountryService.getCountries()
    }
}