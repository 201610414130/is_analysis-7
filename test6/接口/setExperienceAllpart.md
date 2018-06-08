# 接口：setExperienceAllPart  [返回](../README.md)
用例： [增加实验](../用例/增加实验.md),[编辑实验信息](../用例/编辑实验信息.md)

- 功能：
    查看实验信息。

- 权限：
    老师：修改/创建实验及其评分项。

- API请求地址：
    接口基本地址/v1/api/setExperienceAllPart

- 请求方式 ：
    POST

- 请求实例：

        {
            "exper_ID": "92425153",
            "exper_name": "实验一",
            "exper_address": "test1",
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
  |exper_ID|实验ID，新建时为空|
  |exper_name|实验名称|
  |exper_address|实验github地址|
  |data|评分项信息|
  |element_ID|评分项ID，新建时为空|
  |element_name|评分项名称|
  |persent|评分项百分比|

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