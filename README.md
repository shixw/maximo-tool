# Maximo 工具集合
主要包括以下功能：
- **Maximo功能迁移** 

## Maximo功能迁移
- **数据库配置** ：导入导出数据库配置相关内容；
- **域迁移** ： 导出域相关的配置
- **应用程序设计器** ：导入导出应用程序设计器相关内容；
- **消息** ：导入导出消息相关内容；

### 数据库配置
主要功能有两个：
1. 将指定的数据库配置中的对象导出为归档文件；
2. 将导出的归档文件导入到指定的系统中并自动配置数据库变更。

### 域迁移
支持导出导入域主表，字母数字域 对应的 alndomain 表

### 应用程序设计器
主要功能有两个：
1. 将指定的应用程序设计器中的应用程序导出为归档文件；
2. 将导出的归档文件导入到指定的系统中包括导入系统的XML。

导出模块同时导出模块对应的二级菜单,导入的时候首先删除原先的菜单 然后再导入
导出 系统 XML中增加的内容  

## 参数说明
- **-option**：需要执行什么操作，包括： 1. exportdbconfig (导出数据库配置) ; 2. importdbconfig (导入数据库配置) ; 3. exportdomainadm(导出域配置) ; 4. importdomainadm (导入域配置) ; 5. exportapp (导出应用) ; 6. importapp (导入应用信息) ; 7. importmaxmessages (导入消息) ; 8. exportmaxmessages(导出消息)
- **-maximopath**：maximo的发布包的路径
- **-packagepath**：数据导出的路径
- **-importpath**：数据导入的路径
- **-exportdomainids**：需要导出的域的ID,用","分割
- **-exportobjects**：需要导出的对象,用","分割
- **-exportmodules**：需要导出的模块的ID,用","分割
- **-exportapps**：需要导出的app,用","分割
- **-exportapps**：需要导出的消息的信息 使用字段 msgid 用 , 隔开

## Ant脚本参数说明