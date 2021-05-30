package fr.abdel.kmmdessert.shared.cache

import kotlin.Long
import kotlin.String

public data class Review(
  public val id: String,
  public val userId: String,
  public val dessertId: String,
  public val text: String,
  public val rating: Long
) {
  public override fun toString(): String = """
  |Review [
  |  id: $id
  |  userId: $userId
  |  dessertId: $dessertId
  |  text: $text
  |  rating: $rating
  |]
  """.trimMargin()
}
