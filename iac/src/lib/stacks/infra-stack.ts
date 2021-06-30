import * as cdk from '@aws-cdk/core';

export interface InfraProps extends cdk.StackProps {
  prefix: string;
  environment: string;
}

export class InfraStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: InfraProps) {
    super(scope, id, props);

    /**
     * 📝 Ec2 Stack
     * @description @aws-cdk/aws-ec2 를 사용하여 spring boot 인스턴스를 기동한다.
     **/

    /**
     * 📝 Database Stack
     * @description @aws-cdk/aws-rds를 사용하여 rdb를 생성한다.
     **/


  }
}
