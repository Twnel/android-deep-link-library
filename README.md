# Android Deep Link Library
Conecta tu aplicación con Twnel Messenger de forma facil y rapida.

## minSdk 14
# Installing
### 1 - Agrega la dependencia en build.gradle

```groovy
 repositories {
        // ...
        maven { url "https://jitpack.io" }
 }
```
```groovy
 dependencies {
        compile 'com.github.Twnel:android-deep-link-library:1.1'
	}
```
### 2 - Usa la clase TwnelDeepLink para realizar el enlace con Twnel Messenger
```java
 new TwnelDeepLink.Builder()
                    .context(this)
                    //The company identifier inside Twnel
                    .companyId("twnelvipalerts")
                     //A fully-qualified package name for intent generation (used to return to your app)
		     //this is a example replace it by your app package name
                    .appPackageName("com.twnel.deeplinklibrary")
                    // A fully-qualified Activity class name for intent generation (used to return to your app).
		    //this is a example replace it by your app activity class name
                    .activityClassName("com.twnel.deeplinklibrary.MainActivity")
                    //if you want to show a dialog alert when Twnel App is not installed 
                    .showDialog(true)
                    .dialogTitle("Install Twnel")
                    .dialogMessage("You can chat with us via Twnel Messenger 24/7")
                    .dialogNextButtonText("Next")
                    .build()
                    .navigate();	
```
## 3 **agrega android:exported="true" a tu activity (activityClassName) en  AndroidManifest.xml (Para poder regresar a tu aplicacíon).**

# Demo
![Alt text](https://github.com/Twnel/android-deep-link-library/blob/master/art/demo_deeplink.png)
## Ejemplo
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

```
## Versioning

For the versions available, see the [tags on this repository](https://github.com/Twnel/android-deep-link-library/releases). 

## Authors

* **Yesid Lazaro** -@ingyesid 

## License

All rights reserved Twnel Inc 2017
