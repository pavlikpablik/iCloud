更新字段名	alter table TABLE_NAME rename column column_old to column_new;
添加字段	alter table TABLE_NAME add COLUMN_NAME varchar(10);
删除字段	alter table TABLE_NAME drop column COLUMN_NAME;
添加字段并附值	alter table TABLE_NAME ADD COLUMN_NAME NUMBER(1) DEFAULT 1;
修改字段值	update TABLE_NAME set filedname=value where filedname=value;
修改字段数据类型	alter table tablename modify filedname varchar2(20);

update t_sys_user set password ='4a13cbcb9f901715' where login_name='icloud'