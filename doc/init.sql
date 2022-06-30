-- 用户表
CREATE TABLE `sys_user` (
  `id` char(36) NOT NULL COMMENT 'ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `forbidden` char(1) DEFAULT NULL COMMENT '禁用。1启用 2禁用',
  `create_id` char(36) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` char(36) DEFAULT NULL COMMENT '修改者ID',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';


-- 角色表
CREATE TABLE `sys_role` (
  `id` char(36) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(50) DEFAULT NULL COMMENT '代码',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `sort` decimal(65,10) DEFAULT NULL COMMENT '排序',
  `role_type` char(1) DEFAULT NULL COMMENT '类型。1 root角色  2创建',
  `create_id` char(36) DEFAULT NULL COMMENT '创建者ID',
  `create_name` varchar(50) DEFAULT NULL COMMENT '创建者姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` char(36) DEFAULT NULL COMMENT '修改者ID',
  `update_name` varchar(50) DEFAULT NULL COMMENT '修改者姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 用户角色表
CREATE TABLE `sys_user_role` (
  `id` char(36) NOT NULL COMMENT 'ID',
  `user_id` char(36) DEFAULT NULL COMMENT '用户ID',
  `role_id` char(36) DEFAULT NULL COMMENT '角色ID',
  `create_id` char(36) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- 权限表
CREATE TABLE `sys_permission` (
  `id` char(36) NOT NULL COMMENT 'ID',
  `permission_type` char(1) DEFAULT NULL COMMENT '权限类型。 1目录 2接口',
  `parent_id` char(36) DEFAULT NULL COMMENT '父ID。根几点ID默认“-1”',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL',
  `code` varchar(200) DEFAULT NULL COMMENT '代码',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `sort` decimal(65,10) DEFAULT NULL COMMENT '排序',
  `create_id` char(36) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` char(36) DEFAULT NULL COMMENT '修改者ID',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';


-- 角色权限表
CREATE TABLE `sys_role_permission` (
  `id` char(36) NOT NULL COMMENT 'ID',
  `role_id` char(36) DEFAULT NULL COMMENT '角色ID',
  `permission_id` char(36) DEFAULT NULL COMMENT '权限ID',
  `create_id` char(36) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

