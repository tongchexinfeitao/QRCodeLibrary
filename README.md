一、依赖

1、在根工程的 build.gradle 中:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
	
	
2、在 app 的build.gradle 中：

	dependencies {
	        implementation 'com.github.tongchexinfeitao:QRCodeLibrary:1.0'
	}
	

---------------------------------------------------------------------------------------------------

二、用法：

	/**
 * 权限和动态权限库中已经都加了，不需要再加
 *
 * 必须初始化
 * CodeUtils.init(this);
 *
 * 用法1、 根据文字生成带Logo二维码（不带Logo参数传null，如果机器人不显示，自己找一张Logo使用）
 *  Bitmap qrBitmap = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
 *
 * 用法2、 相机扫一扫识别二维码
 * CodeUtils.analyzeByCamera(this);   重写 onActivityResult方法，调用 CodeUtils.onActivityResult 接受结果
 *
 * 用法3、 打开相册选择二维码图片识别二维码
 * CodeUtils.analyzeByPhotos(this);   重写 onActivityResult方法，调用 CodeUtils.onActivityResult 接受结果
 *
 *用法4、 长按或者点击二维码图片，识别二维码
 * CodeUtils.analyzeByImageView(imageView, AnalyzeCallback)
 *
 */
 
 
 三、详细用法请看app
 
 四、注意事项
 1、库中已经适配好权限和动态权限，不用再添加权限
 2、关于v7包版本不一致的问题可以在 app的build.gradle的  android 的 外 面添加， 指定成自己的版本号
 
 configurations.all {
    resolutionStrategy.eachDependency { DependencyResolveDetails details ->
        def requested = details.requested
        if (requested.group == 'com.android.support') {
            if (!requested.name.startsWith("multidex")) {
                details.useVersion '28.0.0'
            }
        }
    }
}

 
 
 
