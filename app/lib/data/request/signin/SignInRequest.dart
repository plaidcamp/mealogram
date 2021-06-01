import 'package:gourmetlog/data/response/signin/SignInResponse.dart';
import 'package:http/http.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:async';

class SignInRequest {
  Future<List<SignInResponse>> getSignInInfo() async {
    // final response = await http.get('https://localhost/test');
    final Uri postUrl = Uri.parse("https://localhost/v1/signin");
    Response res = await get(postUrl);
    if (res.statusCode == 200) {
      List<dynamic> body = jsonDecode(res.body);

      //map 메소드를 통해 iterable내부의 각 element에 대해서 SignInResponse.fromJson 메소드 실행(json 형태로 리턴해주는 메소드)
      List<SignInResponse> result = body.map((dynamic item) => SignInResponse.fromJson(item)).toList();
      return result;
    }
  }
}