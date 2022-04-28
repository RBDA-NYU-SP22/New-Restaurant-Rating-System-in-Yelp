 use tt2370;
 create external table if not exists filtered_business_data(json string);
 load data inpath 'hdfs://horton.hpc.nyu.edu:8020/user/tt2370/project/filtered_data/filtered_business_data.json' overwrite into table filtered_business_data;
 select * from filtered_business_data limit 100;

 select get_json_object(json, '$.state') as state, count(distinct get_json_object(json, '$.business_id')) as cnt from filtered_business_data group by get_json_object(json, '$.state') order by cnt desc;

 select count(distinct get_json_object(json, '$.business_id')) as cnt from filtered_business_data;

 select (ceiling(get_json_object(json, '$.review_count')/50)*50) as review_category, count(distinct get_json_object(json, '$.business_id')) as cnt from filtered_business_data group by ceiling(get_json_object(json, '$.review_count')/50) order by cnt desc;