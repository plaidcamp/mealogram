import * as cdk from "@aws-cdk/core";
import * as rds from "@aws-cdk/aws-rds";

export interface RdsStackProps extends cdk.StackProps {
  prefix: string;
  environment: string;
}

export class RdsStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props: RdsStackProps) {
    super(scope, id, props);
  }
}
