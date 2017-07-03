## 项目划分

- news 资讯模块
- questionnaire 问卷模块
- supervice 监督模块
- community 社区模块
- personal 我的模块
- login 登录模块
- common 公共内容


## 关于第三方框架

已有框架

- 图片加载：glide
- 控件绑定 ButterKnife
- 网络框架：Retrofit
- 函数式编程：rxjava/rxAndroid
- 完美屏幕适配：autoLayout
- 解析：Gson
- 原生拓展：recyclerView   design


已经对retrofit进行了一定的封装，使用如下

1.创建一个类继承自BaseResponse,重写parseInfo方法（会传入info内的json串，在其中进行数据解析）

2.使用:PS:如果觉得警告麻烦 可以在调用的方法上加上 

	@SuppressWarnings("unchecked")
	new HttpRequest<DefaultResponse>()
                .with(context)
                .addParam("key","values")//添加单个参数
                .addParams(new TreeMap<String, String>())//添加多个参数
                .setListener(new HttpRequest.OnNetworkListener<DefaultResponse>() {
                    @Override
                    public void onSuccess(DefaultResponse response) {
			//请求成功回调
                    }

                    @Override
                    public void onFail(String message) {
			//请求失败回调
                    }
                })//添加回调
                .start(new DefaultResponse());//开始请求

3.如需上传图片，只需要调用CommonUtils.getImg()方法将图片转成base64字符串 通过key-values的形式放到参数中，多张图片调用getImgs
