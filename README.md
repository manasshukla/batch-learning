This example shows how to create custom statuses while running through a conditional job flow.  
To create a custom status we would need to create a class that implements `JobExecutionDecider` interface. This interface has a method `decide`
which could be implemented to returna a custom status. The return type of this method is `FlowExecutionStatus` so don't forget to wrap your custom status e.g. a String. 
`return new FlowExecutionStatus("CORRECT");`  
We can then inject this decider bean in our conditional flow and make transitions depending on the result.

 
 Find the code for decider [here](./src/main/java/com/practise/batch/CorrectIemDecider.java)  
 Find the code for job, decider bean and transition [here](./src/main/java/com/practise/batch/BatchLearningApplication.java)

 