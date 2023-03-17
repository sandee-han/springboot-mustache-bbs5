select hospital_name, road_name_address
from bbs.nation_wide_hospitals
where road_name_address like "경기도 수원시%"
  and hospital_name like "%피부과%"


SELECT business_type_name, hospital_name, road_name_address
FROM bbs.nation_wide_hospitals
where business_type_name in ('보건소', '보건지소');


-- 병상 10개 이상 20개 미만
SELECT hospital_name, patient_room_count FROM bbs.nation_wide_hospitals
where patient_room_count between 10 and 19
order by patient_room_count desc;