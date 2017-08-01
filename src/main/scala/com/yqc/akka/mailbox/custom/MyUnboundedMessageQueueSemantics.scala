package com.yqc.akka.mailbox.custom

import java.util.concurrent.ConcurrentLinkedQueue

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{Envelope, MailboxType, MessageQueue, ProducesMessageQueue}
import com.typesafe.config.{Config, ConfigFactory}

/**
  * 创建自己的邮箱
  * Created by yangqc on 2017/8/1
  */
trait MyUnboundedMessageQueueSemantics

object MyUnboundedMailbox {

  // MailBox 内部实现的队列
  class MyMessageQueue extends MessageQueue with MyUnboundedMessageQueueSemantics {

    private final val queue = new ConcurrentLinkedQueue[Envelope]()

    // these should be implemented; queue used as example
    def enqueue(receiver: ActorRef, handle: Envelope): Unit = queue.offer(handle)

    def dequeue(): Envelope = queue.poll()

    def numberOfMessages: Int = queue.size

    def hasMessages: Boolean = !queue.isEmpty

    def cleanUp(owner: ActorRef, deadLetters: MessageQueue) {
      while (hasMessages) {
        deadLetters.enqueue(owner, dequeue())
      }
    }
  }

}

// This is the Mailbox implementation
class MyUnboundedMailbox extends MailboxType with ProducesMessageQueue[MyUnboundedMailbox.MyMessageQueue] {

  import MyUnboundedMailbox._

  // This constructor signature must exist, it will be called by Akka
  def this(settings: ActorSystem.Settings, config: Config) = {
    // put your initialization code here
    this()
  }

  // The create method is called to create the MessageQueue
  final override def create(owner: Option[ActorRef], system: Option[ActorSystem]): MessageQueue = new MyMessageQueue()
}

class CustomActor extends Actor {
  override def receive: Receive = {
    case x => println(x)
  }
}

object MyCustomMailBoxTest {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("mySystem", ConfigFactory.load().getConfig("CustomMailBox"))
    val myActor = system.actorOf(Props[CustomActor], "myActor")
    myActor ! "12"
  }
}

