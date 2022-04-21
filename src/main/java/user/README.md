# User Data
## Demo/Template

`src/main/java/user` includes all operations on user data.

`User.java` is the data structure for source data. 

`UserDriver.java` is the driving function for all the operations.

`UserMapper.java` and `UserReducer` is just an easy demo and may serve as a template as Mapper for future use.

## User Data Profiling
In User Data Profiling, we compute the maximum and the minimum of all the columns of Number type data, and counted all the data. 

### Tuple

`UserProfilingTuple` is a customed class created for transferring data in a better way. There are 4 members, `max`, `min`, and `count` to record respectively the maximum, the minimum, and the number of each field and `isUserId` is for counting the number of each `user_id`. It also overrides the read and write methods to enable a success in reading and writing this tuple within the MapReduce framework.

### Mapper

`UserProfilingMapper` is the mapper for user data profiling. It reads the data in String, converts the data into a Java object based on a defined `User` class. In this class, we declared `user_id`, `review_count`, and many other columns. There are 22 fields in total, covering data type `int`, `double`, and `String`. In `UserProfilingMapper`, we use a `switch ... case` control flow to handle each of the fields mainly according to the type. For number types, we store the data to the tuple max and min and set the count to 1; for String and other types, we only need to set the count to 1. The output key for the Profiling mapper is the field name and the output value is the `UserProfilingTuple` with corresponding data. `user_id` is one special case. We also generate `<key, value>` pair in the form of `<user_id, count>` to count the appearance of each user_id to see if `user_id` is unique. `friends` is also a special case. `friends` is a long string consists with `user_id`s. So, in the mapper, we split the string to count the number of friends a user have and compute the max and min that number. 

### Reducer

`UserProfilingReducer` is the reducer for user data profiling. It reads the data in a <Text, `UserProfilingTuple`>  data. Just like what we did in mapper, we again use a `switch ... case` to handle the data based on the types. For all the numbers, we compute the maximum and the minimum and sum up the count number, and for other types, we only sum up the count number. Then all the data that is the description of fields and the `user_id` with more than 1 counting number. 

### Result

I have tested the program on two datasets: a small one with only 9 lines and a big one with 3GB+. After I prepared the input data and compiling and running the program, the output is pasted at the following:

```bash
# Output of 10 lines of example
AUi8MPWJ0mLkMfwbui27lg	2
average_stars	      4.27       2.75         10
compliment_cool	    2543.0        0.0         10
compliment_cute	     361.0        0.0         10
compliment_funny	    2543.0        0.0         10
compliment_hot	    1713.0        0.0         10
compliment_list	     147.0        0.0         10
compliment_more	     163.0        0.0         10
compliment_note	    1212.0        0.0         10
compliment_photos	     323.0        0.0         10
compliment_plain	    5696.0        1.0         10
compliment_profile	     191.0        0.0         10
compliment_writer	     815.0        0.0         10
cool	   27281.0        0.0         10
elite	       0.0        0.0         10
fans	    1357.0        1.0         10
friends	      52.0        0.0         10
funny	   13066.0        1.0         10
name	       0.0        0.0         10
review_count	    4333.0       12.0         10
useful	   43091.0        6.0         10
user_id	       0.0        0.0         10
yelping_since	       0.0        0.0         10
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
friends    14995.0        0.0    1987897
funny     185823.0        0.0    1987897
name           0.0        0.0    1987897
review_count       17473.0        0.0    1987897
useful    206296.0        0.0    1987897
user_id        0.0        0.0    1987897
yelping_since          0.0        0.0    1987897
```
The output of the data consists two parts. It shows the duplicate `user_id` in the first several lines and follows a simple analysis on each field.

Duplicate `user_id` are picked up and displayed in the first several lines. We may need to remove the duplicate ids to ensure that the `user_id` is unique.

For part two, each line of the output represents the field, maximum, minimum, and the number of data respectively.

