import 'dart:convert';
import 'dart:io';
import 'package:gourmetlog/common/auth/TokenManager.dart';
import 'package:gourmetlog/data/request/GlobalRequest.dart';
import 'package:gourmetlog/data/response/post/PostResponse.dart';
import 'package:http/http.dart';

class PostRequest extends GlobalRequest{

  Future<PostResponse> getPostInfo(int userId) async {
    final Uri postUrl = Uri.parse("https://localhost/post");

    TokenManager.instance.getAccessToken().then((token) async {
      Response res = await client.post(postUrl, headers: {
        'Content-Type': 'application/json; charset=UTF-8',
        HttpHeaders.authorizationHeader : token,
      },
        body: jsonEncode(<String, int>{
          'userId': userId,
        }),);
      if (res.statusCode == 200) {
        Map<String,dynamic> body = jsonDecode(res.body);

        PostResponse result = PostResponse.fromJson(body);
        return result;
      }

      else if (res.statusCode == 401) {

      }

      else {
        throw Exception("Sign-In Request Error");
      }
    }).catchError(() {
      throw Exception("Read token error");
    });


  }
}