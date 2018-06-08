# 接口：getStudents  [返回](../README.md)
用例： [学生列表](../用例/学生列表.md),[查看成绩](../用例/查看成绩.md)

- 权限：
    学生/访客：不能看到score_array
    老师：可以看到score_array

- 功能：
    返回所有学生的列表及其已选课程的各个实验成绩

- API请求地址：
   接口基本地址/v1/api/getStudents

- 请求方式 ：
    GET

- 请求参数说明:
    无

- 返回实例：

        {
            "status": true,
            "info": null,
            "total": 132,
            "data": [
                {"school_year": "2017-2018-2",
                "course_name": "信息系统",
                "student_ID": "20101111",
                "username": "张三",
                "class": "15软件工程3班",
                "githubname": "threezhang",
                "score_list":"76,-1,-1,-1,-1",
                "course_ID":"83421423",
                "exper_ID_list":"43525653,54725762,97536256,98146754,13478635"},
                {
                ...其他学生
                }
            ]
        }

- 返回参数说明：

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|
  |status|bool类型，true表示正确的返回，false表示有错误|
  |info|返回结果说明信息|
  |total|返回学生人数|
  |data|学生信息|
  |school_year|该门课程开设的学年|
  |course_name|课程名称|
  |student_ID|学生ID|
  |username|用户真实姓名|
  |class|该学生所在班级|
  |githubname|该用户的Github的用户名|
  |score_list|学生在某门课程里的实验成绩汇总|
  |course_ID|该门课程ID|
  |exper_ID_list|该门课程的实验ID汇总|