CREATE TABLE user_role(
    id          INT(9) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id     INT(9) NOT NULL COMMENT '用户id',
    role        INT(2) NOT NULL COMMENT '角色',
    create_date DATE NOT NULL COMMENT '创建时间'
)