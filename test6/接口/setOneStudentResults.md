# 接口：setOneStudentResults  [返回](../README.md)
用例： [评定成绩](../用例/评定成绩.md)

- 功能：
    设置一个学生的部分实验成绩和评语。
    
- 权限：
    老师：可以查看所有学生的成绩。
    
- API请求地址： 
    接口基本地址/v1/api/setOneStudentResults

- 请求方式 ：
    POST
 
- 请求实例：  

        { 
            "student_id": "20101111", 
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

- 请求参数说明:       
 
  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|      
  |student_ID|学生ID|
  |course_ID|课程ID|
  |exper_ID|实验ID|
  |comment|老师对该实验的评价|
  |score|实验的最终成绩|
  |data|该实验所有的评分项|
  |element_ID|评分项ID|
  |element_name|评分项名称|
  |persent|评分项占百分比|
 
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