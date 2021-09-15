#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { STACK_NAME } from './config';
import { InfraStack } from '../lib/stacks/infra-stack';

require("dotenv").config();
process.env.STACK_ENV = process.env.STACK_ENV || "prod";

//'ex. dev-plaidcamp-mealogram'
const prefix = `${process.env.STACK_ENV}-${STACK_NAME}`;

const app = new cdk.App();

new InfraStack(app, `${prefix}-infraStack`, {
  prefix,
  environment: process.env.STACK_ENV
});
