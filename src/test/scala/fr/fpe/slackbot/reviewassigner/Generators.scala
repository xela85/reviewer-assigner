package fr.fpe.slackbot.reviewassigner

import org.scalacheck.{Arbitrary, Gen}

object Generators {

  implicit val emailAddressGenerator: Arbitrary[EmailAddress] = Arbitrary {
    for {
      beforeArobase <- Gen.alphaStr
      beforePoint <- Gen.alphaStr
      afterPoint <- Gen.listOfN(2, Gen.alphaChar)
    } yield EmailAddress(s"$beforeArobase@$beforePoint.${afterPoint.mkString}")
  }

  implicit val reviewerGenerator: Arbitrary[Reviewer] = Arbitrary {
    for {
      emailAddress <- emailAddressGenerator.arbitrary
      slackUsernameStr <- Gen.alphaNumStr
    } yield Reviewer(SlackUsername(slackUsernameStr), emailAddress)
  }

  implicit def setOf[T: Arbitrary]: Arbitrary[Set[T]] = Arbitrary(Arbitrary.arbitrary[T].flatMap(a => Gen.listOf(a)).map(_.toSet))

}
