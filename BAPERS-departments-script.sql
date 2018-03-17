use bapers_data;

insert into department(department_code,department_name) values('CR','copy room'),('DR','dark room'),('DA','development area'),('PR','printing room'),('FR','finishing room'),('PD','packaging department');

ALTER TABLE task
MODIFY  COLUMN duration_min smallint;