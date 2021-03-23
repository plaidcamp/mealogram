import { Inject, Injectable } from '@nestjs/common'
import * as dotenv from 'dotenv'
import * as fs from 'fs'
import * as path from 'path'
import { CONFIG_OPTIONS } from './constants'
import { ConfigOptions, EnvConfig } from './interface'

/**
 * Source : config.service.ts
 * author : 박준서
 * writdate : 2021.03.23
 * detail : Config 설정 주입을 위한 Dynamic Module (Serivce)
 */

@Injectable()
export class ConfigService {
    // interface에서 만들어 준 Type interface
    private readonly envConfig: EnvConfig;

    // Constructor
    constructor(@Inject(CONFIG_OPTIONS) options: ConfigOptions) {
        const filePath = `${process.env.NODE_ENV || 'dev'}.env` // properties 이름 지정
        const envFile  = path.resolve(__dirname, '../../',options.folder, filePath) // 해당파일 load
        this.envConfig = dotenv.parse(fs.readFileSync(envFile)) // dotenv로 properties 파일 parsing
    }

    // Get
    // get method로 opation 가져가면 됨
    get(key: string): string {
        return this.envConfig[key]
    }
}
