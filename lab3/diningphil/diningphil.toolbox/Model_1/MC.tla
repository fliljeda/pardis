---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_1539099732567102000 == 
-1
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_1539099732567103000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_1539099732567104000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 17:42:12 CEST 2018 by fredrik
