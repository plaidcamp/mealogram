import 'package:gourmetlog/data/response/signin/SignInResponse.dart';
import 'package:http/http.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:async';


class SignInRequest {
  Future<SignInResponse> getSignInInfo(String email, String password) async {
    // final response = await http.get('https://localhost/test');
    final Uri postUrl = Uri.parse("https://localhost/login");

    Response res = await post(postUrl, headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
      body: jsonEncode(<String, String>{
        'email': email,
        'password' : password
      }),);
    if (res.statusCode == 200) {
      Map<String,dynamic> body = jsonDecode(res.body);

      SignInResponse result = SignInResponse.fromJson(body);
      return result;
    }
    else {
      throw Exception("Sign-In Request Error");
    }
  }
}