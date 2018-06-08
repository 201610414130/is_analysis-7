# 接口：getCourseChosen  [返回](../README.md)
用例： [查看已选课程](../用例/查看已选课程.md)

- 功能：
    学生/老师查看已选择课程。
    
- 权限：
    学生/老师：查看已选择课程，必须先登录。    
    
- API请求地址： 
    接口基本地址/v1/api/getCourseChosen/<user_id>/<school_year>

- 请求方式 ：
    GET
 
  - 请求参数说明:    
  
  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |user_id|用户ID|
  |school_year|学年|
  
- 返回实例：

        {         
            "status": true,
            "info": null,
            "data": [
                {"course_ID": "52475757",
                "course_name": "信息系统",
                "course_hour": "总课时"
                "isChosen ":false
                {
                ...其他课程
                }
            ]
        }
 
- 返回参数说明：    
 
  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |status|bool类型，true表示正确的返回，false表示有错误|
  |info|返回结果说明信息|
  |data|课程信息|
  |course_ID|课程ID|  
  |course_name|课程名称|
  |course_hour|课时|
  |isChosen|bool类型，true表示该用户已选择该课程，false表示未选择|