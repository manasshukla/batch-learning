This example shows how to pass Job Params to create a job instance. In this example we will supply the job params from the command line while running the job. 
So the execution of the job will look like mentioned below : 

` java -jar batch-learning-0.0.1-SNAPSHOT.jar "param1=Value1" "param2=value2"
`

To access the parameters in the code, we will use the API exposed by StepContext. To get the StepContext, we would use the ChunkContext. 
Below is the code snippet that reads the job param : 

`String param1 = chunkContext.getStepContext().getJobParameters().get("param1").toString`

In the above line we use the chunkContext to get the StepContext. We can then get a map of parameters from the StepContext.
This could be done inside the execute method of the Tasklet as ChunkContext is available as the input param to the function. 

See code [here](./src/main/java/com/practise/batch/BatchLearningApplication.java)

To run the job with the same code, run the below command :

`java -jar batch-learning-0.0.1-SNAPSHOT.jar "item=Cricket Bat" "package_date(date)=2020/01/01"
`

PS : Once the job has run, subsequent job runs would throw exceptions if the job params are not changed.