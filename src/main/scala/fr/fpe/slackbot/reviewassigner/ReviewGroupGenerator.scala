package fr.fpe.slackbot.reviewassigner

object ReviewGroupGenerator {

  def randomizeReviewers(reviewers: Set[Reviewer]): List[ReviewGroup] =
    reviewers.subsets(ReviewGroup.MaxSize).map(_.toList).map(ReviewGroup(_)).toList

  def randomizedReviewersIterator(reviewers: Set[Reviewer]): Iterator[ReviewGroup] =
    Iterator.continually(randomizeReviewers(reviewers)).flatten

}
