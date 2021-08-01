import 'package:flutter/foundation.dart';

class SignUpResponse {
  final int userId;
  final int matchCode;

  SignUpResponse ({
    @required this.userId,
    @required this.matchCode,
  });

  factory SignUpResponse.fromJson(Map<String, dynamic> json) {
    return SignUpResponse(
        userId: json['userId'] as int,
        matchCode: json['matchCode'] as int
    );
  }

}