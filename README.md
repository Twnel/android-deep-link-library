#Android Deep Link Library
Conecta tu aplicación con Twnel Messenger de forma facil y rapida.

# minSdk 14

#1 - Agrega la dependencia en build.gradle

```groovy
 repositories {
        // ...
        maven { url "https://jitpack.io" }
 }
```
```groovy
 dependencies {
        compile 'com.github.Twnel:android-deep-link-library:1.0'
	}
```
#2 - Usa la clase TwnelDeepLink para realizar el enlace con Twnel Messenger

# Demo
![Alt text](https://github.com/Twnel/android-deep-link-library/blob/master/art/demo_deeplink.png)
# Ejemplo
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button butOpenChat;
    private Button butOpenChatWithoutAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butOpenChat = (Button) findViewById(R.id.but_open_chat);
        butOpenChatWithoutAlert = (Button) findViewById(R.id.but_open_chat_without_dialog);
        butOpenChat.setOnClickListener(this);
        butOpenChatWithoutAlert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.but_open_chat){
            new TwnelDeepLink.Builder()
                    .context(this)
                    .companyId("twnelvipalerts")
                    .appPackageName("com.twnel.deeplinklibrary")
                    .activityClassName("com.twnel.deeplinklibrary.MainActivity")
                    .showDialog(true)
                    .dialogTitle("Install Twnel")
                    .dialogMessage("You can chat with us via Twnel Messenger 24/7")
                    .dialogNextButtonText("Next")
                    .build()
                    .navigate();
        }else {
            new TwnelDeepLink.Builder()
                    .context(this)
                    .companyId("twnelvipalerts")
                    .appPackageName("com.twnel.deeplinklibrary")
                    .activityClassName("com.twnel.deeplinklibrary.MainActivity")
                    .showDialog(false)
                    .build()
                    .navigate();
        }
    }
}

