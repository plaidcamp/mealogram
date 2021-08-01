import 'dart:convert';
import 'dart:io';
import 'package:gourmetlog/common/auth/TokenManager.dart';
import 'package:http/http.dart';
import 'package:http_interceptor/http_interceptor.dart';

class ExpiredTokenManager extends RetryPolicy{
  @override
  int maxRetryAttempts = 1;

  @override
  Future<bool> shouldAttemptRetryOnResponse(ResponseData data) async {

    //access token 만료 시 refresh token과 access token 둘다 초기화
    if(data.statusCode == 401) {
      final Uri postUrl = Uri.parse("https://localhost/post");

      String token = await TokenManager.instance.getRefreshToken();
      Response res = await post(postUrl, headers: {
        'Content-Type': 'application/json; charset=UTF-8',
        HttpHeaders.authorizationHeader : token,
      },
        body: jsonEncode(<String, String>{
          'refreshToken' : token,
        }),);
      if (res.statusCode == 200) {
        Map<String, dynamic> body = jsonDecode(res.body);
        String newRefreshToken = body['refreshToken'];
        String newAccessToken = body['accessToken'];
        TokenManager.instance.saveToken("refreshToken", newRefreshToken);
        TokenManager.instance.saveToken("accessToken", newAccessToken);

        return true;
      }
      else {
        throw Exception("reset token Error");
      }
      return true;
    }
    return false;
  }
}