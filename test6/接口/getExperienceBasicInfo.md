# 接口：getExperienceBasicInfo  [返回](../README.md)
用例： [查看用户信息](../用例/查看用户信息.md)

- 功能：
    查看实验信息。

- 权限：
    学生/老师：查看已创建的实验信息。

- API请求地址：
    接口基本地址/v1/api/getExperienceBasicInfo/<user_ID>/<school_year>

- 请求方式 ：
    GET

- 请求参数说明:

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|
  |user_ID|用户ID|
  |school_year|实验所在学年|

- 返回实例：

        {
            "status": true,
            "info": null,
            "data": [
                {"exper_ID": "92425153",
                "exper_name": "实验一",
                "exper_address": "test1"
                {
                ...其他实验
                }
            ]
        }

- 返回参数说明：

  |参数名称|说明|
  |:---------:|:--------------------------------------------------------|
  |status|bool类型，true表示正确的返回，false表示有错误|
  |info|返回结果说明信息|
  |data|实验信息|
  |exper_ID|实验ID|
  |exper_name|实验名称|
  |exper_address|实验github地址|