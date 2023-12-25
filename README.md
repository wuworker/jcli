# 命令行工具
要求java17
* jdate：日期相关
* jenc：url编解码
* json：json相关
* jrdm：生成随机数

## 安装
### 1. 执行maven install安装命令到本地
```shell
mvn install
```
* 默认安装目录：~/.jcli
* 如果默认的JAVA_HOME版本不是17，需要在安装目录的bin文件夹下，新增java-env.sh文件，声明java17的家目录
```shell
export JAVA_HOME=
```

### 2.声明环境变量

往本地profile文件写入jcli的可执行文件路径
```shell
export JCLI_HOME=安装目录
export PATH="$PATH:$JCLI_HOME/bin"
```

### 3. 使用命令

