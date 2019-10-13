CREATE TABLE role_permission(
    id            INT(9) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    role          INT(9) NOT NULL COMMENT '角色id',
    permission    INT(9) NOT NULL COMMENT '权限id',
    create_date   DATE NOT NULL COMMENT '创建时间'
)
COMMENT '角色权限表'