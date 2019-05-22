# Allure android reportlibrary

This library generate allure report for adnroid instrumented tests (Espresso,UiAutomator)

Usage
-----

First add a maven repo link into your `repositories` block of module build file:
           
    maven {
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
Thats all! Now you can run your instumetal tests (by command `./gradlew clean connectedAndroidTest`) 

Allure Report will be saved to `/sdcard/allure-results` by default.
If you want to generate allure report you should `adb pull /sdcard/allure-results` first and then generate report
If you want to change allure report directory you should set assets directory in your project:
```
   sourceSets {
        androidTest {
            assets.srcDirs = ['src/androidTest/assets/']
        }
    }
```
And create `allure.properties` file in this diractory. After that add standart allure properties:
```
           allure.results.directory=/sdcard/your/allure/path
           allure.link.issue.pattern=http://localhost:8080/browse/{}
           allure.link.tms.pattern=http://localhost/testrail/index.php?/cases/view/{}
```


# Example android allure reporting project with aspects:
<a href="https://github.com/FruNoman/AllureAndroidExample">AllureAndroidExample</a>

For using `@Step` and `@Attach` annotations you should add <a hraf="https://github.com/Archinamon/android-gradle-aspectj">Archiamon AspectJ plugin</a>:
```        ...
           classpath 'com.archinamon:android-gradle-aspectj:3.2.0'
           ...
```
```
           plugins {
               id 'com.android.application'
               id 'com.archinamon.aspectj'
           }
```

This library can't execute aspects from jar, so you shoud create StepAspects in your android flavor:


        ```import com.github.frunoman.allure.Allure;
           import com.github.frunoman.allure.AllureLifecycle;
           import com.github.frunoman.allure.Step;
           import com.github.frunoman.model_pojo.Parameter;
           import com.github.frunoman.model_pojo.Status;
           import com.github.frunoman.model_pojo.StepResult;



           import org.aspectj.lang.ProceedingJoinPoint;
           import org.aspectj.lang.annotation.Around;
           import org.aspectj.lang.annotation.Aspect;
           import org.aspectj.lang.reflect.MethodSignature;

           import java8.util.Objects;
           import java.util.UUID;

           import static com.github.frunoman.allure.util.AspectUtils.getParameters;

           import static com.github.frunoman.allure.util.ResultsUtils.getStatusDetails;
           import static com.github.frunoman.allure.util.ResultsUtils.getStatusNot;


           /**
            * @author Dmitry Baev charlie@yandex-team.ru
            * Date: 24.10.13
            * @author sskorol (Sergey Korol)
            */
           @Aspect
           public class StepsAspects {
               private static AllureLifecycle lifecycle;

               @SuppressWarnings("PMD.UnnecessaryLocalBeforeReturn")
               @Around("@annotation(com.github.frunoman.allure.Step) && execution(* *(..))")
               public Object step(final ProceedingJoinPoint joinPoint) throws Throwable {
                   final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                   final Step step = methodSignature.getMethod().getAnnotation(Step.class);
                   final String uuid = UUID.randomUUID().toString();
                   String returnState = "void";
                   String name = step.value();
                   if(name.isEmpty()){
                       name=methodSignature.getMethod().getName();
                   }
                   final StepResult result = new StepResult()
                           .withName(name)
                           .withParameters(getParameters(methodSignature, joinPoint.getArgs()));
                   getLifecycle().startStep(uuid, result);
                   try {
                       final Object proceed = joinPoint.proceed();
                       if(proceed!=null){
                           returnState = proceed.toString();
                       }
                       getLifecycle().updateStep1(uuid, result.withStatus(Status.PASSED));
                       return proceed;
                   } catch (Throwable e) {
                       getLifecycle().updateStep1(uuid, result.withStatus(getStatusNot(e))
                               .withStatusDetails(getStatusDetails(e).orElse(null)));
                       throw e;
                   } finally {
                       getLifecycle().updateStep1(uuid, result.withParameters(new Parameter().withName("return")
                               .withValue(returnState)));
                       getLifecycle().stopStep(uuid);
                   }
               }

               public static AllureLifecycle getLifecycle() {
                   if (Objects.isNull(lifecycle)) {
                       lifecycle = Allure.getLifecycle();
                   }
                   return lifecycle;
               }
           }
           
     ```
   
    
