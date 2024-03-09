项目采用springboot3 + springSecurity6+jwt作为基础框架以及登录权限校验

jdk采用17+版本

1.0.0版本


注意！！！
所有表实体类必须在com.aiplusplus.favorites.doman.entity包下并且引入mybatisplus的TableName注解，否则项目启动无法校验实体对应的表是否存在