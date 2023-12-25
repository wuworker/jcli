#!/bin/bash
# mvn install时执行

# 安装家目录
JCLI_HOME=$1
if [ -z $JCLI_HOME ];then
  JCLI_HOME=~/.jcli
fi
if [ ! -d "$JCLI_HOME" ];then
  mkdir $JCLI_HOME
fi

# 复制可执行文件
if [ ! -d "$JCLI_HOME/bin" ];then
  mkdir $JCLI_HOME/bin
fi
cp -rf target/bin/* $JCLI_HOME/bin

# 复制lib包
if [ ! -d "$JCLI_HOME/lib" ];then
  mkdir $JCLI_HOME/lib
fi
JAR_FILE=$(ls target|grep jar)
cp -f target/$JAR_FILE $JCLI_HOME/lib

# 复制依赖文件
if [ ! -d "$JCLI_HOME/lib/ext" ];then
  mkdir $JCLI_HOME/lib/ext
fi
cp -rf target/ext/* $JCLI_HOME/lib/ext
