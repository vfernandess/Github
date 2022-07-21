#include <jni.h>
#include <string>
#include <jni.h>
#include <jni.h>
#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_voidx_security_Keys_apiKey(JNIEnv *env, jobject thiz) {
    std::string apiKey = "my-hidden-api-key";
    return env->NewStringUTF(apiKey.c_str());
}
