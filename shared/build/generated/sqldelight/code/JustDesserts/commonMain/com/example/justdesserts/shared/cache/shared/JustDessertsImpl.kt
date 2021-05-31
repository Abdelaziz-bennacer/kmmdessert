package com.example.justdesserts.shared.cache.shared

import com.example.justdesserts.shared.cache.JustDesserts
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import fr.abdel.kmmdessert.shared.cache.Dessert
import fr.abdel.kmmdessert.shared.cache.JustDessertQueries
import fr.abdel.kmmdessert.shared.cache.UserState
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

internal val KClass<JustDesserts>.schema: SqlDriver.Schema
  get() = JustDessertsImpl.Schema

internal fun KClass<JustDesserts>.newInstance(driver: SqlDriver): JustDesserts =
    JustDessertsImpl(driver)

private class JustDessertsImpl(
  driver: SqlDriver
) : TransacterImpl(driver), JustDesserts {
  public override val justDessertQueries: JustDessertQueriesImpl = JustDessertQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE Dessert (
          |    id TEXT NOT NULL PRIMARY KEY,
          |    userId TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    description TEXT NOT NULL,
          |    imageUrl TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE Review (
          |    id TEXT NOT NULL PRIMARY KEY,
          |    userId TEXT NOT NULL,
          |    dessertId TEXT NOT NULL,
          |    text TEXT NOT NULL,
          |    rating INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE UserState (
          |    userId TEXT NOT NULL,
          |    token TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class JustDessertQueriesImpl(
  private val database: JustDessertsImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), JustDessertQueries {
  internal val selectAllDesserts: MutableList<Query<*>> = copyOnWriteList()

  internal val selectDessertById: MutableList<Query<*>> = copyOnWriteList()

  internal val selectUserState: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> selectAllDesserts(mapper: (
    id: String,
    userId: String,
    name: String,
    description: String,
    imageUrl: String
  ) -> T): Query<T> = Query(-2042172162, selectAllDesserts, driver, "JustDessert.sq",
      "selectAllDesserts", "SELECT * FROM Dessert") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!
    )
  }

  public override fun selectAllDesserts(): Query<Dessert> = selectAllDesserts { id, userId, name,
      description, imageUrl ->
    Dessert(
      id,
      userId,
      name,
      description,
      imageUrl
    )
  }

  public override fun <T : Any> selectDessertById(id: String, mapper: (
    id: String,
    userId: String,
    name: String,
    description: String,
    imageUrl: String
  ) -> T): Query<T> = SelectDessertByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!
    )
  }

  public override fun selectDessertById(id: String): Query<Dessert> = selectDessertById(id) { id_,
      userId, name, description, imageUrl ->
    Dessert(
      id_,
      userId,
      name,
      description,
      imageUrl
    )
  }

  public override fun <T : Any> selectUserState(mapper: (userId: String, token: String) -> T):
      Query<T> = Query(718352822, selectUserState, driver, "JustDessert.sq", "selectUserState", """
  |SELECT * FROM UserState
  |LIMIT 1
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!
    )
  }

  public override fun selectUserState(): Query<UserState> = selectUserState { userId, token ->
    UserState(
      userId,
      token
    )
  }

  public override fun insertDessert(
    id: String,
    userId: String,
    name: String,
    description: String,
    imageUrl: String
  ): Unit {
    driver.execute(282549497, """
    |INSERT INTO Dessert(id, userId, name, description, imageUrl)
    |VALUES (?, ?, ?, ?, ?)
    """.trimMargin(), 5) {
      bindString(1, id)
      bindString(2, userId)
      bindString(3, name)
      bindString(4, description)
      bindString(5, imageUrl)
    }
    notifyQueries(282549497, {database.justDessertQueries.selectAllDesserts +
        database.justDessertQueries.selectDessertById})
  }

  public override fun updateDessertById(
    name: String,
    description: String,
    imageUrl: String,
    id: String
  ): Unit {
    driver.execute(352528475, """
    |UPDATE Dessert
    |SET name = ?, description = ?, imageUrl = ?
    |WHERE id = ?
    """.trimMargin(), 4) {
      bindString(1, name)
      bindString(2, description)
      bindString(3, imageUrl)
      bindString(4, id)
    }
    notifyQueries(352528475, {database.justDessertQueries.selectAllDesserts +
        database.justDessertQueries.selectDessertById})
  }

  public override fun removeAllDesserts(): Unit {
    driver.execute(-1569512330, """DELETE FROM Dessert""", 0)
    notifyQueries(-1569512330, {database.justDessertQueries.selectAllDesserts +
        database.justDessertQueries.selectDessertById})
  }

  public override fun removeDessertById(id: String): Unit {
    driver.execute(-778119744, """
    |DELETE FROM Dessert
    |WHERE id = ?
    """.trimMargin(), 1) {
      bindString(1, id)
    }
    notifyQueries(-778119744, {database.justDessertQueries.selectAllDesserts +
        database.justDessertQueries.selectDessertById})
  }

  public override fun insertUserState(userId: String, token: String): Unit {
    driver.execute(-670644071, """
    |INSERT INTO UserState(userId, token)
    |VALUES (?, ?)
    """.trimMargin(), 2) {
      bindString(1, userId)
      bindString(2, token)
    }
    notifyQueries(-670644071, {database.justDessertQueries.selectUserState})
  }

  public override fun removeUserState(): Unit {
    driver.execute(-921376978, """DELETE FROM UserState""", 0)
    notifyQueries(-921376978, {database.justDessertQueries.selectUserState})
  }

  private inner class SelectDessertByIdQuery<out T : Any>(
    @JvmField
    public val id: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectDessertById, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-1250779576, """
    |SELECT * FROM Dessert
    |WHERE id = ?
    """.trimMargin(), 1) {
      bindString(1, id)
    }

    public override fun toString(): String = "JustDessert.sq:selectDessertById"
  }
}
