package com.example.justdesserts.shared.cache

import com.example.justdesserts.shared.cache.shared.newInstance
import com.example.justdesserts.shared.cache.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import fr.abdel.kmmdessert.shared.cache.JustDessertQueries

public interface JustDesserts : Transacter {
  public val justDessertQueries: JustDessertQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = JustDesserts::class.schema

    public operator fun invoke(driver: SqlDriver): JustDesserts =
        JustDesserts::class.newInstance(driver)
  }
}