As we can see from the output, we have 1987897 lines of data and the range from the compliment fields are from 0 to thousands, the small comment numbers range from 0 to hundred thousands, and the average stars is from 1 to 5. The result all conforms to their definition. And the total numbers are the same 1987897. 

Overall, the result of user data profiling meets our expectation which may save use a lot of time cleaning it. 

## User Data Cleaning

In User Data Cleaning, we clean the source user dataset to a usable data to database.

### Tuple

`UserCleaningTuple` is a custom class created for transferring data in a better way which contains are the fields of `User` that needed for later analysis usage. It also overrides the read and write methods to enable a success in reading and writing this tuple within the MapReduce framework.

### Mapper

`UserCleaningMapper` is the mapper for user data cleaning. It reads the data in String, converts the data into a Java object based on a defined `User` class, and output a `UserCleaningTuple`. Mapper here is for dropping the columns that we will not need any more. And we do that by removing some of the members in the `UserCleaningTuple`. Since in the profiling step, we already know that the value of each field is in the correct range, no special operation is needed in the mapper.

### Reducer

We don't really need a Reducer for the Data cleaning process since we have checked that there is no duplicate `user_id` in our dataset. 

### Result

I have tested the program on two datasets: a small one with only 9 lines and a big one with 3GB+. After I prepared the input data and compiling and running the program, the output is pasted at the following:

