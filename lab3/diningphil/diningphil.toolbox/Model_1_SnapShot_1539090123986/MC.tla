---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_153909012194273000 == 
-1
----

\* INIT definition @modelBehaviorInit:0
init_153909012194274000 ==
Init
----
\* NEXT definition @modelBehaviorNext:0
next_153909012194275000 ==
Next
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153909012194276000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 15:02:01 CEST 2018 by fredrik
