package com.bw.qrcodelibrary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 权限和动态权限库中已经都加了，不需要再加
 * 必须初始化
 * 相机、相册识别二维码必须重写 CodeUtils.onActivityResult 接受
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_qr_content)
    TextView mTvQrContent;
    @BindView(R.id.btn_qr_scan)
    Button mBtnQrScan;
    @BindView(R.id.btn_qr_open_image)
    Button mBtnQrOpenImage;
    @BindView(R.id.iv_qr_picture)
    ImageView mIvQrPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // TODO: 2019/12/27 必须初始化
        CodeUtils.init(this);

        mIvQrPicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // TODO: 2019/12/27 用法4、 长按或者点击二维码图片，识别二维码
                CodeUtils.analyzeByImageView(mIvQrPicture, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();

                    }
                });
                return true;
            }
        });
    }


    @OnClick({R.id.tv_qr_content, R.id.btn_qr_scan, R.id.btn_qr_open_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击生成二维码
            case R.id.tv_qr_content:
                // TODO: 2019/12/27 用法1、 根据文字生成二维码
                String content = mTvQrContent.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    // TODO: 2019/8/30 生成不带头像的二维码
                    Bitmap qrBitmap = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
                    mIvQrPicture.setImageBitmap(qrBitmap);
                }
                break;
            //扫描二维码
            case R.id.btn_qr_scan:
                // TODO: 2019/12/27 用法2、 相机扫一扫识别二维码
                CodeUtils.analyzeByCamera(this);
                break;
            //识别相册中的二维码
            case R.id.btn_qr_open_image:
                // TODO: 2019/12/27 用法3、 打开相册选择二维码图片识别二维码
                CodeUtils.analyzeByPhotos(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO: 2019/12/27 从相机和相册识别的时候需要重写该方法接受结果
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
