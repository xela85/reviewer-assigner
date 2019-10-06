package fr.fpe.slackbot.reviewassigner

case class Reviewer(slackUsername: SlackUsername, emailAddress: EmailAddress)

case class SlackUsername(value: String) extends AnyVal