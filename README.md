# QuickPokedex
QuickPokedex is a Pokedex application written for Android. 

# What is a Pokedex?
A Pokedex is an encyclopedia containing information about Pokemon.

# How does it work?
QuickPokedex makes API calls to the RESTFul API provided by http://pokeapi.co/ and stores the information in a SQLite database for caching purposes so that no API calls are needed unless if there is new information updated or added on PokeAPI. This also means that QuickPokedex is able to work offline due to the information being stored with the application.
