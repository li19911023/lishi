该框架core可以直接进行依赖

主要的功能是对单表的增删改查进行优化,前端按照一定格式的json数据进行传递即可

并集成了swagger-ui工具,但是swagger只能用于json的传递,不能再请求头中加入数据,如需要在请求头中加数据,可以使用postman工具
简化了重复代码和后期维护成本,后期维护只要是处理好各个实体类的关系即可
处理异常信息,并返回给前端(这个可以自己自定义设置)
我自己写的redisUtils工具类等
