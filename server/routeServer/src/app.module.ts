import { Module } from '@nestjs/common';
import { HomeController } from './controller/home.controller';
import { BoardController } from './controller/board.controller';
import { AppService } from './app.service';

@Module({
  imports: [],
  controllers: [HomeController, BoardController],
  providers: [AppService],
})
export class AppModule {}
