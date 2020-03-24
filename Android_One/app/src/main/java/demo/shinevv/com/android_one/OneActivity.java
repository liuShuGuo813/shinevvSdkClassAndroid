package demo.shinevv.com.android_one;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ksyun.player.now.model.BaseServerRes;
import com.ksyun.player.now.model.ServerAPI;
import com.ksyun.player.now.model.VVAuthResponse;
import com.ksyun.player.now.view.AccountManager;
import com.shinevv.data.Constants;
import com.shinevv.inter.LoginClass;

import demo.shinevv.com.android_one.api.MyVVRoomApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class OneActivity extends AppCompatActivity {
    protected EditText etRoomNumber;
    protected EditText etRoomPassword;
    protected EditText etNickName;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        etRoomNumber = findViewById(R.id.room_number);
        etRoomPassword = findViewById(R.id.room_password);
        etNickName = findViewById(R.id.nick_name);
        progressBar = findViewById(R.id.login_pg);
        TextView tvVersion = findViewById(R.id.vvroom_version);
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            //设置版本信息
            tvVersion.setText(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        textView = findViewById(R.id.mLogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setClickable(false);
                progressBar.setVisibility(View.VISIBLE);
                loginToClass(etRoomNumber.getText().toString(), etRoomPassword.getText().toString(), etNickName.getText().toString());
            }
        });
    }

    /**
     * 登录课堂
     *
     * @param mRoomId   房间号
     * @param mPassword 密码
     * @param mName     昵称
     */
    public void loginToClass(final String mRoomId, final String mPassword, final String mName) {
        LoginClass loginClass = new LoginClass();
        loginClass.loginOnline(this, mRoomId, mPassword, mName, new LoginClass.LoginClassCallback() {
            @Override
            public void onSuccess(String result) {
                progressBar.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(OneActivity.this, com.shinevv.vvroom.MainActivity.class);
                intent.putExtra("isEject", true);
                intent.putExtra(Constants.INTENT_ROOM_NUMBER, mRoomId);
                intent.putExtra(Constants.INTENT_ROOM_PASSWORD, mPassword);
                intent.putExtra(Constants.INTENT_NICK_NAME, mName);
                intent.putExtra(Constants.INTENT_ROLE, Constants.INTENT_ROLE_STUDENT);
                startActivity(intent);
            }

            @Override
            public void onFailure(String result) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setClickable(true);
            }

        });
    }
}
