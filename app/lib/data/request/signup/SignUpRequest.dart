import 'package:gourmetlog/data/response/signup/SignUpResponse.dart';
import 'package:http/http.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:async';


class SignUpRequest {
  Future<SignUpResponse> getSignInInfo(String email, String password) async {
    // final response = await http.get('https://localhost/test');
    final Uri postUrl = Uri.parse("https://localhost/signup");

    Response res = await post(postUrl, headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
      body: jsonEncode(<String, String>{
        'email': email,
        'password' : password
      }),);
    if (res.statusCode == 200) {
      Map<String,dynamic> body = jsonDecode(res.body);

      SignUpResponse result = SignUpResponse.fromJson(body);
      return result;
    }
    else {
      throw Exception("Sign-Up Request Error");
    }
  }
}