```
qVc8ODYU5SZjKXVBgXdI7w 585 7217 1259 5994 "" 267 3.91 250 65 55 56 18 232 844 467 467 239
j14WgRoU_-2ZE1aw1dXrJg 4333 43091 13066 27281 "ueRPE0CX75ePGMqOFVj6IQ, OqH85SAF0oLijVY_SeAk8A, BI_PXSF5M61S8O9bqR7VVA, piEVx0FaVhYm6SbQYX8erw, 30Pch18LE63MyRYOC34-8g, UnG4LPz49UWbGvBzVxQaMQ, uGSIOloqL56QKsLTvR_D7g, adxyOPvCYbr7LFhqMQp1SQ, 2lmBAfQWAI06ziEfN5NkCQ, T7xoyml0r3QTW5_16zKzAw, rCx7tb3toOJUsvdOeqYY0g, 4YIgqJiqqZKPIdoPz2Zv6w, w8yoASmlp6RSaZkFD38M1Q, RkaRfAUwjKebGe2tg7uuXQ, R_E1c9mHOIvZT0DFnOSoxg, MKgpIiuzs752BJjPeCW0cw, II_l7zYdMevzk0PFXN3T4g, 1iGkJM2v790RJGo3lJSUAg, gEso8fW-bktDlGeNxbGJ-Q, hxDeqPbPwkKEFXOfK7av9A, SXYOQBtf8VaOu6JuJGNulg, IIbvCWGtIrIS5nvX4xXwOQ, CZQUtmO5NIY-bZWkCqHeJg, -9rBe1pnWodYlfr7qXpBAA, 88jrTyFviMKJWn44JZFeQg, 3-a6VJVZ7Ga_xoKhnhe0qQ, SOCNCNisrOk-queHDhtpLA, 3yBusRwPYtMCpaBkEyhyJw, zy7MzuEjrSzYmXiLy5PV1A, uYDeoofS3HsD4quTa57ucg, p8kHm5pzSevEG7sJdGkIYA, uSyPtqgXYsAwdiPvostnLg, TGdnpYdQgPnKAd7JoC37pQ, W9InhAGYesLGqrGFWh_vbg, aGgNm24n_jpgD52nhbJiHw, WbEcyPmZT7j15CzncVBu7g, ZFxU8-XX9R2Oe1UgtV4Fdw, 3TJcP0stfxjWGsLuGQgQdg, rCfgLHJPaIi-6G8fGZ7B1A, FbN6Uqlqsoxjv2l5dnsQhA, 9w32ooe10bil7oIr5ycQAw, nfIXitXhfIuATNdeH3BYpA, NPG4PWirYbZ6P49c2J9ikA, HvxTJwdgJeaUmMWUDndN5g, JbMHLU7HOiNjACKDiZvGfw, vkBqXCyfOe5IkyD6zoXLRA, sm6AzP6HSQhI9j9V_oiPWA, PoKRQcy2qWPYhVZSEUZG6g, mrO2R1Uc_DmKKEtH2VTNDg, 5Ujdrr6RwPMjoZskZGj2cw, m6OZG66JPXVIZRkqm6reDg, n8UuPQ-nodu1xMHQheGsfg" 52 3.32 89 13 10 17 3 66 96 119 119 35
SZDeASXq7o05mMNLshsdIA 224 512 330 299 "enx1vVPnfdNUdPho6PH_wg, 4wOcvMLtU6a9Lslggq74Vg, 1OocYCAZixwbAXueW75FMw, GM_iCKAB1eszzcrTZ0zbfg, RgDVC3ZUBqpEe6Y1kPhIpw, i1NwLLny-RDwONN6B6PcWA, PYxHpeTz2ZxZNJEBIAJgvg, iintcYjvi6p0t_6y72KlZg, mBg3-7rnNwLScbARYHI2GQ, ri4ujkuugWObCu9AkLJqAQ, fQl9-OzXtisnX9l9P6A2bQ, 8nJqiKD4bLoby3hsxbGuZQ, j-X6katTF0OfkdgO6FQFfw, iB9JYE9SFglGQlv2bCyQrg, Do0zZReqXeCQ8Hgl3776pg, v5rsc8m5ax-8nUlIVwRzTA, 1i2LWsWjwwutX9e6coYGxw, KGuVYGYVlPIhmjbct77fEg, zkGbmHWnt5DJjhflEbw-lg, qTypCfhOn6LctbByauKKdA, 6jTlFMNXaClz7veYzJcM_A, 9CWr1HukCWdjW-5zTCXVIA, 57PP2IenLvvbECNbkm3gHA, CCJ9MEk39jYA2mRNQjkWrA, UzUnpnlH1n346MDgaFMOmw, 5-LDJ6a9mZl08mLj2rXZ_A, LFha-r2VljAzN13YwySTXw, 866bNH6RTDVVl-14OyD9Sw, xtB77qTiYR2VknLcmpx4ow, ZhUNLBzrocf-RAT7qdFk0g, 83Xb0PPBwZiG2c_fLpZgAw, KhjOWuMOTtLuxqqjMaoagA, rhA8QRe-jnRLqR8hBHK1bQ, 7BSP1GSsJkiEXvA3dOPx_Q" 28 4.27 24 4 1 6 2 12 16 26 26 10
hA5lMy-EnncsH4JoR-hFGQ 79 29 15 7 "PBK4q9KEEBHhFvSXCUirIw, 3FWPpM7KU1gXeOM_ZbYMbA, AWpdSLtet45ZfbYxR_QtGw, QTLb-h7-9RYFDGkDX3-dJg, gmb_vgiPYXS_g-2tXevDng, hpw0VmkI1okMheHoE_Zg5A, UN26eUIXD5HSZt-b6DA_qQ, ZPffRCIk8Y0HJFzvhZebbg, hjUxm7j0uqmhD7OtZFZ2sQ, 3B9OpzTdQt2qZCJNcwXB_A, SVmMQFw-eqDMzizruVhypQ, Y04yRCQ12M435p6v2X3uow, W1EVCvCnle1CQEnuvV7xFQ, aBrrtkB4j1eewa_S9nkwBA, tjm3b3Ts8msuGiYRybHx0Q, Z1MFQFgn-O9ADIOr6g-wEQ, tEUxh2rK4M_9r_XBH4S-oQ, FrWXMb2ixxJG49YuhIye4g, 16cK8VltpHEGMNbqpbpXpA, S3CpkNFpx1QUW0Q4xTWhIw, NgMSgZNDXCWnA1UB6ngFxQ, GR-pfj00PXOaqCSRiF4NCA, wQR26Y7QyZY1eXvi989s-g, ccFwJ1oUv5ILhHS6OXac2A, xGbN2D2YUouarZiZG_0GMQ, SM7CpJ3WwcdoAu59jvtLzA, F7JfpRM9Ld2RMvXrL2DWZA" 1 3.54 1 1 0 0 0 1 1 0 0 0
q_QQ5kBBwlCcbL1s4NVK3g 1221 14953 9940 11211 "xBDpTUbai0DXrvxCe3X16Q, KLrTBLpN4csEWKdLX2meYQ" 1357 3.85 1713 163 191 361 147 1212 5696 2543 2543 815
cxuxXkcihfCbqt5Byrup8Q 12 6 1 0 "HDAQ74AEznP-YsMk1B14CA, 6A6-aIX7fg_zRy9MiE6YyQ, G8ceJ1sl4bTa3HpHH30Tsw, vLxpEHKZFzv8LYY5eeGjTg, 82cSkx1JPYncxaEbHw881Q, gJwuQ4UlRBpievm1RczSJA, kyyqWH_txy77lWSU07djaQ, v8nn6KoAX95TM6l8eLwaOg, 8tj9792w3U7lv_XkErMn4A, o4-1TV2DNRiJuP54QnYlLw, SpuK67B0EVHX0DuMEgPq3w, 70WzR6h6CrGVx5Oc6jpsfw, bXmJD9Xcr8H1wgIkjC1Blw, CSsZKyjgef2GS20JtP4W2w, 6gbobg9g8ed_S_LP5MsF-Q, MnfDvdGoM3hIKesuTjdHGA, srlNQdd0pXKdNqyWr35bGA, df3kNNGt5nykGt4Q6MH1ew, vShdkvNEIa_4yt-Fwz_t1A, yKC1QUmOahoNyzsr9PvIaw, glL3jHCYQJnZnYxS3H0rOg, C14KW1jjKM8QlYfMyzP1sQ, rMLNeEH2y5Evz1crWSjoCw" 1 2.75 0 0 0 0 0 0 1 0 0 0
E9kcWJdJUHuTKfQurPljwA 358 399 102 143 "y2GyxJF5VQWohxgw_GR7Jw, 0NRn4eY3JWN0IFqvOSa5gA, 5N3vTkVPocwDkxJKkMDL-w, 1OKQcPJM4VFAOT4zBL5UDw, 5eKI5IE1LhtzUzE_DDWc8A, dQhrG-JrtvuY0bXLdz2xLA" 23 3.73 4 7 2 0 0 8 6 12 12 5
lO1iq-f75hnPNZkTy3Zerg 40 109 40 46 "tOQDlz36rI__SOsbL-HCag, 83Xb0PPBwZiG2c_fLpZgAw, -1O9sBJSdzbOqpTOtxC4fw, XORpp4JbjyVc8gJ1FsrvmA, Y3xvfLidDtRQpOIGlRvGBQ, hbEys0S87pWjVgp1TXf_XA, EOJHBH07oCgLIuTyrNI9iQ, N3b6QoR1GJdzMzGmwg-QHA, AuDcANioqhywKgJZo3SW7A, Muf0VajywqdGmJK2nvaoOw, xpKKjExAycZeaicKKx4PGA, DpFjr68seT8yAi8TPbyFCw, SSnXkwxPIOJsWfB-SkBGFA, arPyZHh5E3zJlCX63b8-sw, 3kwcpzQneZRByfMs3U_K6Q, ZNuyfwJt8vkcncmRz5xecg" 7 4.04 2 0 0 3 0 3 4 5 5 3
AUi8MPWJ0mLkMfwbui27lg 109 154 20 23 "gy5fWeSv3Gamuq9Ox4MV4g, lMr3LWU6kPFLTmCpDkACxg, _5280JO3WzgiVTJUYahCcg, tCqYnhAdQhPO3JAAnc09ig, Hx6Aaw2aOhr-3xSKe7scng, 0n3zQFQZgfdpjG4drF812g, YogWNBUTdnLKB9WfiLYZPw, DLU5G_7fG6VK6ZH8oVJSZQ, 8EXDnMKdEEXbU2AXp3y5-A, Tj8SuJiN4h9WZNs0f2eypg, gbx3IYFKB-36HhV5wM3G1w, rcU7ysY41qGppbw4pQgjqg, 69a9Aygpj-fW3KxnREpaAw, xazq3kc_pbjo7z6pZUwPgw, 5It1pYl-OcpW2xFlZS5-dw, XHrKXZQXWdMFEKrlxdarVw, dccfgKtgbk_WLaC0Mlfjyw, dPzJCGsLOLNozrJ01UnTXQ, PadmV2GEoA6mWpQUpPh7Ig, whKV8TxcoT7NR5jhsCjNKg, beCVDc6aud9eLsP3PZ90ZQ, 2PqFngvKvApLmX5OdxCLyw, oNd5bP0MiB-wBkKZejxOag, piTn3h6zP0ZPLHD3L63VWQ" 4 3.4 0 0 0 0 0 1 6 3 3 0
```

