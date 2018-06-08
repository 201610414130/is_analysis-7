# 接口：getUserBasicInfo  [返回](../README.md)
用例： [查询用户](../用例/查询用户.md)

- 功能：
    查看用户的基本信息。
    
- 权限：
    管理员：查看用户的基本信息，需先登录。    
    
- API请求地址： 
    接口基本地址/v1/api/getUserBasicInfo/<permission>/<search>

- 请求方式 ：
    GET
      
- 请求参数说明:        

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |permission|账户的权限，1代表老师，2代表学生|
  |search|搜索内容|
  
- 返回实例：

        {         
            "status": true,
            "info": null,
            "data": [
                {"ID": "104733",
                "user_ID":"32475798"
                "username": "凝露",
                "githubname": "ningluingithub"
                "location ":"辗转部",
                "disable ":false
                {
                ...其他用户
                }
            ]
        }
 
- 返回参数说明：    
 
  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |status|bool类型，true表示正确的返回，false表示有错误|
  |info|返回结果说明信息|
  |ID|学号或者工号|
  |user_ID|用户ID，对应表User.user_ID的值|
  |username|用户的真实姓名|  
  |location|班级或者部门名称|
  |githubname|gitHub用户名|
  |permission|用户权限，1为老师，2为学生|