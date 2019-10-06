package fr.fpe.slackbot.reviewassigner

case class ReviewGroup(reviewers: List[Reviewer])

object ReviewGroup {
  def MaxSize = 3
}
