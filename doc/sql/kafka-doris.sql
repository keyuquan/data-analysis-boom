SHOW  ROUTINE LOAD;
SHOW ROUTINE LOAD FOR  ods_ocean_ad_plan_conf;

create routine load doris_boom.ods_trackingio_active on ods_trackingio_active   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_trackingio_active" 
); 

create routine load doris_boom.dwm_ta_event_day_pkg_kpi on dwm_ta_event_day_pkg_kpi   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_ta_event_day_pkg_kpi" 
); 

create routine load doris_boom.dwm_ta_event_day_pkg_retain on dwm_ta_event_day_pkg_retain   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_ta_event_day_pkg_retain" 
); 

create routine load doris_boom.dwm_ta_event_day_pkg_plan_kpi on dwm_ta_event_day_pkg_plan_kpi   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_ta_event_day_pkg_plan_kpi" 
); 

create routine load doris_boom.dwm_ta_event_day_pkg_plan_retain on dwm_ta_event_day_pkg_plan_retain   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_ta_event_day_pkg_plan_retain" 
); 

create routine load doris_boom.dwm_pangle_day_site_ad_unit_kpi on dwm_pangle_day_site_ad_unit_kpi   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_pangle_day_site_ad_unit_kpi" 
); 

create routine load doris_boom.ods_ocean_ad_plan_conf on ods_ocean_ad_plan_conf   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_ods_ocean_ad_plan_conf" 
); 

create routine load doris_boom.dwm_ocean_day_ad_plan_kpi on dwm_ocean_day_ad_plan_kpi   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_ocean_day_ad_plan_kpi" 
); 

create routine load doris_boom.dwm_ocean_day_ad_plan_inventory_kpi on dwm_ocean_day_ad_plan_inventory_kpi   
PROPERTIES (  
    "format"="json",  
    "desired_concurrent_number"="1",  
    "strip_outer_array" ="false",    
    "max_error_number"="1000" 
) 
FROM KAFKA (     
    "kafka_broker_list"= "ta1:9092,ta2,ta3:9092",     
    "kafka_topic" = "boom_dwm_ocean_day_ad_plan_inventory_kpi" 
); 


