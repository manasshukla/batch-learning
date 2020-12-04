This example shows setting up a job repository containing metadata of the jobs. 
The database engine used is mysql. 

Changes done : 
1. Add mysql connector dependencies in pom.xml
2. Add datasource specific properties in application.properties.

Once the Job repo is set, we will see that the job executes successfully once and then does not execute in the subsequent runs. 
The reason is that since Job Execution happens only and only if the job instance passed to it is changed. Since the job params remain same (There are no job params in this example), the job instance remains the same and hence no subsequent job runs. 

On successful run of the jobs, one would see that some job metadata tables are created in the mysql database. 
Following three tables warrant an inspection to further gain understanding of job metadata : 
 BATCH_JOB_EXECUTION,
 BATCH_JOB_INSTANCE, and
 BATCH_STEP_EXECUTION.
 
 
 