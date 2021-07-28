import 'package:gourmetlog/common/auth/HttpInterceptor.dart';
import 'package:http/http.dart';
import 'package:http_interceptor/http/http.dart';

class GlobalRequest {
  final client = InterceptedClient.build(interceptors: [HttpInterceptor()],);

  Client getClient() {
    return client;
  }

}