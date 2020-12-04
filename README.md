This example shows how to add multiple steps in a Job. All that is needed is to create a Step Bean for each step and add it to the Job Bean using the next() method.
After successful run, we would see that the new steps are also reflected in the `BATCH_STEP_EXECUTION` table in the job repo. 

Find the code [here](./src/main/java/com/practise/batch/BatchLearningApplication.java)
