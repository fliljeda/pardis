---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0Free
const_153909952499196000 == 
-1
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_153909952499297000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153909952499298000 ==
\A i \in 0..4: philosophers[i] = "grabbing" ~> philosophers[i] = "eating"
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 17:38:44 CEST 2018 by fredrik
