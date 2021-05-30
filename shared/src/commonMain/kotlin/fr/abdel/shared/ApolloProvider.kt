package fr.abdel.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.interceptor.BearerTokenInterceptor
import com.apollographql.apollo.interceptor.TokenProvider
import fr.abdel.shared.cache.Database
import fr.abdel.shared.cache.DatabaseDriverFactory
import fr.abdel.shared.logger.LoggingInterceptor
import fr.abdel.shared.logger.MyLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ApolloProvider(databaseDriverFactory: DatabaseDriverFactory, myLogger: MyLogger): TokenProvider {

    internal val database = Database(databaseDriverFactory)

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    internal val apolloClient: ApolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl =  "https://abdel-graphql-server.herokuapp.com/graphql",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json"
            ),
        ),
    interceptors = listOf(BearerTokenInterceptor(this), LoggingInterceptor(myLogger))
    )

    override suspend fun currentToken(): String {
       return database.getUserState()?.token ?: ""
    }

    override suspend fun refreshToken(previousToken: String): String {
        return ""
    }
}