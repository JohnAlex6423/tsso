CREATE TABLE user_detail(
    id        INT(9)      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id   INT(9)      NOT NULL COMMENT '用户id',
    nike_name VARCHAR(16) NOT NULL COMMENT '昵称',
    avatar    VARCHAR(64) NOT NULL COMMENT '头像'
)
COMMENT '用户资料表'