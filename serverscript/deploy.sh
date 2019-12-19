cd weather
# 拉取最新代码
git pull

# 编译jar包
mvn clean package

# 拷贝jar包到weather目录
cp target/weather.jar ../

# 用docker-compose 部署更新
docker-compose up --build
