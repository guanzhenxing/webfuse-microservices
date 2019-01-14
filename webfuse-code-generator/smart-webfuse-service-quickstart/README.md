# service-archetype

## 命令

## 生成模板命令

步骤1：

```shell
mvn archetype:create-from-project
```

步骤2：

将.gitignore复制到`./target/generated-sources/archetype/src/main/resources/archetype-resources`中

步骤3：

修改`./target/generated-sources/archetype`的pom.xml。添加如下代码：
```xml
<plugin>
  <artifactId>maven-resources-plugin</artifactId>
  <version>3.0.2</version>
  <configuration>
    <!-- Required so that .gitignore gets included in archetypes -->
    <!-- see https://issues.apache.org/jira/browse/MRESOURCES-190 -->
    <addDefaultExcludes>false</addDefaultExcludes>
  </configuration>
</plugin>
```

步骤4：

到`./target/generated-sources/archetype`目录下，运行`mvn install`安装。

>
> 说明：步骤2和步骤3是为了能够生成`.gitignore`文件。 
> 如果需要包含更多的文件或者去掉其他的文件，那么在`archetype-metadata.xml`中修改对应的`fileSet`。
> 

### 使用模板生成项目

```shell
mvn archetype:generate -DarchetypeCatalog=local
```
选择对应的数字，按照提示进行操作。

或者

```shell
mvn archetype:generate \
     -DarchetypeGroupId=cn.wenfuse \
     -DarchetypeArtifactId=smart-webfuse-service-quickstart-archetype \
     -DarchetypeVersion=1.0.0 \
     -DgroupId=one.top \
     -DartifactId=你想要的项目名 \
     -Dversion=1.0.0 \
     -DinteractiveMode=false \
     -DarchetypeCatalog=internal,remote \
     -DarchetypeRepository=远程的私有库地址
```

或者在IDEA中添加：

```text
File >> New >> Project.. >> Maven >> 勾选“Create from archetype” >> Add Archetype >> 填写对应的值 >> OK
 
对应的值：
GroupId=one.top
ArtifactId=smart-webfuse-service-quickstart-archetype
Version=1.0.0
Repository=远程的私有库地址

```
下次创建项目的时候就可以使用此模板创建了


### deploy

`./target/generated-sources/archetype`的pom.xml中添加：

```xml
 <distributionManagement>
        <repository>
            <id>远程的私有库-release</id>
            <url>远程的私有库地址</url>
        </repository>

        <snapshotRepository>
            <id>远程的私有库-snapshots</id>
            <url>远程的私有库地址</url>
        </snapshotRepository>
    </distributionManagement>
```
 然后 在settings.xml中<server/>添加对应的用户名密码