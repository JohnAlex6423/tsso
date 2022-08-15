CREATE TABLE user_permission(
    id              INT(9) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    permission      INT(2) NOT NULL COMMENT '权限',
    permission_desc VARCHAR(16) NOT NULL COMMENT '权限描述',
    create_date     DATE NOT NULL COMMENT '创建时间'
)