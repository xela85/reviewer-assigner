package fr.fpe.slackbot.reviewassigner

trait ReviewAssigner {
  def assignReviews(emailAddress: EmailAddress, currentOffset: Int): (Int, ReviewGroup)
}

class ReviewAssignerImpl(reviewers: List[Reviewer]) extends ReviewAssigner {

  def assignReviews(emailAddress: EmailAddress, currentOffset: Int): (Int, ReviewGroup) = {
    val (reviewGroup, nextOffset) = ReviewGroupGenerator.randomizeReviewers(reviewers.toSet)
      .zipWithIndex
      .drop(currentOffset)
      .filterNot(_._1.contains(emailAddress))
      .head
    (nextOffset, reviewGroup)
  }


}
