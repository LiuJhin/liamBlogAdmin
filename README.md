# Liam's Blog

基于 Spring Boot 的博客系统后端

## 技术栈

- Spring Boot 3.4.1
- Spring Security
- Spring Data JPA
- MySQL
- Redis
- JWT
- Swagger/OpenAPI
- 阿里云 OSS

## 主要功能

### 1. 管理员认证授权

- 基于 JWT 的无状态认证
- 注册接口: POST `/api/auth/register`
  - 返回用户信息（ID、用户名、头像、性别、身份）和 JWT token
- 登录接口: POST `/api/auth/login`
  - 返回用户信息和 JWT token

### 2. 文章管理

- 创建文章: POST `/api/articles`
  - 支持标题、内容
  - 自动记录作者和创建时间
- 文章列表: GET `/api/articles`
  - 支持分页查询
  - 支持按标题搜索
  - 返回文章详细信息（标题、内容、作者、创建时间等）

### 3. 项目展示

- 项目列表: GET `/api/projects`
  - 支持分页查询
  - 支持按项目名称和标签搜索
  - 返回项目信息（名称、标签、图片、负责内容、开发周期等）

### 4. API 文档

- Swagger UI 访问地址: http://localhost:8080/swagger-ui.html
- OpenAPI 文档: http://localhost:8080/api-docs

### 5. 文件上传

- 支持文件上传到本地或阿里云 OSS
- 最大文件大小: 10MB

## 项目结构

src/main/java/org/example/liamsblog/
├── config/
│ └── SecurityConfig.java # 安全配置
├── controller/
│ └── AuthController.java # 认证控制器
├── dto/
│ ├── AuthResponse.java # 认证响应 DTO
│ ├── LoginRequest.java # 登录请求 DTO
│ └── RegisterRequest.java # 注册请求 DTO
├── entity/
│ └── Admin.java # 管理员实体
├── repository/
│ └── AdminRepository.java # 管理员数据访问
└── service/
└── AuthService.java # 认证服务

## 开发环境要求

- JDK 17+
- MySQL 8.0+
- Redis
- Maven 3.9+

## 快速开始

1. 克隆项目
2. 配置 application.properties
3. 启动 MySQL 和 Redis
4. 运行项目

### 登录

## 错误处理

- 400: 请求参数错误
- 401: 未授权
- 403: 禁止访问
- 404: 资源不存在
- 500: 服务器内部错误

## 安全性

- 使用 Spring Security 进行认证和授权
- 密码加密存储
- JWT token 认证
- 跨域支持
