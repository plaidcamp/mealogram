import { TypeOrmModule } from '@nestjs/typeorm'
import { configModule, ConfigModuleOptions } from '../config/config.module'
import * as path from 'path'

const configOption: ConfigModuleOptions = { folder: '../configs' };
const config = configModule.register( configOption );

export const databaseConnections = [
    TypeOrmModule.forRoot({
        type: 'mysql',
        host: '',
        port: 3306,
        username: '',
        password: '',
        database: '',
        name: '',
        entities: [
            `${path.join('../../', 'src', 'modules', 'shared')}/**/*.dto{.ts,.js}`
        ],
        synchronize: false
    })
]