# Stage 1: Build
FROM node:latest as build
RUN mkdir -p /app
WORKDIR /app

COPY Web/bankclientsrecommender/package.json /app/package.json
RUN npm install
COPY Web/bankclientsrecommender /app
RUN npm run build

# Stage 2: Serve using Nginx
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/bankclientsrecommender /usr/share/nginx/html