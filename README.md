# DayCare 
Team project of CSYE6200 in NEU

## 如何运行前端
1. 安装node.js
```shell script
# Windows 
# 1. [推荐]
https://nodejs.org/zh-cn/download/package-manager/#windows
# Mac
# 1. curl
curl "https://nodejs.org/dist/latest/node-${VERSION:-$(wget -qO- https://nodejs.org/dist/latest/ | sed -nE 's|.*>node-(.*)\.pkg</a>.*|\1|p')}.pkg" > "$HOME/Downloads/node-latest.pkg" && sudo installer -store -pkg "$HOME/Downloads/node-latest.pkg" -target "/"
# 2. brew
brew install node
# 3. [推荐]
https://nodejs.org/zh-cn/#home-downloadhead
```
2. [可选 && 推荐]安装yarn
```shell script
npm install -g yarn --registry=https://registry.npm.taobao.org
# [推荐]
yarn config set registry https://registry.npm.taobao.org -g
yarn config set sass_binary_site http://cdn.npm.taobao.org/dist/node-sass -g
``` 
3. 安装依赖
```shell script
# 终端中进入web-fe目录下
cd (填自己的文件夹路径并除去两个括号)/web-fe
yarn (或者 npm install)

# 在IDEA中安装依赖（不推荐，网上自己搜）
```
4. 启动
```shell script
# 终端中进入web-fe目录下
cd (填自己的文件夹路径并除去两个括号)/web-fe
yarn start
```

### Basic Usage
//TODO
