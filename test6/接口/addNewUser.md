# 接口：addNewUser  [返回](../README.md)
用例： [增加学生用户](../用例/增加学生用户.md),[增加老师用户](../用例/增加老师用户.md)

- 功能：
    增加新用户。

- 权限：
    管理员：增加学生/老师用户。

- API请求地址：
    接口基本地址/v1/api/addNewUser

- 请求方式 ：
    POST

- 请求实例：

        {
            "permission": 1,
            "ID":"104733"
            "username": "凝露",
            "githubname": "ningluingithub",
            "location": "辗转部"
        }

- 请求参数说明:

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |permission|增加账户的权限，1代表老师，2代表学生|
  |ID|学生学号或老师工号|
  |username|location|
  |githubname|github用户名|
  |location|老师部门或学生班级|

- 返回实例：

        {         
            "status": true,
            "info": null
        }

- 返回参数说明：    
 
  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |status|bool类型，true表示正确的返回，false表示有错误|
  |info|返回结果说明信息|