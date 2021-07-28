import 'package:flutter/foundation.dart';

class SignInResponse {
  final int userId;
  final String accessToken;
  final String refreshToken;
  final int matchCode;

  SignInResponse ({
    @required this.userId,
    @required this.accessToken,
    @required this.refreshToken,
    @required this.matchCode,
  });

  factory SignInResponse.fromJson(Map<String, dynamic> json) {
    return SignInResponse(
      userId: json['userId'] as int,
      accessToken: json['accessToken'] as String,
      refreshToken: json['refreshToken'] as String,
      matchCode: json['matchCode'] as int
    );
  }

}