import * as cdk from "@aws-cdk/core";
import { ServerStack } from "./server-stack";

export interface InfraProps extends cdk.StackProps {
  prefix: string;
  environment: string;
}

export class InfraStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: InfraProps) {
    super(scope, id, props);

    const prefix = props?.prefix || "";

    /**
     * 📝 Server Stack
     * @description @aws-cdk/aws-ec2 를 사용하여 spring boot 인스턴스를 기동한다.
     **/

    const serverStack = new ServerStack(this, `${prefix}-serverStack`, {
      stackName: `${prefix}-serverStack`,
      environment: this.environment,
      prefix,
    });
  }
}
