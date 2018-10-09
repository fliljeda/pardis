---- MODULE MC ----
EXTENDS tokenring, TLC

\* CONSTANT definitions @modelParameterConstants:0N
const_153910548957132000 == 
5
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_153910548957133000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153910548957134000 ==
<> \E x \in 0..N-1: \A i \in 0..N-1 : nodes[i] = x
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 19:18:09 CEST 2018 by filip
