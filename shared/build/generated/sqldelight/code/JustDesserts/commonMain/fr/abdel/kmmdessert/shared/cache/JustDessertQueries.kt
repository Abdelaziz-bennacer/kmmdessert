package fr.abdel.kmmdessert.shared.cache

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String
import kotlin.Unit

public interface JustDessertQueries : Transacter {
  public fun <T : Any> selectAllDesserts(mapper: (
    id: String,
    userId: String,
    name: String,
    description: String,
    imageUrl: String
  ) -> T): Query<T>

  public fun selectAllDesserts(): Query<Dessert>

  public fun <T : Any> selectDessertById(id: String, mapper: (
    id: String,
    userId: String,
    name: String,
    description: String,
    imageUrl: String
  ) -> T): Query<T>

  public fun selectDessertById(id: String): Query<Dessert>

  public fun <T : Any> selectUserState(mapper: (userId: String, token: String) -> T): Query<T>

  public fun selectUserState(): Query<UserState>

  public fun insertDessert(
    id: String,
    userId: String,
    name: String,
    description: String,
    imageUrl: String
  ): Unit

  public fun updateDessertById(
    name: String,
    description: String,
    imageUrl: String,
    id: String
  ): Unit

  public fun removeAllDesserts(): Unit

  public fun removeDessertById(id: String): Unit

  public fun insertUserState(userId: String, token: String): Unit

  public fun removeUserState(): Unit
}
