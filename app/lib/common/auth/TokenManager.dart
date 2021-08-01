import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class TokenManager {
  static final TokenManager instance = new TokenManager();
  final storage =  new FlutterSecureStorage();

  factory TokenManager() => instance;

  Future saveToken(String key, String value) async {
    var result = await storage.write(key: key, value: value);
    return result;
  }

  Future<String> readToken(String key) async {
    var result = await storage.read(key: key);
    return result;
  }

  Future<String> getAccessToken() async {
    var result = await storage.read(key: "accessToken");
    return result;
  }

  Future<String> getRefreshToken() async {
    var result = await storage.read(key: "refreshToken");
    return result;
  }

  Future<DateTime> getAccessTokenTime() async {
    var result = await storage.read(key: "accessTokenTime");
    return DateTime.parse(result);
  }

  Future<DateTime> getRefreshTokenTime() async {
    var result = await storage.read(key: "refreshTokenTime");
    return DateTime.parse(result);
  }

  Future deleteToken(String key) async {
    var result = await storage.delete(key: key);
    return result;
  }

  Future initToken() async {
    var result = await storage.deleteAll();
    return result;
  }


}