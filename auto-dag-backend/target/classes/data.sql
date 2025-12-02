-- 初始化数据脚本
-- 创建默认用户
INSERT INTO users (id, username, email, password, created_at, updated_at) VALUES
(1, 'admin', 'admin@example.com', '$2a$10$8K1p/a0dhrxiowP.dnkgNORTWgdEDHn5L2/xjpEWuC.QQv4rKO9jO', NOW(), NOW());

-- 创建默认角色
INSERT INTO roles (id, name, description, created_at, updated_at) VALUES
(1, 'ROLE_ADMIN', '管理员角色', NOW(), NOW()),
(2, 'ROLE_USER', '普通用户角色', NOW(), NOW());

-- 关联用户和角色
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(1, 2);

-- 创建示例DAG数据
INSERT INTO dag (id, dag_id, name, description, current_version, status, created_by, created_at, updated_at) VALUES
(1, 'sample-dag-001', '示例DAG任务', '这是一个示例DAG任务', '1.0.0', 'ACTIVE', 1, NOW(), NOW());