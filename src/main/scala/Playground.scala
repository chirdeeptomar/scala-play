import persistence.core.CustomerRepository

/**
 * Created by Chirdeep.Tomar on 10/09/2015.
 */

import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global


object Playground extends App {
  override def main(args: Array[String]): Unit = {
    val repo = new CustomerRepository
    val user  = repo.get(3)
//    repo.create(user.get)

    if (user.isDefined) {
      println("Hello " + user.get.name + " you are " + user.get.getAge + " years old today");
    }

    val customer = repo.get("56000992d9100e1e00be850a")
    customer.onComplete {
      case Success(data)=>
        println(s"${data.name}")
      case Failure(ex) =>
        println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
    }
  }
}
