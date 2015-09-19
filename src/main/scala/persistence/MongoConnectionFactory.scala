package persistence

import reactivemongo.api.{MongoConnection, MongoDriver}

import scala.concurrent.ExecutionContext.Implicits.global

object MongoConnectionFactory {

  def connect(host: String) = {
    // gets an instance of the driver
    // (creates an actor system)
    val driver = new MongoDriver
    val connection = driver.connection(List(host))
    connection("scala-play")
  }

  def disconnect(connection: MongoConnection)  = {
    connection.close()
  }
}
