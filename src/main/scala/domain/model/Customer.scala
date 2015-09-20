package domain.model

/**
 * Created by Chirdeep.Tomar on 10/09/2015.
 */

import com.github.nscala_time.time.Imports._
import org.joda.time.Years
import persistence.entity.CustomerEntity

case class Customer(name: String, email: String, dob: DateTime) {
  def getAge = {
    Years.yearsBetween(dob,DateTime.now()).getYears
  }
}

object Customer {

  implicit def toCustomer(customerEntity: CustomerEntity): Customer =
    Customer(customerEntity.name, customerEntity.email, customerEntity.dob)
}
