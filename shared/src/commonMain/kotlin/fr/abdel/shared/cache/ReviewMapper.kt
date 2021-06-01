package fr.abdel.shared.cache

import fr.abdel.kmmdessert.GetDessertQuery
import fr.abdel.kmmdessert.GetReviewQuery
import fr.abdel.kmmdessert.NewReviewMutation
import fr.abdel.kmmdessert.UpdateReviewMutation
import fr.abdel.kmmdessert.shared.cache.Review

fun GetReviewQuery.GetReview.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)

fun GetDessertQuery.Review.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)

fun NewReviewMutation.CreateReview.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)


fun UpdateReviewMutation.UpdateReview.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)