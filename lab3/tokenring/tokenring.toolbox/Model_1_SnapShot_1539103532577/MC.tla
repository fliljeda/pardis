---- MODULE MC ----
EXTENDS tokenring, TLC

\* CONSTANT definitions @modelParameterConstants:0N
const_15391035301045000 == 
5
----

\* INIT definition @modelBehaviorNoSpec:0
init_15391035301046000 ==
FALSE/\leader = 0
----
\* NEXT definition @modelBehaviorNoSpec:0
next_15391035301047000 ==
FALSE/\leader' = leader
----
=============================================================================
\* Modification History
\* Created Tue Oct 09 18:45:30 CEST 2018 by filip
