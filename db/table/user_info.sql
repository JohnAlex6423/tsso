CREATE TABLE user_info (
    id           INT(9) NOT NULL COMMENT 'id' PRIMARY KEY AUTO_INCREMENT,
    username     varchar(16) NOT NULL COMMENT '用户名',
    email        varchar(32) NOT NULL COMMENT '邮箱',
    password     varchar(64) NOT NULL COMMENT '密码',
    salt         varchar(16) NOT NULL COMMENT '盐',
    create_date  DATE NOT NULL COMMENT '创建时间'
)
COMMENT '用户表'