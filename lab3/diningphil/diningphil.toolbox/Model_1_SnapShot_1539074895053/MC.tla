---- MODULE MC ----
EXTENDS diningphil, TLC

\* INIT definition @modelBehaviorNoSpec:0
init_153907489402142000 ==
FALSE/\p1 = 0
----
\* NEXT definition @modelBehaviorNoSpec:0
next_153907489402143000 ==
FALSE/\p1' = p1
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 10:48:14 CEST 2018 by fredrik
