# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Programs\AndroidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

## PROJECT SPECIFIC GUARDS ##

# Keep the BuildConfig
-keep class com.example.BuildConfig { *; }

# Keep the support library
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }

## END PROJECT SPECIFIC GUARDS ##

## BUTTERKNIFE GUARDS ##

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

## END BUTTERKNIFE GUARDS ##

## DESIGN LIBRARY GUARDS ##

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

## END DESIGN LIBRARY GUARDS

## SUPPORT V7 APPCOMPAT GUARDS ##

-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

## END SUPPORT V7 APPCOMPAT GUARDS ##

## SUPPORT V7 CARDVIEW GUARDS ##

-keep class android.support.v7.widget.RoundRectDrawable { *; }

## END SUPPORT V7 CARDVIEW GUARDS ##