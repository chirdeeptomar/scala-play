package persistence.entity

import com.github.nscala_time.time.Imports._
import domain.model.Customer
import reactivemongo.bson.{BSONDocumentWriter, BSONDocumentReader, BSONObjectID, BSONDocument}

case class CustomerEntity(id: BSONObjectID = BSONObjectID.generate,
                          val name: String,
                          val email: String,
                          val dob: DateTime)

object CustomerEntity {

  implicit def toCustomerEntity(customer: Customer) = CustomerEntity(name = customer.name, email = customer.email, dob = customer.dob)

  implicit object CustomerEntityBSONReader extends BSONDocumentReader[CustomerEntity] {

    def read(doc: BSONDocument): CustomerEntity =
      CustomerEntity(
        id = doc.getAs[BSONObjectID]("_id").get,
        name = doc.getAs[String]("question").get,
        email = doc.getAs[String]("answer").get,
        dob = doc.getAs[String]("dob").get.asInstanceOf[DateTime]
      )
  }

  implicit object CustomerEntityBSONWriter extends BSONDocumentWriter[CustomerEntity] {
    def write(customerEntity: CustomerEntity): BSONDocument =
      BSONDocument(
        "_id" -> customerEntity.id,
        "name" -> customerEntity.name,
        "email" -> customerEntity.email,
        "dob" -> customerEntity.dob.date
      )
  }
}