# 接口：getCourseInfo  [返回](../README.md)
用例： [选择课程](../用例/选择课程.md)

- 功能：
    查看课程信息。
    
- 权限：
    学生/老师：查看可选择的课程或已选的课程，必须先登录。    
    
- API请求地址： 
    接口基本地址/v1/api/getCourseInfo/<school_year>

- 请求方式 ：
    GET
      
- 请求参数说明:        

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |school_year|课程所在学年|
  
- 返回实例：

        {         
            "status": true,
            "info": null,
            "data": [
                {"course_ID": "52475757",
                "course_name": "信息系统",
                "course_hour": "总课时"
                "teachername":"林菀"
                "choice_ID":"35454541"
                "school_year":"2017-2018-2"
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
  |teachername|选课老师姓名|
  |choice_ID|选课记录ID|
  |school_year|学年|