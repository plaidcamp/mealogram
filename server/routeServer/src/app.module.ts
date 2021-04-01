import { Module } from '@nestjs/common';
import { HomeController } from './controller/home.controller';
import { BoardController } from './controller/board.controller';
import { AppService } from './app.service';
import { configModule } from './config/config.module';

@Module({
  imports: [configModule.register({ folder: './configs' })],
  controllers: [HomeController, BoardController],
  providers: [AppService],
})
export class AppModule {}
