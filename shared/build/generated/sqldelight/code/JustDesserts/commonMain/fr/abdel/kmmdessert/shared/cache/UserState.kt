package fr.abdel.kmmdessert.shared.cache

import kotlin.String

public data class UserState(
  public val userId: String,
  public val token: String
) {
  public override fun toString(): String = """
  |UserState [
  |  userId: $userId
  |  token: $token
  |]
  """.trimMargin()
}
