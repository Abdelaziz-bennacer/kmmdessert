package fr.abdel.shared.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import fr.abdel.shared.ApolloProvider
import fr.abdel.shared.cache.Database
import kotlinx.coroutines.ExperimentalCoroutinesApi

open class BaseRepository(apolloProvider: ApolloProvider) {
    @ApolloExperimental
    @ExperimentalCoroutinesApi
    val apolloClient: ApolloClient = apolloProvider.apolloClient
    val database: Database = apolloProvider.database
}