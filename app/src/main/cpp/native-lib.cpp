#include <jni.h>
#include <string>

extern "C"
jstring
Java_android_test_robot_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
