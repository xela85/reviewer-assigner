package fr.fpe.slackbot.reviewassigner

import cats.kernel.Monoid
import cats.instances.map._
import cats.instances.int._
import org.scalatest.{FlatSpec, Matchers}

class ReviewGroupGeneratorSpec extends FlatSpec with Matchers {

  private def buildReviewers: Set[Reviewer] = Set(Reviewer(SlackUsername("one"), EmailAddress("one@truc.fr")),
    Reviewer(SlackUsername("two"), EmailAddress("two@truc.fr")),
    Reviewer(SlackUsername("three"), EmailAddress("three@truc.fr")),
    Reviewer(SlackUsername("four"), EmailAddress("four@truc.fr")))

  "Review randomizer" should "never put the same review twice in a group" in {
    val reviewers = buildReviewers
    val result = ReviewGroupGenerator.randomizeReviewers(reviewers)
    result.foreach { reviewGroup =>
        reviewGroup.reviewers.distinct.length shouldEqual reviewGroup.reviewers.length
    }
  }

  it should "never return return the same review group in a row" in {
    val reviewers = buildReviewers
    val result = ReviewGroupGenerator.randomizeReviewers(reviewers)
    result.distinct.size shouldEqual result.size
  }

  it should "include every reviewer equally" in {
    val reviewers = buildReviewers
    val result = ReviewGroupGenerator.randomizeReviewers(reviewers)

    val occurencesByReviewer = Monoid.combineAll(result.map(_.reviewers.map(_ -> 1).toMap))
    occurencesByReviewer.foreach { case (_, occurences) =>
      occurences shouldEqual ReviewGroup.MaxSize.toFloat / reviewers.size * result.size
    }
  }

}
