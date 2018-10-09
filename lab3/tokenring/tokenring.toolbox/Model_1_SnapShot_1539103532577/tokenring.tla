
(* AUTHORS: FREDRIK LILJEDAHL, FILIP JANSSON   *)
(* DATE: 2018-10-09                            *)
----------------------- MODULE tokenring -----------------------------
EXTENDS Naturals
CONSTANT N

VARIABLES  nodes, leader
checkLeader(i) == i = leader
nodeParent(i,p) == p = (i-1)%N
nodeChild(p,c) == c = (p+1)%N


copyParent(c) == 
                   /\ ~checkLeader(c) 
                   /\ nodes' = [nodes  EXCEPT ! [c] = nodes[(c-1)%N]] 
                   /\ leader' = leader
                    
updateLeader(n) == /\ checkLeader(n)
                   /\ nodes[n] = nodes[(n-1)%N]
                   /\ nodes' = [nodes EXCEPT ![n] = nodes[n] +1]
                   /\ leader' = leader


Init ==  nodes = [i \in 0..N |-> i] /\ leader \in 0..N
 
         
         
Next == \E node \in 0..N: copyParent(node) \/ updateLeader(node)
(* Maybe release single fork*)                           
  
(*LeftFork(phil) == phil
RightFork(phil) == (phil-1)%5   FOR DEADLOCK*)

        
==========================================================================