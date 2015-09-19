package domain.model

/**
 * Created by Chirdeep.Tomar on 10/09/2015.
 */

import com.github.nscala_time.time.Imports._
import org.joda.time.Years

class Customer(val name: String, val email: String, val dob: DateTime) {

  def getAge = {
    Years.yearsBetween(dob,DateTime.now()).getYears
  }
}
