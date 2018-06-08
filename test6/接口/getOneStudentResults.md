# 接口：getOneStudentResults  [返回](../README.md)
用例： [评定成绩](../用例/评定成绩.md),[查看成绩](../用例/查看成绩.md)

- 功能：
    获取一个学生的一个实验的成绩评分项得分和实验评价。
    
- 权限：    
    学生：只能查看自己的成绩，即接口参数student_ID必须等于登录学生的student_ID
    老师：可以查看所有学生的成绩。
    
- API请求地址： 
    接口基本地址/v1/api/getOneStudentResults/<student_ID>/<course_ID>/<exper_ID>/<school_year>/<class>

- 请求方式 ：
    GET

- 请求参数说明:        

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|
  |student_ID|学生ID|
  |course_ID|课程ID|
  |exper_ID|实验ID|
  |school_year|学年|
  |class|班级|
    
- 返回实例：

        {         
            "status": true,
            "info": null,    
            "student_id": "20101111"，
            "school_year": "2017-2018-2",
            "username": "张三",
            "class": "15软件工程3班",
            "githubname": "threezhang",
            "is_workin_github":"true",
            "course_ID":"83421423",
            "exper_ID":"43525653",
            "comment":"（老师评语）",
            "score":"79",
            "data": [
                            {
                            "element_ID":46733465,
                            "element_name": 评分项1, 
                            "persent": 30
                            }, 
                            {
                            ...其他评分项
                            }
                        ] 
        }
 
- 返回参数说明：    
 
  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |status|bool类型，true表示正确的返回，false表示有错误|
  |info|返回结果说明信息|
  |student_ID|学生ID|
  |school_year|该门课程开设的学年|
  |username|用户真实姓名|
  |class|该学生所在班级|
  |githubname|该用户的Github的用户名|
  |is_workin_github|bool类型，true表示该实验的github地址可访问，false表示不可访问|
  |course_ID|课程ID|
  |exper_ID|实验ID|
  |comment|老师对该实验的评价|
  |score|实验的最终成绩|
  |data|该实验所有的评分项|
  |element_ID|评分项ID|
  |element_name|评分项名称|
  |persent|评分项占百分比|