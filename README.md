# 命令行工具


## 安装
### 1. 执行maven install安装命令到本地
```shell
mvn install
```
默认安装目录：~/.jcli

### 2.声明环境变量

往本地profile文件写入环境变量声明
```shell
export JCLI_HOME=安装目录
export PATH="$PATH:$JCLI_HOME/bin"
```

### 3. 使用命令

