package fr.fpe.slackbot.reviewassigner

import cats.Eq
import cats.instances.string._

case class EmailAddress(value: String) extends AnyVal

object EmailAddress {

  implicit val eqForEmailAddress: Eq[EmailAddress] = Eq.by[EmailAddress, String](_.value.toUpperCase)

}
