# 使用官方Node.js运行时作为基础镜像
FROM node:18-alpine AS build

# 设置维护者信息
LABEL maintainer="autodag-team@example.com"

# 设置工作目录
WORKDIR /app

# 复制package.json和package-lock.json
COPY package*.json ./

# 安装依赖
RUN npm ci --only=production

# 复制源代码
COPY . .

# 构建生产版本
RUN npm run build

# 使用nginx作为生产服务器
FROM nginx:alpine

# 设置维护者信息
LABEL maintainer="autodag-team@example.com"

# 复制构建产物到nginx目录
COPY --from=build /app/dist /usr/share/nginx/html

# 复制nginx配置文件
COPY nginx.conf /etc/nginx/nginx.conf

# 暴露端口
EXPOSE 80

# 启动nginx
ENTRYPOINT ["nginx", "-g", "daemon off;"]