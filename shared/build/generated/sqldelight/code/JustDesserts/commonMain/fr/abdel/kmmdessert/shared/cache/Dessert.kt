package fr.abdel.kmmdessert.shared.cache

import kotlin.String

public data class Dessert(
  public val id: String,
  public val userId: String,
  public val name: String,
  public val description: String,
  public val imageUrl: String
) {
  public override fun toString(): String = """
  |Dessert [
  |  id: $id
  |  userId: $userId
  |  name: $name
  |  description: $description
  |  imageUrl: $imageUrl
  |]
  """.trimMargin()
}
