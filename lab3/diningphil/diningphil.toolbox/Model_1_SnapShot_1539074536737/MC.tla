---- MODULE MC ----
EXTENDS diningphil, TLC

\* CONSTANT definitions @modelParameterConstants:0phils
const_15390745348937000 == 
{{p5, p2}, {p1, p3}, {p2, p4}, {p3, p5}}
----

\* INIT definition @modelBehaviorNoSpec:0
init_15390745348938000 ==
FALSE/\p1 = 0
----
\* NEXT definition @modelBehaviorNoSpec:0
next_15390745348939000 ==
FALSE/\p1' = p1
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 10:42:14 CEST 2018 by fredrik
