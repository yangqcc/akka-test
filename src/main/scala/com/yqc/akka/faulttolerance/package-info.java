/**
 * Created by yangqc on 17-10-22
 */
package com.yqc.akka.faulttolerance;
/**
 * 1.When the supervisor strategy is not defined for an actor the following exceptions are handled by default:
 * <p>
 * (1)ActorInitializationException will stop the failing child actor
 * (2)ActorKilledException will stop the failing child actor
 * (3)DeathPactException will stop the failing child actor
 * (4)Exception will restart the failing child actor
 * (5)Other types of Throwable will be escalated to parent actor
 * <p>
 * <p>
 * 2.You can combine your own strategy with the default strategy:
 * <p>
 * override val supervisorStrategy =
 * OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
 * case _: ArithmeticException => Resume
 * case t =>
 * super.supervisorStrategy.decider.applyOrElse(t, (_: Any) => Escalate)
 * }
 * <p>
 * <p>
 * 3.Supervision of Top-Level Actors
 * Toplevel actors means those which are created using system.actorOf(), and they are children of the User Guardian.
 * There are no special rules applied in this case, the guardian simply applies the configured strategy.
 * <p>
 * 4.
 * (1)-1 to maxNrOfRetries, and Duration.inf to withinTimeRange,then the child is always restarted without any limit(akka默认重启策略)
 * (2)-1 to maxNrOfRetries, and a non-infinite Duration to withinTimeRange maxNrOfRetries is treated as 1
 * (3)a non-negative number to maxNrOfRetries and Duration.inf to withinTimeRange
 * withinTimeRange is treated as infinite duration (i.e.) no matter how long it takes,
 * once the restart count exceeds maxNrOfRetries, the child actor is stopped
 */