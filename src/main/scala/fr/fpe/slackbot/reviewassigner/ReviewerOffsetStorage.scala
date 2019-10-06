package fr.fpe.slackbot.reviewassigner

import java.nio.file.{Files, Path}

import cats.effect.concurrent.{MVar, Ref}
import cats.effect.{ContextShift, IO}

import scala.jdk.CollectionConverters._

class ReviewerOffsetStorage(reviewAssigner: ReviewAssigner, filePath: Path, currentOffsetRef: Ref[IO, Int])(implicit cs: ContextShift[IO]) {

  def assignReviews(emailAddress: EmailAddress): IO[ReviewGroup] =
    currentOffsetRef.modify(reviewAssigner.assignReviews(emailAddress, _))

}

object ReviewerOffsetStorage {

  private def fileLock(implicit cs: ContextShift[IO]): IO[MVar[IO, Unit]] = MVar[IO].of(())

  def create(path: Path, reviewAssigner: ReviewAssigner)(implicit cs: ContextShift[IO]): IO[ReviewerOffsetStorage] = for {
    offset <- readIndexFile(path)
    currentOffsetRef <- Ref[IO].of(offset)
  } yield new ReviewerOffsetStorage(reviewAssigner, path, currentOffsetRef)

  private def readIndexFile(filePath: Path)(implicit contextShift: ContextShift[IO]): IO[Int] =
    fileLock.bracket { _ =>
      for {
        fileLines <- IO(Files.readAllLines(filePath)).map(_.asScala)
      } yield fileLines.head.toInt
    }(_ => IO.pure(()))

}