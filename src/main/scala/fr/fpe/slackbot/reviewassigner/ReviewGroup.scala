package fr.fpe.slackbot.reviewassigner

import cats.syntax.eq._

case class ReviewGroup(reviewers: List[Reviewer]) {

  def contains(emailAddress: EmailAddress): Boolean = reviewers.exists(_.emailAddress === emailAddress)

}

object ReviewGroup {
  def MaxSize = 3
}
