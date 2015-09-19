import persistence.core.CustomerRepository

/**
 * Created by Chirdeep.Tomar on 10/09/2015.
 */
object Playground extends App {
  override def main(args: Array[String]): Unit = {
    val repo = new CustomerRepository
    val user  = repo.get(3)
    repo.create(user.get)

    if (user.isDefined) {
      println("Hello " + user.get.name + " you are " + user.get.getAge + " years old today");
    }
  }
}
