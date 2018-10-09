------------------------ MODULE diningphil -----------------------------
EXTENDS Integers

CONSTANT Free (* -1 to indicate free fork *)
VARIABLES  philosophers, forks

(* Maybe release single fork*)                           
  
LeftFork(phil) == phil
RightFork(phil) == (phil-1)%5
  
                     
(*LeftFork(phil) == IF phil /= 0 THEN phil ELSE 4
RightFork(phil) == IF phil /= 0 THEN phil -1 ELSE phil *)

forkFree(i) == forks[i] = Free
grabFork(index,owner) == forks' = [forks EXCEPT ![index] = owner] (*  *)
releaseForks(phil) == /\ forks' = [forks EXCEPT ![LeftFork(phil)] = Free, 
                                                ![RightFork(phil)] = Free]




thinking(phil) ==  /\ philosophers[phil] = "thinking"
                   /\ forkFree(LeftFork(phil)) 
                   /\ grabFork(LeftFork(phil),phil)
                   /\ philosophers' = [philosophers EXCEPT ![phil] = "grabbing"]

grabbing(phil) ==  /\ philosophers[phil] = "grabbing"
                   /\ forkFree(RightFork(phil))
                   /\ grabFork(RightFork(phil), phil)
                   /\ philosophers' = [philosophers EXCEPT ![phil] = "eating"]

eating(phil) ==    /\ philosophers[phil] = "eating"
                   /\ releaseForks(phil) 
                   /\ philosophers' = [philosophers EXCEPT ![phil] = "thinking"]


Init == /\ philosophers = [i \in 0..4 |-> "thinking"]
        /\ forks = [i \in 0..4 |-> Free]

Next == \E phil \in 0..4 : thinking(phil) \/ grabbing(phil) \/ eating(phil)

                     
==========================================================================