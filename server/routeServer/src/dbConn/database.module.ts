import { DynamicModule, Module }  from '@nestjs/common'
import { TypeOrmModule } from '@nestjs/typeorm'
import { databaseConnections } from './database-connection'

@Module({})
export class DatabaseModule {
    static forRoot(): DynamicModule {
        return {
            module: DatabaseModule,
            providers: [
                {
                    provide: ,
                    useValue:
                },
            ]
        }
    }
}