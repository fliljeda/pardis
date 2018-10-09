
(* AUTHORS: FREDRIK LILJEDAHL, FILIP JANSSON   *)
(* DATE: 2018-10-09                            *)
----------------------- MODULE diningphil -----------------------------
EXTENDS Integers

CONSTANT Free (* -1 to indicate free fork *)
VARIABLES  philosophers, forks, fairray

(* Maybe release single fork*)                           
  
(*LeftFork(phil) == phil
RightFork(phil) == (phil-1)%5   FOR DEADLOCK*)

vars == <<philosophers, forks, fairray>>  
                     
LeftFork(phil) == IF phil /= 0 THEN phil ELSE 4
RightFork(phil) == IF phil /= 0 THEN phil -1 ELSE phil 

forkFree(i) == forks[i] = Free
grabFork(index,owner) == forks' = [forks EXCEPT ![index] = owner] (*  *)
releaseForks(phil) == /\ forks' = [forks EXCEPT ![LeftFork(phil)] = Free, 
                                                ![RightFork(phil)] = Free]

checkFairray(phil) == ~\E x \in 0..4: fairray[x] < fairray[phil] 

fairrayFilled == \A x \in 0..4: fairray[x] = 1

thinking(phil) ==  /\ philosophers[phil] = "thinking"
                   /\ forkFree(LeftFork(phil)) 
                   /\ grabFork(LeftFork(phil),phil)
                   /\ philosophers' = [philosophers EXCEPT ![phil] = "grabbing"]
                   /\ checkFairray(phil)
                   /\ IF fairrayFilled THEN fairray' = [i \in 0..4 |-> 0] ELSE fairray' = fairray


grabbing(phil) ==  /\ philosophers[phil] = "grabbing"
                   /\ forkFree(RightFork(phil))
                   /\ grabFork(RightFork(phil), phil)
                   /\ philosophers' = [philosophers EXCEPT ![phil] = "eating"]
                   /\ checkFairray(phil)
                   /\ fairray' = fairray

eating(phil) ==    /\ philosophers[phil] = "eating"
                   /\ releaseForks(phil) 
                   /\ philosophers' = [philosophers EXCEPT ![phil] = "thinking"]
                   /\ fairray' = [fairray EXCEPT ![phil] = fairray[phil]+1]

Init == /\ philosophers = [i \in 0..4 |-> "thinking"]
        /\ forks = [i \in 0..4 |-> Free]
        /\ fairray = [i \in 0..4 |-> 0]


Fairness == \A i \in 0..4: WF_vars(grabbing(i)) /\ WF_vars(eating(i))

Next == /\ \E phil \in 0..4 : thinking(phil) \/ grabbing(phil) \/ eating(phil)
        
Spec == Init /\ [][Next]_vars /\ WF_vars(Next)             
==========================================================================