create table users (
  id BIGINT(20) NOT NULL auto_increment,
  username VARCHAR(30) not null COMMENT '用户名',
  password varchar(200) not null comment '密码',
  email varchar(50) null default null comment '邮箱',
  phone varchar(20) null default null comment '电话',
  status varchar(20) not null default 'ENABLE' comment '状态',
  remark varchar(200) null comment '备注',
  PRIMARY KEY (id)
);

create table groups(
  id BIGINT(20) NOT NULL auto_increment,
  name VARCHAR(30) NOT NULL,
  remark varchar(200) null comment '备注',
  PRIMARY KEY (id)
);

create table user_group(
  id BIGINT(20) NOT NULL auto_increment,
  user_id BIGINT(20) NOT NULL,
  group_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);


create table roles(
  id BIGINT(20) NOT NULL auto_increment,
  code VARCHAR(30) NOT NULL comment '角色代码',
  name VARCHAR(30) NOT NULL comment '角色名称',
  status varchar(20) not null default 'ENABLE' comment '状态',
  type varchar(10) not null comment '类型',
  remark varchar(200) null comment '备注',
  PRIMARY KEY (id)
);

create table user_role(
  id BIGINT(20) NOT NULL auto_increment,
  user_id BIGINT(20) NOT NULL,
  role_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);

create table group_role(
  id BIGINT(20) NOT NULL auto_increment,
  group_id BIGINT(20) NOT NULL,
  role_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);


create table permissions(
  id BIGINT(20) NOT NULL auto_increment,
  name VARCHAR(30) NOT NULL,
  code VARCHAR(30) NOT NULL,
  remark varchar(200) null comment '备注',
  status varchar(20) not null default 'ENABLE' comment '状态',
  type varchar(10) not null comment '类型',
  PRIMARY KEY (id)
);

create table role_permission(
  id BIGINT(20) NOT NULL auto_increment,
  role_id BIGINT(20) NOT NULL,
  permission_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);

create table actions(
  id BIGINT(20) NOT NULL auto_increment,
  name VARCHAR(30) NOT NULL,
  code VARCHAR(30) NOT NULL,
  remark varchar(200) null comment '备注',
  status varchar(20) not null default 'ENABLE' comment '状态',
  PRIMARY KEY (id)
);

create table permission_action(
  id BIGINT(20) NOT NULL auto_increment,
  permission_id BIGINT(20) NOT NULL,
  action_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);

create table resources(
  id BIGINT(20) NOT NULL,
  name VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  code VARCHAR(30) NOT NULL,
  remark varchar(200) null comment '备注',
  status varchar(20) not null default 'ENABLE' comment '状态',
  PRIMARY KEY (id)
);

create table permission_resource(
  id BIGINT(20) NOT NULL auto_increment,
  permission_id BIGINT(20) NOT NULL,
  resource_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);

