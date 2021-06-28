import 'package:flutter/foundation.dart';

class SignInResponse {
  final int userId;
  final String token;
  final int matchCode;

  SignInResponse ({
    @required this.userId,
    @required this.token,
    @required this.matchCode,
  });

  factory SignInResponse.fromJson(Map<String, dynamic> json) {
    return SignInResponse(
      userId: json['userId'] as int,
      token: json['token'] as String,
      matchCode: json['matchCode'] as int
    );
  }

}