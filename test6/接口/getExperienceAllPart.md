# 接口：getExperienceAllPart  [返回](../README.md)
用例： [编辑实验信息](../用例/编辑实验信息.md),[查询实验](../用例/查询实验.md)

- 功能：
    查看实验信息。

- 权限：
    学生/老师：查看已创建的实验信息。

- API请求地址：
    接口基本地址/v1/api/getExperienceAllPart/<exper_ID>

- 请求方式 ：
    GET

- 请求参数说明:

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|
  |exper_ID|实验ID|

- 返回实例：

        {
            "status": true,
            "info": null,
            "exper_ID": "92425153",
            "exper_name": "实验一",
            "exper_address": "test1"
            "data": [
                {"element_ID": "57525785",
                "element_name": "评分项一",
                "persent": "20"
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
  |exper_ID|实验ID|
  |exper_name|实验名称|
  |exper_address|实验github地址|
  |data|评分项信息|
  |element_ID|评分项ID|
  |element_name|评分项名称|
  |persent|评分项百分比|