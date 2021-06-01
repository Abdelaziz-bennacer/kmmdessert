package fr.abdel.shared.repository

import com.apollographql.apollo.api.ApolloExperimental
import fr.abdel.kmmdessert.*
import fr.abdel.kmmdessert.shared.cache.Dessert
import fr.abdel.kmmdessert.type.DessertInput
import fr.abdel.shared.ApolloProvider
import fr.abdel.shared.cache.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single

class DessertRepository(apolloProvider: ApolloProvider): BaseRepository(apolloProvider) {

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getDesserts(page: Int, size: Int): Desserts? {
        val response = apolloClient
            .query(GetDessertsQuery(page, size))
            .execute()
            .single()
        return response.data?.desserts?.toDesserts()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getDessert(dessertId: String): DessertDetail? {
        val response = apolloClient
            .query(GetDessertQuery(dessertId))
            .execute()
            .single()
        return response.data?.dessert?.toDessertDetail()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun newDessert(dessertInput: DessertInput): Dessert? {
        val response = apolloClient
            .mutate(NewDessertMutation(dessertInput))
            .execute()
            .single()
        return response.data?.createDessert?.toDessert()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun updateDessert(dessertId: String, dessertInput: DessertInput): Dessert? {
        val response = apolloClient
            .mutate(UpdateDessertMutation(dessertId, dessertInput))
            .execute()
            .single()
        return response.data?.updateDessert?.toDessert()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun deleteDessert(dessertId: String): Boolean? {
        val response = apolloClient
            .mutate(DeleteDessertMutation(dessertId))
            .execute()
            .single()
        return response.data?.deleteDessert
    }

    fun saveFavorite(dessert: Dessert) {
        database.saveDessert(dessert)
    }

    fun removeFavorite(dessertId: String) {
        database.deleteDessert(dessertId)
    }

    fun updateFavorite(dessert: Dessert) {
        database.updateDessert(dessert)
    }

    fun getFavoriteDessert(dessertId: String): Dessert? {
        return database.getDessertById(dessertId)
    }

    fun getFavoriteDesserts(): List<Dessert> {
        return database.getDesserts()
    }

}