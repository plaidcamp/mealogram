import 'package:gourmetlog/common/auth/TokenManager.dart';
import 'package:http_interceptor/http_interceptor.dart';

class HttpInterceptor implements InterceptorContract{
  static final int REFRESH_TOKEN_EXPIRE_PERIOD = 111111;
  static final int ACCESS_TOKEN_EXPIRE_PERIOD = 111111;

  @override
  Future<RequestData> interceptRequest({RequestData data}) async {
    try {
      DateTime currentDate = DateTime.now();
      String refreshToken = await TokenManager.instance.getRefreshToken();
      String accessToken = await TokenManager.instance.getAccessToken();
      DateTime refreshTokenTime = await TokenManager.instance.getRefreshTokenTime();
      DateTime accessTokenTime = await TokenManager.instance.getAccessTokenTime();

      //토큰 발급시간과 현재시간 차이 계산
      Duration refreshTokenDiff = currentDate.difference(refreshTokenTime);

      //토큰 만료여부 체크
      Duration accessTokenDiff = currentDate.difference(accessTokenTime);
      if(refreshTokenDiff.inMilliseconds >= REFRESH_TOKEN_EXPIRE_PERIOD) {
        //Refresh Token 만료 시 로그아웃
      }

      if(accessTokenDiff.inMilliseconds >= ACCESS_TOKEN_EXPIRE_PERIOD) {
        //Access Token 및 Refresh 토큰 초기화
      }

      //헤더에 access Token 추가
      data.headers['authorization'] = accessToken;
      data.headers['content-type'] = "application/json; charset=UTF-8";
      return data;
    } catch(error) {
        print(error);
      return null;
    }
  }

  @override
  Future<ResponseData> interceptResponse({ResponseData data}) async {
    return data;
  }

}