1. 将此文件夹整个复制到某目录下

2. 进入 `environment` 目录

3. 为 `entrypoint.sh` 文件赋予执行的权限，使用 `chmod +x entrypoint.sh` 命令

4. 修改 `storage.conf` 配置文件：

   ```properties
   tracker_server=主机IP:22122
   http.server_port=文件下载端口
   ```

5. 修改 `client.conf` 配置文件：

   ```properties
   tracker_server=主机IP:22122
   ```

6. 修改 `mod_fastdfs.conf` 配置文件：

   ```properties
   tracker_server=主机IP:22122
   ```

7. 修改 `nginx.conf` 配置文件：

   ```tex
   user  root;
   worker_processes  1;
   
   events {
       worker_connections  1024;
   }
   
   http {
       include       mime.types;
       default_type  application/octet-stream;
   
       sendfile        on;
   
       keepalive_timeout  65;
   
       server {
           listen       文件下载端口;
           server_name  localhost;
   
           location ~/group([0-9])/M00 {
               ngx_fastdfs_module;
           }
   
           error_page   500 502 503 504  /50x.html;
           location = /50x.html {
               root   html;
           }
       }
   }
   ```

8. 启动容器，返回上一层目录：

   ```shell
   docker-compose up -d
   ```

9. 测试上传

   1. 交互式进入容器

      ```text
      docker exec -it fastdfs /bin/bash
      ```

   2. 测试文件上传

      ```text
      /usr/bin/fdfs_upload_file /etc/fdfs/client.conf /usr/local/src/fastdfs-5.11/INSTALL
      ```

   3. 服务器反馈上传地址

      ```text
      ******
      ```

   4. 访问

      ```text
      http://主机地址:文件下载端口/文件地址（******）
      ```