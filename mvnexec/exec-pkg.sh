#!/bin/bash
# mvn package时执行

FILE=$(ls target|grep jar)
CMD=$(echo $FILE| awk -F ""-"" '{print $1}')

if [ ! -d "target/bin" ];then
  mkdir target/bin
fi

# 生成执行脚本
TEMPLATE_SH=$(dirname "$0")/jcli-template
cp $TEMPLATE_SH target/bin/$CMD

# 替换jar文件名称
sed -i '' "s/__need_replace_jarfile__/$FILE/" target/bin/$CMD

chmod 755 target/bin/$CMD