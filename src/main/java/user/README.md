# User Data
## Demo

`src/main/java/user` includes all operations on user data.

`User.java` is the data structure for source data. 

`UserDriver.java` is the driving function for all the operations.

`UserMapper.java` and `UserReducer` is just an easy demo and may serve as a template as Mapper for future use.

## User Data Profiling
In User Data Profiling, I computed the maximum and the minimum of all the columns of Number type data, and counted all the data. 

### What I have done

`UserProfilingTuple` is a customed class created for transferring data in a better way. There are 3 members for now, `max`, `min`, and `count` to record respectively the maximum, the minimum, and the number of each field. It also overrides the read and write methods to enable a success in reading and writing this tuple within the MapReduce framework.

`UserProfilingMapper` is the mapper for user data profiling. It reads the data in String, converts the data into a Java object based on a defined `User` class. In this class, we declared `user_id`, `review_count`, and many other columns. There are 22 fields in total, covering data type `int`, `double`, and `String`. In `UserProfilingMapper`, we use a `switch ... case` control flow to handle each of the fields according to the type. For number types, we store the data to the tuple max and min and set the count to 1; for String and other types, we only need to set the count to 1. The output key for the Profiling mapper is the field name and the output value is the `UserProfilingTuple` with corresponding data. 

`UserProfilingReducer` is the reducer for user data profiling. It reads the data in a <Text, `UserProfilingTuple`>  data. Just like what we did in mapper, we again use a `switch ... case` to handle the data based on the types. For all the numbers, we compute the maximum and the minimum and sum up the count number, and for other types, we only sum up the count number. Then all the data will be output. 

### Result

I have tested the program on two datasets: a small one with only 9 lines and a big one with 3GB+. After I prepared the input data and compiling and running the program, the output is pasted at the following:

```bash
# Output of 10 lines of example
average_stars	      4.27       2.75          9
compliment_cool	    2543.0        0.0          9
compliment_cute	     361.0        0.0          9
compliment_funny	    2543.0        0.0          9
compliment_hot	    1713.0        0.0          9
compliment_list	     147.0        0.0          9
compliment_more	     163.0        0.0          9
compliment_note	    1212.0        0.0          9
compliment_photos	     323.0        0.0          9
compliment_plain	    5696.0        1.0          9
compliment_profile	     191.0        0.0          9
compliment_writer	     815.0        0.0          9
cool	   27281.0        0.0          9
elite	       0.0        0.0          9
fans	    1357.0        1.0          9
friends	       0.0        0.0          9
funny	   13066.0        1.0          9
name	       0.0        0.0          9
review_count	    4333.0       12.0          9
useful	   43091.0        6.0          9
user_id	       0.0        0.0          9
yelping_since	       0.0        0.0          9
```

```bash
# Output of the big data
average_stars          5.0        1.0    1987897
compliment_cool    49967.0        0.0    1987897
compliment_cute    13654.0        0.0    1987897
compliment_funny           49967.0        0.0    1987897
compliment_hot     25784.0        0.0    1987897
compliment_list    12669.0        0.0    1987897
compliment_more    13501.0        0.0    1987897
compliment_note    59031.0        0.0    1987897
compliment_photos          82630.0        0.0    1987897
compliment_plain          101097.0        0.0    1987897
compliment_profile         14180.0        0.0    1987897
compliment_writer          15934.0        0.0    1987897
cool      199878.0        0.0    1987897
elite          0.0        0.0    1987897
fans       12497.0        0.0    1987897
friends        0.0        0.0    1987897
funny     185823.0        0.0    1987897
name           0.0        0.0    1987897
review_count       17473.0        0.0    1987897
useful    206296.0        0.0    1987897
user_id        0.0        0.0    1987897
yelping_since          0.0        0.0    1987897
```

Each line of the output represents the field, maximum, minimum, and the number of data respectively.

As we can see from the output, we have 1987897 lines of data and the range from the compliment fields are from 0 to thousands, the small comment numbers range from 0 to hundred thousands, and the average stars is from 1 to 5. The result all conforms to their definition. And the total numbers are the same 1987897. 

Overall, the result of user data profiling meets our expectation which may save use a lot of time cleaning it. 
