// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package fr.abdel.kmmdessert.type

import com.apollographql.apollo.api.InputType
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import kotlin.Int
import kotlin.String
import kotlin.Suppress

/**
 * The input of the review without the identifier
 */
@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class ReviewInput(
  val rating: Int,
  val text: String
) : InputType {
  override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller.invoke { writer ->
    writer.writeInt("rating", this@ReviewInput.rating)
    writer.writeString("text", this@ReviewInput.text)
  }
}
