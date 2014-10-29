package com.chirpit

import dds.config.DefaultEntities.{defaultDomainParticipant, defaultPolicyFactory}
import scala.io.StdIn._
import dds._
import java.util.UUID

object ChirpOut {


  def createChirp(user: String, msg: String) = {
    val cid = UUID.randomUUID().toString
    val id = new ChirpId(user, cid)
    val location = new Location()
    location.location(new SomeLocation(0, 0))
    val h = new ChirpHeader(id, location, System.currentTimeMillis(), ChirpActionKind.CHIRP_KIND)
    val b = new ChirpBody()
    b.chirp(msg)
    new ChirpAction(h, b)
  }

  def main(args: Array[String]) = {
    if (args.length > 0) {

      val user = args(0)

      val topic = Topic[ChirpAction](Config.ChirpActionTopic)

      val pubQos = PublisherQos().withPolicy(Partition(user))
      implicit val pub = Publisher(pubQos)

      val dwQos = DataWriterQos().withPolicies(
        Reliability.Reliable,
        Durability.TransientLocal
      )
      val dw = DataWriter[ChirpAction](pub, topic, dwQos)
      println("\n\n\n")
      println(Console.GREEN + "[Press Enter to terminate]\n" + Console.RESET)
      print(Console.GREEN + "chirp >> " + Console.RESET)
      var msg = readLine().take(Config.CHIRP_MAX_LEN)
      while (msg.length > 1) {
        val chirp = createChirp(user, msg)
        dw.write(chirp)
        print(Console.GREEN + "chirp >> " + Console.RESET)
        msg = readLine().take(Config.CHIRP_MAX_LEN)
      }
      dw.close()
      pub.close()
      defaultDomainParticipant.close()
    }

  }
}
