/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Jni_Guomi */

#ifndef _Included_Jni_Guomi
#define _Included_Jni_Guomi
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     Jni_Guomi
 * Method:    sm2GenerateKey
 * Signature: ([B[B[B)V
 */
JNIEXPORT void JNICALL Java_Jni_1Guomi_sm2GenerateKey
  (JNIEnv *, jobject, jbyteArray, jbyteArray, jbyteArray);

/*
 * Class:     Jni_Guomi
 * Method:    sm2Sign
 * Signature: ([B[B[B[BI)[B
 */
JNIEXPORT jbyteArray JNICALL Java_Jni_1Guomi_sm2Sign
  (JNIEnv *, jobject, jbyteArray, jbyteArray, jbyteArray, jbyteArray, jint);

/*
 * Class:     Jni_Guomi
 * Method:    sm2Verify
 * Signature: ([B[B[BI[BI)Z
 */
JNIEXPORT jboolean JNICALL Java_Jni_1Guomi_sm2Verify
  (JNIEnv *, jobject, jbyteArray, jbyteArray, jbyteArray, jint, jbyteArray, jint);

/*
 * Class:     Jni_Guomi
 * Method:    sm3Hash
 * Signature: ([BI)[B
 */
JNIEXPORT jbyteArray JNICALL Java_Jni_1Guomi_sm3Hash
  (JNIEnv *, jobject, jbyteArray, jint);

/*
 * Class:     Jni_Guomi
 * Method:    sm4CBCEnc
 * Signature: ([BI[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_Jni_1Guomi_sm4CBCEnc
  (JNIEnv *, jobject, jbyteArray, jint, jbyteArray, jbyteArray);

/*
 * Class:     Jni_Guomi
 * Method:    sm4CBCDec
 * Signature: ([BI[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_Jni_1Guomi_sm4CBCDec
  (JNIEnv *, jobject, jbyteArray, jint, jbyteArray, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif