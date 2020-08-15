# DayCare 
Team project of CSYE6200 in NEU

## 如何运行前端
1. 安装node.js
```shell script
# Windows 
# 1. [推荐]
https://nodejs.org/zh-cn/download/package-manager/#windows
# Mac
# 1. curl
curl "https://nodejs.org/dist/latest/node-${VERSION:-$(wget -qO- https://nodejs.org/dist/latest/ | sed -nE 's|.*>node-(.*)\.pkg</a>.*|\1|p')}.pkg" > "$HOME/Downloads/node-latest.pkg" && sudo installer -store -pkg "$HOME/Downloads/node-latest.pkg" -target "/"
# 2. brew
brew install node
# 3. [推荐]
https://nodejs.org/zh-cn/#home-downloadhead
```
2. [可选 && 推荐]安装yarn
```shell script
npm install -g yarn --registry=https://registry.npm.taobao.org
# [推荐]
yarn config set registry https://registry.npm.taobao.org -g
yarn config set sass_binary_site http://cdn.npm.taobao.org/dist/node-sass -g
``` 
3. 安装依赖
```shell script
# 终端中进入web-fe目录下
cd (填自己的文件夹路径并除去两个括号)/web-fe
yarn (或者 npm install)

# 在IDEA中安装依赖（不推荐，网上自己搜）
```
4. 启动
```shell script
# 终端中进入web-fe目录下
cd (填自己的文件夹路径并除去两个括号)/web-fe
yarn start
```

## 如何写一个接口

http-rest-service基本概念对齐
1. get-获取-list
2. post-提交-save

### 后端
1. 明确http请求方法，与rest基本概念对齐
  - Get
  - Post
2. [根据实际情况决定]写Mapper层的接口去数据库中拉取数据
3. 写Service层中对于数据库中拉取到的数据的业务处理
4. 在Contoller中对于Service中业务处理的请求封装

具体示例如下。但下方省略部分细节，如Mapper层的SQL查询封装类的编写，Controller层中不同查询结果对响应码的处理
```java
// 以通过targetAge查询相关Immunization为例子
// 1. 查询：对应mapper list；对应http:get方法
// ImunizationMapper.java
public interface ImmunizationMapper extends BaseMapper<ImmunizationDO> {
  // 具体实现方式看mybatis-plus文档。与数据库层交互写在Mapper层外也可上升至Service层通过对应的Mapper.list封装Wrapper类来写查询条件，见下方ImmunizationService
  List<Immunization> getImmunizationByTargetAge(int targetAge);
}

// [1补充] 1的替代实现步骤1 在ImmunizationService中声明方法
public interface ImmunizationService extends BaseService<ImmunizationDO> {
  List<ImmunizationDO> getImmunizationByTargetAge(int targetAge);
}
// [1补充] 1的替代实现步骤2 在ImmunizationServiceImpl中实现方法
public class ImmunizationServiceImpl extends BaseServiceImpl<ImmunizationMapper, ImmunizationDO>
  implements ImmunizationService {
  @Override
  public List<ImmunizationDO> getImmunizationByTargetAge(int targetAge) {
    return baseMapper.selectList(Wrappers.<ImmunizationDO>query().eq("targetAge", targetAge));
  }
}

// 2.在Service层中封装好业务逻辑相关代码后，进入对应的Controller中写对前端暴露的接口
@RestController
@RequestMapping("/v1/immunization")
@Slf4j
public class ImmunizationController extends BaseController {
  @Autowired
  ImmunizationService service;
  // GetMapping对应http的请求方法，获取http请求参数可参考其他Controller或网上搜。
  @GetMapping("/list/ageState/{ageState}")
  public Result<Object> add(@PathVariable int ageState) {
    return Result.buildOkData(service.getImmunizationByTargetAge(ageState));
  }
}
```

### 前端
> 因为我本身对Ant-Design这个组件库并不是很熟悉，所以下方可能会存在一些理解上的偏差。有什么问题可以直接戳我（申屠康）

React语法、组件、项目结构等基本知识见官网文档，此处只描述如何如何在一个页面与后端接口对应。
```javascript
// ├── ListTableList
// │   ├── components
// │   │   ├── CreateForm.tsx
// │   │   └── UpdateForm.tsx
// │   ├── data.d.ts 前端结构体定义，理解成后端class
// │   ├── index.tsx 页面组件及各类方法
// │   └── service.ts 后端接口声明供index中调用

// ./web-fe-src/pages中的每个文件夹的语义对应一个前端页面，以在表单中展示数据（ListTableList）为例
// 1. 定义一个结构体与后端VO结构对应，[定义一个结构体作为查询条件，若简单查询可直接只用url参数而非结构体]
export interface TableListItem {
  key: number;
  disabled?: boolean;
  href: string;
  avatar: string;
  name: string;
  owner: string;
  desc: string;
  callNo: number;
  status: number;
  updatedAt: Date;
  createdAt: Date;
  progress: number;
}

export interface TableListParams {
  status?: string;
  name?: string;
  desc?: string;
  key?: number;
  pageSize?: number;
  currentPage?: number;
  filter?: { [key: string]: any[] };
  sorter?: { [key: string]: any };
}



// 2. 定义一个ProColumns<TableListItem>[]变量作为Table的列，理解成表的列集合即可
const columns: ProColumns<TableListItem>[] = [
    {
      title: '规则名称',
      dataIndex: 'name',
      rules: [
        {
          required: true,
          message: '规则名称为必填项',
        },
      ],
    }];
// TableListItem为表中数据项的实体，与后端VO结构对应。其中dataIndex需与前端定义的结构体的字段名对齐，rules为表单验证规则，详情见文档

// 3. 在service.ts中声明后端接口，如
export async function queryRule(params?: TableListParams) {
  return request('/api/rule', {
    params,
  });
}

// 4. 在ProTable的request中写调后端接口的具体实现，如下：
// request={(params, sorter, filter) => queryRule({ ...params, sorter, filter })}
```


### Basic Usage
//TODO
