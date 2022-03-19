# 1、用户

查看当前用户的缺省表空间

```sql
select username,default_tablespace from user_users;
```

查看当前用户的角色

```sql
select * from user_role_privs;
```

查看当前用户的系统权限和表级权限

```sql
select * from user_sys_privs;

select * from user_tab_privs;
```

显示当前会话所具有的权限

```sql
select * from session_privs;
```

显示指定用户所具有的系统权限

```sql
select * from dba_sys_privs where grantee='GAME';
```



# 2、表

查看用户下所有的表

```sql
select * from user_tables;
```

查看名称包含log字符的表

```sql
select object_name,object_id from user_objects where instr(object_name,'LOG')>0;
```

查看某表的创建时间

```sql
select object_name,created from user_objects where object_name=upper('table_name');
```

查看某表的大小

```sql
select sum(bytes)/(1024*1024) as "size(M)" from user_segments where segment_name=upper('table_name');
```

查看放在ORACLE的内存区里的表

```sql
select table_name,cache from user_tables where instr(cache,'Y')>0;
```

# 3、索引

查看索引个数和类别

```sql
select index_name,index_type,table_name from user_indexes order by table_name;
```

查看索引被索引的字段

```sql
select * from user_ind_columns where index_name=upper('index_name');
```

查看索引的大小

```sql
select sum(bytes)/(1024*1024) as "size(M)" from user_segments where segment_name=upper('index_name');
```

# 4、序列号

查看序列号，last_number是当前值

```sql
select * from user_sequences;
```

# 5、视图

查看视图的名称

```sql
select view_name from user_views;
```

查看创建视图的select语句

```sql
set view_name,text_length from user_views;

set long 2000; 说明：可以根据视图的text_length值设定set long 的大小

select text from user_views where view_name=upper('&view_name');
```



# 6、同义词

查看同义词的名称

```sql
select * from user_synonyms;
```

# 7、约束条件

查看某表的约束条件

```sql
select constraint_name, constraint_type,search_condition, r_constraint_name from user_constraints where table_name = upper('&table_name');

select c.constraint_name,c.constraint_type,cc.column_name from user_constraints c,user_cons_columns cc where c.owner = upper('&table_owner') and c.table_name = upper('&table_name') and c.owner = cc.owner and c.constraint_name = cc.constraint_name order by cc.position;
```



# 8、存储函数和过程

查看函数和过程的状态

```sql
select object_name,status from user_objects where object_type='FUNCTION';

select object_name,status from user_objects where object_type='PROCEDURE';
```



查看函数和过程的源代码

```sql
select text from all_source where owner=user and name=upper('&plsql_name');
```







# 9、导入DMP文件



```java
imp NYBXXZSP/123456@localhost:1521/orcl  full=y  file=G:\xzsp\nybxzsp-hd.dmp ignore=y
```

