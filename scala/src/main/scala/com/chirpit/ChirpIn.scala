package com.chirpit

import dds.config.DefaultEntities.{defaultDomainParticipant, defaultPolicyFactory}
import scala.io.StdIn._
import org.omg.dds.pub.PublisherQos
import dds.Publisher
import dds._
import dds.prelude._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

object ChirpIn {
  def main(args: Array[String]) {
    if (args.length > 0) {
      val user = args(0)
      val topic = Topic[ChirpAction](Config.ChirpActionTopic)
      val subQos = SubscriberQos().withPolicy(Partition(args.toList))
      implicit val sub = Subscriber(subQos)
      val drQos = DataReaderQos().withPolicies(Reliability.Reliable, Durability.TransientLocal)
      val dr = dds.DataReader[ChirpAction](sub, topic, drQos)
      dr.listen {
        case DataAvailable(_) => {
          val samples = dr.take()
          samples.toStream.filter {
            s => (s.getData != null) && (s.getData.header.kind.value() == ChirpActionKind.CHIRP_KIND.value())
          }.foreach(s => println(Console.GREEN + s.getData.header.id.uid + ">> " +  Console.RESET + s.getData.body.chirp() + "\n" ))
        }
      }


    }
  }
}
