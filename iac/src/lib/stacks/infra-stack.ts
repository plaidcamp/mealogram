import * as cdk from '@aws-cdk/core';

export interface InfraProps extends cdk.StackProps {
  prefix: string;
  environment: string;
}

export class InfraStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: InfraProps) {
    super(scope, id, props);

    // The code that defines your stack goes here
  }
}
