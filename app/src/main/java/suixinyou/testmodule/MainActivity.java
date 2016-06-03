package suixinyou.testmodule;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.Toast;

import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ShowToast(View view)
    {
        Toast.makeText(this, "Toast text, normal", Toast.LENGTH_SHORT).show();
    }


    private Button button01 = null;
    public void ChangeColor(View view)
    {
        button01 = (Button)findViewById(R.id.button_changecolor);
        if (button01 != null)
            button01.setBackgroundColor(getResources().getColor(255));
    }

    private WebView webview;
    public void GoURL(View view)
    {
        webview=(WebView)findViewById(R.id.webView);
        //设置WebView属性，能够执行JavaScript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >=19)
        {
            webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        //加载URL内容
        webview.loadUrl("http://www.baidu.com");
        //设置web视图客户端
        webview.setWebViewClient(new MyWebViewClient());
    }
    //设置回退
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&webview.canGoBack())
        {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    //web视图客户端
    public class MyWebViewClient extends WebViewClient
    {
        public boolean shouldOverviewUrlLoading(WebView view,String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

/**
    public class SystemMemoryInfo
    {
        public static HashMap<String, String> getMemoryInfo(Context context)
        {
            HashMap<String, String> hmMeminfo = new HashMap<String, String>();
            // 系统内存信息文件
            String meminfoPath = "/proc/meminfo";
            BufferedReader br = null;
            try {
                FileReader fr = new FileReader(meminfoPath);
                br = new BufferedReader(fr, 4096);
                String lineStr = null;
                while ((lineStr = br.readLine()) != null) {
                    // 空格区分 分为三部分
                    // 1. 属性名： 2.数值 3.kB
                    // eg. MemTotal: 126608 kB
                    String[] lineItems = lineStr.split("\\s+");
                    if (lineItems != null && lineItems.length == 3) {
                        // 去掉[:]
                        String itemName = lineItems[0].substring(0, lineItems[0].length() - 1);
                        Log.i("itemName", itemName);
                        // 单位是KB，乘以1024转换为Byte,
                        long itemMemory = Integer.valueOf(lineItems[1]) * 1024;
                        // Byte转换为KB或者MB，内存大小规格化
                        String itemValue = Formatter.formatFileSize(context, itemMemory);
                        hmMeminfo.put(itemName, itemValue);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 打印MemoryInfo信息
            Log.i("getMemoryInfo", hmMeminfo.toString());
            return hmMeminfo;
        }
        public static long getAvailableInternalMemorySize() {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        }
        // 这个是手机内存的总空间大小
        public static long getTotalInternalMemorySize() {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        }
    }
 */
}
