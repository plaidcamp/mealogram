import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class AuthManager {
  static final AuthManager instance = new AuthManager();
  final storage =  new FlutterSecureStorage();

  factory AuthManager() => instance;

  Future saveToken(String key, String value) async {
    var result = await storage.write(key: key, value: value);
    return result;
  }

  Future<String> readToken(String key) async {
    var result = await storage.read(key: key);
    return result;
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