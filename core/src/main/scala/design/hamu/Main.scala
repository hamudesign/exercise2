package design.hamu

import design.hamu.job.Job

object Main {

  def main(args: Array[String]): Unit = args match {
    case Array(jobName, fileName) =>
      val job = Job(jobName)
      val result = FileReader
        .readFile(fileName)
        .flatMap(job.apply)
      result match {
        case Right(json) => println(json.spaces2)
        case Left(e) => println(s"Job ${jobName} failed with error ${e.getMessage}")
      }
    case _ =>
      println("Invalid input args: [jobName] [fileName]")
  }
}
