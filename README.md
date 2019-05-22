# Allure android plugin 

Usage
-----

First add a maven repo link into your `repositories` block of module build file:
           
    ``` maven {
           url 'https://jitpack.io'
        }
              
Add dependency to your`dependencies` section:

   ```implementation 'com.github.FruNoman:allure-android:0.0.6'```
   
Add read/write storage permissions to AndroidManifest.xml:
  ```
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 ```
Add runtime read/write permissions to Junit test class:
 ```
    @Rule
    public GrantPermissionRule permissionsRules =
            GrantPermissionRule.grant(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
```
Set `AndroidAllureJunit4Runner.class` to `@RunWith` annotation:
```
@RunWith(AndroidAllureJunit4Runner.class)
public class ExampleInstrumentedTest {
}
```
