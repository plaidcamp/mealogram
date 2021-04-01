import { DynamicModule, Module }  from '@nestjs/common'
import { ConfigModule } from '@nestjs/config'
import { ConfigService } from './config.service'
import { CONFIG_OPTIONS } from './constants'

export interface ConfigModuleOptions {
    folder: string;
}

@Module({})
export class configModule {
    static register(options: ConfigModuleOptions): DynamicModule {
        return {
            module: ConfigModule,
            providers: [
                {
                    provide: CONFIG_OPTIONS,
                    useValue: options
                },
                ConfigService,
            ],
            exports: [ConfigService]
        }
    }
}