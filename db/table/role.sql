CREATE TABLE role(
    id              INT(9) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    role            INT(2) NOT NULL COMMENT '角色',
    role_desc       VARCHAR(16) NOT NULL COMMENT '角色描述',
    create_date     DATE NOT NULL COMMENT '创建时间'
)