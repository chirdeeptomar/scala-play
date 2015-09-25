package persistence.core

import domain.model.Customer

import org.joda.time.format.DateTimeFormat
import persistence.MongoConnectionFactory
import persistence.entity.CustomerEntity
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONObjectID, BSONDocument}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Promise, Future}
import scala.util.{Failure, Success}

class CustomerRepository {
  private val fmt = DateTimeFormat.forPattern("dd/MM/yyyy")
  private val db = MongoConnectionFactory.connect("localhost")
  private val customerCollection = db[BSONCollection]("customer")

  val users = Map(
    1 -> new Customer("Dave", "test1@gmail.com",fmt.parseDateTime("12/12/1967")),
    2 -> new Customer("John", "test2@gmail.com", fmt.parseDateTime("03/04/1947")),
    3 -> new Customer("Joe", "test3@gmail.com", fmt.parseDateTime("11/04/1997")),
    4 -> new Customer("Nicole", "test4@gmail.com", fmt.parseDateTime("01/10/1967")))

  def get(id:Int) : Option[Customer] = {
    users.get(id)
  }

  def create(customer: Customer) = {

    val entity = CustomerEntity.toCustomerEntity(customer)

    val future: Future[WriteResult] = customerCollection.insert(entity)

    future.onComplete {
      case Failure(e) => throw e
      case Success(result) =>
        println(s"successfully inserted document with result: $result")
    }
  }

  def get(id: String) : Future[Customer] = {

    val p = Promise[Customer]()

    val query = BSONDocument("_id" -> BSONObjectID(id))

    val future = customerCollection.find(query).one[CustomerEntity]
    future.onComplete {
      case Failure(e) => throw e
      case Success(result) => {
        p.success(Customer toCustomer result.get)
      }
    }
    p.future
  }
}
