﻿
# 基于GitHub的课程实验管理平台的分析与设计

### 成都大学信息科学与工程学院

|学号|班级|姓名|照片|
|:-------:|:-------------: | :----------:|:---:|
|201510414420|软件(本)15-4|吴靖|![flow1](../jing.jpg)|

## 1. 概述
- 基于GitHub的课程实验管理平台的作用是在线管理实验成绩的Web应用系统。学生和老师的实验内容均存放在GitHUB
页面上。
- 学生的功能主要有：设置自己的GitHub用户名，查询自己的实验成绩，选择课程，查看和退选已选择的课程，。学生的GitHub用户名是公开的，但成绩不公开。
- 老师的功能主要有：除了学生的主要功能，还有增添实验，删除实验，查询实验，评定学生成绩。
- 管理员的功能主要有：查询用户，增加用户，冻结用户，增加课程和删除课程。
- 老师和学生都能通过本系统的链接方便地跳转到学生的每个GitHUB实验目录，以便批改实验或者查看实验情况。
- 实验成绩按数字分数计算，每项实验的满分为100分，最低为0分。
- 实验成绩是使用实验的评分项及其分数占比来计算最终成绩，若没有输入百分比，则按平均数来计算。
- 学生和老师都可以选课，但是学生只能选老师选过的科目。
    
## 2. 系统总体结构
![](系统总体结构.png)

界面设计参见：https://zwdbox.github.io/is_analysis/test6/ui/index.html
    
## 3. 用例图设计 [源码](src/UseCase.puml)
![](UseCase.png)

## 4. 类图设计 [源码](src/class.puml)
![](./class.png)

## 5. 数据库设计
- ### [参见数据库设计](数据库设计.md)

## 6. 用例及界面详细设计
- ### [“选择课程”用例](./用例/选择课程.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/选择课程.html)
- ### [“查看成绩”用例](./用例/查看成绩.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查看成绩.html)
- ### [“学生列表”用例](./用例/学生列表.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/index.html)
- ### [“查看已选课程”用例](./用例/查看已选课程.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查看已选课程.html)
- ### [“退选已选课程”用例](./用例/退选已选课程.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查看已选课程.html)

- ### [“评定成绩”用例](./用例/评定成绩.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/评定成绩.html)
- ### [“查询实验”用例](./用例/查询实验.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查询实验.html)
- ### [“增加实验”用例](./用例/增加实验.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/设置实验信息.html)
- ### [“删除实验”用例](./用例/删除实验.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查询实验.html)
- ### [“编辑实验信息”用例](./用例/编辑实验信息.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/设置实验信息.html)

- ### [“查询用户”用例](./用例/查询用户.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查询用户.html)
- ### [“增加老师用户”用例](./用例/增加老师用户.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/增加用户.html)
- ### [“增加学生用户”用例](./用例/增加学生用户.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/增加用户.html)
- ### [“冻结用户”用例](./用例/冻结用户.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查询用户.html)
- ### [“查询课程”用例](./用例/查询课程.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查询课程.html)
- ### [“增加课程”用例](./用例/增加课程.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/增加课程.html)
- ### [“删除课程”用例](./用例/删除课程.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/查询课程.html)

- ### [“修改密码”用例](./用例/修改密码.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/顶部菜单.html)
- ### [“修改用户信息”用例](./用例/修改用户信息.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/顶部菜单.html)
- ### [“查看用户信息”用例](./用例/查看用户信息.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/顶部菜单.html)
- ### [“登出”用例](./用例/登出.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/顶部菜单.html)
- ### [“登录”用例](./用例/登录.md),[界面](https://ikowalski.github.io/is_analysis/test6/ui/登录.html)