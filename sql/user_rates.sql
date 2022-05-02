user xw2788;
create external table user_rate_data (user_id string, review_count int, rate double, friends int, fans int, average_stars double) ROW FORMAT DELIMITED FIELDS TERMINATED BY ';';
load data inpath '/user/xw2788/project/output/result' overwrite into table user_rate_data;
select * from user_rate_data limit 0,5;