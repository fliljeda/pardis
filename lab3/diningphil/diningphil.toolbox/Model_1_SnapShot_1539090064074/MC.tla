---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_153909006202269000 == 
-1
----

\* INIT definition @modelBehaviorInit:0
init_153909006202270000 ==
Init
----
\* NEXT definition @modelBehaviorNext:0
next_153909006202271000 ==
Next
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153909006202272000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 15:01:02 CEST 2018 by fredrik
