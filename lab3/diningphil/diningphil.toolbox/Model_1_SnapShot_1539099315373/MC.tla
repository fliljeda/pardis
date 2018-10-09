---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_153909931329687000 == 
-1
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_153909931329688000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153909931329689000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 17:35:13 CEST 2018 by fredrik
