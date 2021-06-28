import 'package:flutter/foundation.dart';

class PostResponse {
  final int userId;

  PostResponse ({
    @required this.userId,
  });

  factory PostResponse.fromJson(Map<String, dynamic> json) {
    return PostResponse(
        userId: json['userId'] as int,
    );
  }

}