#!/bin/bash
javac *.java

echo "Sequential 1000"
time java Sorting 1000

echo "Sequential 100000"
time java Sorting 100000

echo "Sequential 4000000"
time java Sorting 4000000

echo "ExecutorService 1000"
time java mergeSortExecService 1000

echo "ExecutorService 100000"
time java mergeSortExecService 100000

echo "ExecutorService 4000000"
time java mergeSortExecService 4000000

echo "ForkJoinPool 1000"
time java mergeSortForkJoin 1000

echo "ForkJoinPool 100000"
time java mergeSortForkJoin 100000

echo "ForkJoinPool 4000000"
time java mergeSortForkJoin 4000000

echo "Parallelstream and Lambda 1000"
time java mergeSortStreamsLambda 1000

echo "Parallelstream and Lambda 100000"
time java mergeSortStreamsLambda 100000

echo "Parallelstream and Lambda 4000000"
time java mergeSortStreamsLambda 4000000
