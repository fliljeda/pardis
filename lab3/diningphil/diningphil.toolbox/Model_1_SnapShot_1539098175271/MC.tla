---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_153909817322977000 == 
-1
----

\* INIT definition @modelBehaviorInit:0
init_153909817322978000 ==
Init
----
\* NEXT definition @modelBehaviorNext:0
next_153909817322979000 ==
Next
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153909817322980000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 17:16:13 CEST 2018 by fredrik
