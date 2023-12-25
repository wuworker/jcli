#!/bin/bash

#自定义java家目录
CUSTOM_JAVA_HOME=
# 安装目录
INSTALL_HOME=~/.jcli
# 执行脚本
RUN_FILE=install/bin/run.sh

if [ ! -d "install/bin" ];then
  mkdir $1
fi

# 生成run.sh
echo "#!/bin/bash" > $RUN_FILE
if [[ -n "$CUSTOM_JAVA_HOME" ]];then
  echo "JAVA_HOME=$CUSTOM_JAVA_HOME" >> $RUN_FILE
fi
echo "\$JAVA_HOME/bin/java -jar \$JAR_FILE \"\$@\"" >> $RUN_FILE

if [ -d $INSTALL_HOME ];then
  rm -rf $INSTALL_HOME
else
  mkdir $INSTALL_HOME
fi

cp -r ./install/bin $INSTALL_HOME
cp -r ./install/lib $INSTALL_HOME

echo "Install Success"
