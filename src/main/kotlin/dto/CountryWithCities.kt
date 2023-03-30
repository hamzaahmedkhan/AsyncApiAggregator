package dto

data class CountryWithCities(
    val id: String, val name: String, val cities: List<City>?
)