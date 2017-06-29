package com.yqc.akka

/**
  * Created by yangqc on 2017/6/29.
  *
  * 1.Resume：子Actor会忽略引起异常的消息，继续处理mailbox里的后续消息；
  * 2.Restart：终止引发异常的子Actor，然后初始化一个全新的Actor。但是，存储
  * 在原有Actor的mailbox中的消息会转移到这个新Actor中，并继续处理这些消
  * 息。对外而言，除了引起异常的消息没有处理成功，其他处理行为看起来就像
  * 没有发生错误一般；
  * 3.Stop：终止引发异常的子Actor，后续发来的消息将不被处理，而是被转到
  * deadLetters队列中；
  * 4.Escalate：当前子Actor的supervisor不处理该失败，而是转交给更上一级的
  * supervisor来处理。
  * 在AKKA中，仅将监控Actor的权力赋予该Actor的创建者。这种监控方式在AKKA中
  * 被称之为Parental Supervisior。
  *
  * AKKA为Actor的Supervision提供了SupervisorStrategy，分为AllForOneStrategy和
  * OneForOneStrategy。默认情况下，Actor的SupervisorStrategy被设置为
  * OneForOneStrategy。
  */
