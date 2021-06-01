package fr.abdel.shared.repository

import com.apollographql.apollo.api.ApolloExperimental
import fr.abdel.kmmdessert.DeleteReviewMutation
import fr.abdel.kmmdessert.GetReviewQuery
import fr.abdel.kmmdessert.NewReviewMutation
import fr.abdel.kmmdessert.UpdateReviewMutation
import fr.abdel.kmmdessert.shared.cache.Review
import fr.abdel.kmmdessert.type.ReviewInput
import fr.abdel.shared.ApolloProvider
import fr.abdel.shared.cache.toReview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single

class ReviewRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {
    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getReview(reviewId: String): Review? {
        val response = apolloClient.query(GetReviewQuery(reviewId)).execute().single()
        return response.data?.getReview?.toReview()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun newReview(dessertId: String, reviewInput: ReviewInput): Review? {
        val response = apolloClient.mutate(NewReviewMutation(dessertId, reviewInput)).execute().single()
        return response.data?.createReview?.toReview()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun updateReview(reviewId: String, reviewInput: ReviewInput): Review? {
        val response = apolloClient.mutate(UpdateReviewMutation(reviewId, reviewInput)).execute().single()
        return response.data?.updateReview?.toReview()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun deleteReview(reviewId: String): Boolean? {
        val response = apolloClient.mutate(DeleteReviewMutation(reviewId)).execute().single()
        return response.data?.deleteReview
    }
}