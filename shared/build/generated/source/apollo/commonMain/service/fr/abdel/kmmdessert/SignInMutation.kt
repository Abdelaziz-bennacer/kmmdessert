// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package fr.abdel.kmmdessert

import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import fr.abdel.kmmdessert.type.UserInput
import kotlin.Any
import kotlin.Array
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Map
import kotlin.jvm.Transient
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class SignInMutation(
  val userInput: UserInput
) : Mutation<SignInMutation.Data, SignInMutation.Data, Operation.Variables> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      this["userInput"] = this@SignInMutation.userInput
    }

    override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller.invoke { writer ->
      writer.writeObject("userInput", this@SignInMutation.userInput.marshaller())
    }
  }

  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = variables
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper.invoke {
    Data(it)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = parse(Buffer().write(byteString), scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> = parse(byteString, DEFAULT)

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString =
      OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = scalarTypeAdapters
  )

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    scalarTypeAdapters: ScalarTypeAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    scalarTypeAdapters = scalarTypeAdapters
  )

  data class User(
    val __typename: String = "User",
    val id: String,
    val email: String
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@User.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@User.id)
      writer.writeString(RESPONSE_FIELDS[2], this@User.email)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("id", "id", null, false, null),
          ResponseField.forString("email", "email", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): User = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val id = readString(RESPONSE_FIELDS[1])!!
        val email = readString(RESPONSE_FIELDS[2])!!
        User(
          __typename = __typename,
          id = id,
          email = email
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<User> = ResponseFieldMapper { invoke(it) }
    }
  }

  data class SignIn(
    val __typename: String = "UserResponse",
    val token: String,
    val user: User
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@SignIn.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@SignIn.token)
      writer.writeObject(RESPONSE_FIELDS[2], this@SignIn.user.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("token", "token", null, false, null),
          ResponseField.forObject("user", "user", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): SignIn = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val token = readString(RESPONSE_FIELDS[1])!!
        val user = readObject<User>(RESPONSE_FIELDS[2]) { reader ->
          User(reader)
        }!!
        SignIn(
          __typename = __typename,
          token = token,
          user = user
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<SignIn> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    /**
     * Authenticate an existing user
     */
    val signIn: SignIn?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeObject(RESPONSE_FIELDS[0], this@Data.signIn?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("signIn", "signIn", mapOf<String, Any?>(
            "userInput" to mapOf<String, Any?>(
              "kind" to "Variable",
              "variableName" to "userInput")), true, null)
          )

      operator fun invoke(reader: ResponseReader): Data = reader.run {
        val signIn = readObject<SignIn>(RESPONSE_FIELDS[0]) { reader ->
          SignIn(reader)
        }
        Data(
          signIn = signIn
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Data> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "75bd9776a72480420ab3ace092209a4adbb6230918cb95d93f609dc25d5f762c"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |mutation SignIn(${'$'}userInput:UserInput!) {
          |  signIn(userInput:${'$'}userInput) {
          |    __typename
          |    token
          |    user {
          |      __typename
          |      id
          |      email
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String = "SignIn"
    }
  }
}
