/*
create database demo
go
*/

use demo
go

-- 用户表
/*
if object_id('SysUser', 'U') is not null
  drop table SysUser
go
*/

create table SysUser
(
  id numeric(19) not null,
  lastUpdateTime datetime not null,

  no nvarchar(20) not null,			        -- 用户编码, 唯一
  name nvarchar(40) not null,           -- 用户名
  password nvarchar(40) not null,       -- 密码
  mobile nvarchar(11) not null,         -- 手机号

  constraint pk_SysUser primary key(id)
)
go

create unique index uk_SysUser on SysUser(no)
go
