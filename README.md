# TokBoxChat
Simple WebRTC-based video chat example application


This application being launched on two android devices creates a simple TokBox-based (https://tokbox.com/) paired video/audio chat.
If being lauched single, puts app in waiting incoming connection mode, connect with paired device (by auth server),
and relaunches connection if one of paired devices goes offline.
Application uses https://github.com/Sushinski/OpenTokAuthServer instance on 85.143.215.126(or one-pair version - see .apk - s below )
for generatin session keys.
Used also: Retained fragments, MVP, Dagger2, Retrofit2, EventBus, Crashlytics.

APK`s:

Remote auth server version (multipaired):
https://github.com/Sushinski/TokBoxChat/blob/master/app-debug-85_143_215_126.apk

See https://github.com/Sushinski/TokBoxChat/blob/master/app/src/main/java/com/sushinski/tokboxchat/data_source/RestApiKeySource.java
for server address adjustment.

Local keys single-paired version:
https://github.com/Sushinski/TokBoxChat/blob/master/app-debug_local_auth_keys.apk

