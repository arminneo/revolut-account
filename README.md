# How to run

# Assumptions
- Just single currency
- No validation if user exists
- 

# Approaches
## Concurrency and race condition
I used blocking queue for payment and deposit. Synchronization block for thread safety. And transaction for data integrity.
## Test coverage
86% test coverage.
Covering 

# Tech Stack
- Spark Java (with Jetty)
 
# Expectations
- Thread-safety
- Big test coverage as you can also cover all the sad paths
- Simple and not over-engineered.