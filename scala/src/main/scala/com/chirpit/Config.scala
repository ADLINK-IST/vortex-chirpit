package com.chirpit

import scala.io.StdIn._


object Config {
  val ChirpActionTopic = "ChirpAction"
  val EXIT = 0
  val CHIRP = 1
  val RECHIRP = 2
  val LIKE = 3
  val DISLIKE = 4
  val FOLLOW = 5
  val UNFOLLOW = 6

  val CHIRP_MAX_LEN = 128

  def selection(): Int = {
    println(s"$CHIRP - chirp")
    println(s"$RECHIRP - rechirp")
    println(s"$LIKE - like")
    println(s"$DISLIKE- dislike")
    println(s"$FOLLOW - follow")
    println(s"$UNFOLLOW - unfollow")
    println(s"$EXIT - exit")
    readInt()
  }

}
