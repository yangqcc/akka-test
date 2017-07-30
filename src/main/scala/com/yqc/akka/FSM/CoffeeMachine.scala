package com.yqc.akka.FSM

import akka.actor.FSM
import com.yqc.akka.FSM.CoffeeMachine._

/**
  * scala的FSM
  * Created by yangqc on 2017/7/25
  */
object CoffeeMachine {

  sealed trait MachineState

  case object Open extends MachineState

  case object ReadyToBuy extends MachineState

  case object PoweredOff extends MachineState

  case class MachineData(currentTxTotal: Int, costOfCoffee: Int, coffeesLeft: Int)

}

class CoffeeMachine extends FSM[MachineState, MachineData] {
  //What State and Data must this FSM start with (duh!)
  startWith(Open, MachineData(12, 21, 21))

  //Handlers of State
  when(Open) {
    case Event("yangqc", t@MachineData(1, 1, 1)) => stay() using t
    case _ => stay() using MachineData(2, 2, 2)
  }

  /*
    when(ReadyToBuy) {
      case Open -> ReadyToBuy =>
      case _ =>
    }

    when(PoweredOff) {
      case Open -> ReadyToBuy =>
      case _ =>
    }
  */

  //fallback handler when an Event is unhandled by none of the States.
  whenUnhandled {
    case _ =>
      println("this is unhandled!")
      stay() using MachineData(2, 2, 2)
  }

  //Do we need to do something when there is a State change?
  onTransition {
    case Open -> ReadyToBuy =>
    case _ =>
  }
}
