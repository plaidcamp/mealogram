/**
 * Source : envConfig.interface
 * author : 박준서
 * writdate : 2021.03.23
 * detail : .env 파일 읽어올 때의 pattern 설정
 *          이걸 해줘야 Service에서 Type 지정이 가능하다.
 */

export interface EnvConfig {
    // key(String) : value(String)
    [key: string]: string;
}