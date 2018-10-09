---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_153909966038999000 == 
-1
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_1539099660389100000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_1539099660389101000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 17:41:00 CEST 2018 by fredrik
