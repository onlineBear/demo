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

  no nvarchar(20) not null,			        -- 用户编码, 唯一
  name nvarchar(40) not null,           -- 用户名
  password nvarchar(40) not null,       -- 密码
  email nvarchar(60) not null,          -- 邮箱
  mobile nvarchar(11) not null,         -- 手机号
  profilePhoto nvarchar(100) not null,     -- 头像url
  personalStatement nvarchar(100) not null,		-- 个性介绍

  lastUpdateTime datetime not null,

  constraint pk_SysUser primary key(id)
)
go

create unique index uk_SysUser on SysUser(no)
go

