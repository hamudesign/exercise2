package design.hamu

import design.hamu.job.Job
import java.io.FileNotFoundException
import java.lang.NullPointerException

object Main {

  def main(args: Array[String]): Unit = args match {
    case Array(jobName, fileName) =>
      val job = Job(jobName)
      val result = FileReader
        .readFile(fileName)
        .flatMap(job.apply)
      result match {
        case Right(json) => println(json.spaces2)
        case Left(e) => {
          e match {
            case e: NullPointerException =>
              println(
                s"Job ${jobName} failed with a null pointer exception. File needs to be located in src/main/resources"
              )
            case e: Exception => println(s"Job ${jobName} failed with error ${e.getMessage}")
          }
        }
      }
    case _ =>
      println("Invalid input args: [jobName] [fileName]")
  }
}
