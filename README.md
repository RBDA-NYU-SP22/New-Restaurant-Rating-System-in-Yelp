# New-Restaurant-Rating-System-in-Yelp
## Introduction
In our project, we developed a new rating system that takes users quality into account. We believe that an active user with more agreements from other users tends to give a high quality reviews and we should consider more on those kinds of reviews given by a high-quality. To give an improved rating on restaurants on Yelp, we came up with several algorithms on rating users and reviews to re-rating the restaurants. The algorithms are implemented based on the Yelp's data on reviews, users, tips, and businesses. For more about the project, you can refer to our [Final Report](https://github.com/RBDA-NYU-SP22/RBDA-final-report)

(The source codes on businesses is at [RBDA-NYU-SP22/Business-Analysis](https://github.com/RBDA-NYU-SP22/Business-Analysis))

## Algorithm
![lagrida_latex_editor](https://user-images.githubusercontent.com/39271899/167480277-0a6eb1dc-a4b8-453e-9b1b-30922d2ac732.png)

The above is the algorithm we designed on re-rating user and it is implemented at [UserProcessMapper](./src/main/java/user/UserProcessMapper.java)

## How to run
We use [picocli](https://picocli.info/) to manage commands arguments.
The inputs should be like this
``` bash
Usage: Nersy [--rm] STEP SRC INPUT OUTPUT
      STEP     Specify the step of the data manipulation process
      SRC      data source to process
      INPUT    Input directory
      OUTPUT   Output directory
      --rm     Remove the output file if it exists
```


## Install Maven
Download binary version
``` bash
wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz
```
Unzip the downloaded package
``` bash
tar xzvf apache-maven-3.8.5-bin.tar.gz
```
Add mvn to path
``` bash
echo 'export PATH=/home/$USER/apache-maven-3.8.5/bin:$PATH' >> ~/.bashrc
```
Verify the installation
``` bash
mvn -v
```

## Build and Run on HPC
Pull the source code from GitHub

```bash
git pull
```

build the jar file

```
mvn clean package
```

Prepare the input files
``` bash
  hadoop fs -mkdir /user/$USER/project
  hadoop fs -mkdir /user/$USER/project/input
  hadoop fs -put <inputfile> /user/$USER/project/input
  hadoop fs -ls /user/$USER/project/input
```

Remove the output directory in HDFS

```bash
hadoop fs -rm -r /user/$USER/project/output
```

Run with Hadoop

```
hadoop jar target/nersy-jar-with-dependencies.jar Nersy <STEP> <SRC> /user/$USER/project/input/ /user/$USER/project/output/
```

Check the results
``` bash
  hadoop fs -ls /user/$USER/project/output
  hadoop fs -cat /user/$USER/project/output/part-r-00000
```

## How to set up a local developing environment (No installation of Hadoop needed)
* We use Maven to manage the dependencies
* For Windows users, a winutil is needed. Be sure to download a compatible version [here](https://github.com/steveloughran/winutils) and add it to the environment of PC.
* Restart the IDEA or maybe the computer after setting up the environment



## Tableau and Hive ODBC Driver

We use [Tableau for Students](https://www.tableau.com/academic/students) to analyze the data and use [Hive ODBC Driver 2.6.11 (cloudera.com)](https://www.cloudera.com/downloads/connectors/hive/odbc/2-6-11.html) to connect the HPC. The following steps are copied from professor Tang's slides lecture 8. Connect to the HPC with the VPN on.

**Step 1:** Download and install Tableau Desktop using the free student license.

https://www.tableau.com/academic/students

**Step 2:** Download and install the the Cloudera Hive ODBC Driver.

https://www.cloudera.com/downloads/connectors/hive/odbc/

**Step 3:** Launch Tableau Desktop.

**Step 4:** Select Cloudera Hadoop on the left sidebar.

**Step 5:** Fill out the server information as shown in the following screenshot.

> Server: hm-1.hpc.nyu.edu
>
> Authentication: Username and Password
>
> Username: Your_NetID
>
> Password: \*\*\*\*\*\*\*\*
>
> Transport: SASL

**Step 6:** Click Schema on the left sidebar.

**Step 7:** Enter your NetID.

**Step 8:** Enter your table name on the left sidebar.

**Step 9:** Drag the table into the large area at the right. You should see the data 
pulled from HDFS and appear in Tableau.

**Step 10:** Click Sheet 1 at the bottom.

**Step 11:** From there, watch the Tableau demos to learn how to use it.

https://www.tableau.com/learn/get-started/creator

## Reference

[Hadoop: Intellij??????Maven?????????????????????MapReduce?????? (????????????Hadoop???HDFS??????) - Penguin (polarxiong.com)](https://www.polarxiong.com/archives/Hadoop-Intellij??????Maven?????????????????????MapReduce??????-????????????Hadoop???HDFS??????.html)

[steveloughran/winutils: Windows binaries for Hadoop versions (built from the git commit ID used for the ASF relase) (github.com)](https://github.com/steveloughran/winutils)

[???????????????????????? HADOOP_HOME and hadoop.home.dir are unset. - yucreator - ????????? (cnblogs.com)](https://www.cnblogs.com/yuqiliu/p/14388423.html)