```bash
...
3iJMHpDOXpGbaX_rklDKHA 6 14 3 2 "YVy9RNIlteof_wmrWODsAA, Xchfg03O-NiceW1o_8sSZw" 0 1.67 0 0 0 0 0 0 0 0 0 0
-LjkJ__RkOyn4YtVzDKpRw 1 1 0 0 "Sp6PoyogB3bqfkDzqxanvQ, a51msIBHfDq1uwNHdT9p1A" 0 1.0 0 0 0 0 0 0 0 0 0 0
wzPU_rHcy69MvB0dYLrbmw 5 9 0 2 "3BUQJwPqojO6Hv054hIlkA, s9x_X8z9bFLvuFHvx4AXUw" 1 5.0 0 0 0 0 0 0 1 0 0 1
XfFesxBByhfuOx0myIgrFg 5 1 0 2 "u4RFoKngm1WY1xE0wzoEYg" 0 5.0 0 0 0 0 0 0 0 0 0 0
Se7V87IR_nMflQqKqwTwQA 1 0 0 0 "nrV_SB496NnN9cIvmu2UBg, 7QEolBKTmcVHrGeIpz6OBg, 9K_wlZxhtrQvfD-2r-OJfA, W2ea2DL9K4nXZr_hwFbcYQ, JH36kbU9akchiN8VRxoAYA, O_6w2bZQaEcan9x4vwRzeQ, FTHBAwjlBKXRbJUt7zusHg, FBiPTp4INKnLTHIk61Y76w, zB1iIJKhucXnpH_v6HmdzA, RdOQU7OGKhH6yfhCAfafiQ, HCxPnuXRI6y_bziYYqwXeg, SenJ5oltGS3p-juUxPA-sw, vhj9pLW1OX5UWivMkSI6cA, 1nVYZTxxe3zMctb_Qq7VPg, Y58AqvRy-Qb2ETboxZv4CA, dSvKk7KlsXY25Tz6v4TP_Q" 0 5.0 0 0 0 0 0 0 0 0 0 0
N0UxE9ieEWorUFbO4Z5oXg 6 3 1 3 "OJlWkQf2Jr7NseAbukf1Zg" 0 5.0 0 0 0 0 0 0 0 0 0 0
qjxu5zMArnCETnAmyFt2gA 16 4 0 1 "XCPjfWUQ1ejA71pE3l7CBg" 0 4.31 0 0 0 0 0 0 0 0 0 0
2IKCHHnEe8CggdeEke6P2A 1 0 0 0 "fAYqzlrCKoLZ_8AhsrIgRA" 0 5.0 0 0 0 0 0 0 0 0 0 0
k2hq0Ib5THG_rkeEkonflw 9 5 0 1 "GjgFs1lY7XQzTF16RizX1A" 2 4.11 0 0 0 0 0 0 0 0 0 0
...
```

We only pasted a little part of the entire data cleaning results. The entire the outputs are 24 files containing data like above. For data cleaning, we not only dropped the columns that we no longer need, we also formatted the output strings to be more readable and recognizable for the later databases. 

