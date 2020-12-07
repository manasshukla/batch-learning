This example shows how to create a conditional flow in the Jobs. It defines what to do if a step in a jpb fails.
To define a conditional flow, we can use the `from`, `on`, and `to` transitions of the job.
Example code is given below : 

<pre><code>
jobBuilderFactory.get("packageJob")  
                 .start(packageStep())  
                 <b>.next(driveToAddress())
                 .on("FAILED").to(storePackage())
                 .from(driveToAddress()).on("*").to(deliverToCustomer()) </b>
                 .end()
                 .build();
</code></pre>

Read the bold text above as 
`ON Failure of driveToAddress step, transition to storePackage step`  
 OR  
  in a more familiar if else context `IF the drivetoAddress step FAILS, THEN execute storePackage step.
 `
 So the whole code from above would read like  
 
 <pre><code>IF 
    drivetoAddress step FAILS, 
 THEN 
    execute storePackage step 
 ELSE
    execute deliverToCustomer step 
 </code></pre>
 
 PS : Do not forget to toggle the GOT_LOST flag to true to simulate failure scenarios.
 
 Find the code [here](./src/main/java/com/practise/batch/BatchLearningApplication.java)

 