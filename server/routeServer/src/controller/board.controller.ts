import { Controller, Get } from '@nestjs/common';
import { AppService } from '../app.service';

@Controller('/board')
export class BoardController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get('/hello')
  getFirst(): string {
    return this.appService.getFirstMethod();
  }
}
