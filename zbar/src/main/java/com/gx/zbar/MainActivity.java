package com.gx.zbar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2,clear;
    private EditText editText;
    private ImageView imageView;
    private  byte[]  mContent;
    Bitmap myBitmap;
    private String love;
    private ContentResolver resolver;
    private Bitmap mmbitmap;
    private Button yes;
    private Button fou;
    private PopupWindow pop;
    private View view;
    private Bitmap copybitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findview
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        clear = findViewById(R.id.clear);
        editText = findViewById(R.id.edit);
        imageView = findViewById(R.id.image);
        //弹框
        view = View.inflate(MainActivity.this, R.layout.alert, null);
        yes = view.findViewById(R.id.yes);
        fou = view.findViewById(R.id.fou);
        //扫描二维码
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestScanActivity.class));
                
            }
        });
        //生成二维码
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //8.1以下系统
                //打开手机的图库
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,0);
                //得到要扫描到的文字
                love = editText.getText().toString().trim();
            }
        });
        //清空二维码
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
            }
        });
        //长按保存图片
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //弹框
                pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                pop.showAsDropDown(v,0,0);
                return false;
            }
        });
        //得到图片 是否添加到手机内存中
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (copybitmap==null){
                    return;
                }
                //添加到手机内存中的方法
                saveImageToGallery(MainActivity.this,copybitmap);
                //添加后吐司一下
                Toast.makeText(MainActivity.this,"已经保存到文件图片中请查看",Toast.LENGTH_SHORT).show();
                pop.dismiss();
            }
        });
        fou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"fou",Toast.LENGTH_SHORT).show();
                pop.dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        resolver = getContentResolver();
        switch (requestCode){
                 case 0:
                        //打开手机的相册
                        Uri uri = data.getData();
                        cropRawPhoto(uri);
                    break;
                case 1 :
                    //剪切后的图片
                    if (data != null) {
                        //得到剪切后的图片 bitmap格式
                        mmbitmap = setImageToHeadViewPhote(data);
                        //生成带图片的二维码
                        @SuppressLint("StaticFieldLeak") AsyncTask<Bitmap, Void, Bitmap> execute = new AsyncTask<Bitmap, Void, Bitmap>() {

                            @Override
                            protected Bitmap doInBackground(Bitmap... params) {
                                //如果输入的文字是空的话那么调用默认的文字
                                if (love.equals("")){
                                    return QRCodeEncoder.syncEncodeQRCode("lalal", BGAQRCodeUtil.dp2px(MainActivity.this, 220), Color.parseColor("#ff0000"), params[0]);
                                }
                                //如果输入的文字不为空的话则设置
                                return QRCodeEncoder.syncEncodeQRCode(""+love, BGAQRCodeUtil.dp2px(MainActivity.this, 220), Color.parseColor("#ff0000"), params[0]);
                            }

                            @Override
                            protected void onPostExecute(Bitmap bitmap) {
                                if (bitmap!=null) {
                                    //设置显示图片
                                    copybitmap=bitmap;
                                    imageView.setImageBitmap(bitmap);
                                } else {
                                    Toast.makeText(MainActivity.this, "生成带logo的二维码失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //传入AsyncTask中得到的图片
                        }.execute(mmbitmap);
                    }
                    break;
        }
    }

    //根据intent得到bitmap的格式图片
    private Bitmap setImageToHeadViewPhote(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            return photo;
        }
        return null;
    }
   /* private void setImageToHeadView(Intent data) {
        //获得图片的uri
        Uri originalUri = data.getData();
        //将图片内容解析成字节数组
       try {
            mContent=readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将字节数组转换为ImageView可调用的Bitmap对象
        myBitmap = getPicFromBytes(mContent, null);
       @SuppressLint("StaticFieldLeak") AsyncTask<Bitmap, Void, Bitmap> execute = new AsyncTask<Bitmap, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Bitmap... params) {
                if (love.equals("")){
                    return QRCodeEncoder.syncEncodeQRCode("lalal", BGAQRCodeUtil.dp2px(MainActivity.this, 220), Color.parseColor("#ff0000"), params[0]);
                }
                return QRCodeEncoder.syncEncodeQRCode(""+love, BGAQRCodeUtil.dp2px(MainActivity.this, 220), Color.parseColor("#ff0000"), params[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap!=null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(MainActivity.this, "生成带logo的二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(myBitmap);
    }*/

/*    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null){
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts);
            }

            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }*/

/*    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }*/
        //剪裁
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP"); //打开系统自带的裁剪图片的intent
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");

        // 设置裁剪区域的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // 设置裁剪区域的宽度和高度
        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 160);

        intent.putExtra("return-data", true);

        //解决小米手机的剪裁问题
        Uri uri1 = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, 1);

    }
    //向手机内存中保存图片
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        // 首先创建一个路径
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "erweima";
        //根据路径创建一个文件夹
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        //如果这个文件存在则设置要保存的名字
        String fileName = System.currentTimeMillis() + ".jpg";
        //实例化一下这个文件
        File file = new File(appDir, fileName);
        try {
            //向这个文件中输入数据
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
