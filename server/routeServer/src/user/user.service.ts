import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { UserMaster } from './dto/UserMaster.dto';

@Injectable()
export class UserSerivce {

    private readonly UserMaster: UserMaster[] = [];

  getHello(): string {
    return 'Hello World!';
  }

  getFirstMethod(): string {
    return 'My First Method';
  }
}
