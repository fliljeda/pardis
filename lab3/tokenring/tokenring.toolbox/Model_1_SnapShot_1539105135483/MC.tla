---- MODULE MC ----
EXTENDS tokenring, TLC

\* CONSTANT definitions @modelParameterConstants:0N
const_153910513242426000 == 
5
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_153910513242427000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_153910513242428000 ==
<> \E x \in 0..N: \A i \in 0..N : nodes[i] = x
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 19:12:12 CEST 2018 by filip
