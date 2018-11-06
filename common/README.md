

> 这个模块是用来复制的, 因为直接创建一个模块要么要生成很多无用的文件,且要修改`build.gradle`文件, 要么创建很多文件. 很是麻烦.

## 操作步骤
1. 把此项目copy到相应目录下, 重命名
2. 修改根目录的`settings.gradle`文件, 增加相应模块()
3. 利用`gradle`重新导入项目就可以看到相应模块已经建立
4. 重构里面的包名`forCopy`和启动入口`ForCopyApplication.java`名 
