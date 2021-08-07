import * as cdk from "@aws-cdk/core";
import * as ec2 from "@aws-cdk/aws-ec2";
import * as ecs from "@aws-cdk/aws-ecs";
import { ExecSyncOptions } from "child_process";

export interface ServerStackProps extends cdk.StackProps {
  prefix: string;
  environment: string;
}

export class ServerStack extends cdk.Stack {
  private readonly vpc: ec2.Vpc;
  private readonly cluster: ecs.Cluster;
  private readonly securityGroup: ec2.SecurityGroup;

  constructor(scope: cdk.Construct, id: string, props: ServerStackProps) {
    super(scope, id, props);
    const prefix = props.prefix ? props.prefix : "";

    this.vpc = new ec2.Vpc(this, `${prefix}-vpc`, {
      maxAzs: 2,
      subnetConfiguration: [
        {
          cidrMask: 24,
          name: `${prefix}-pub`,
          subnetType: ec2.SubnetType.PUBLIC,
        },
      ],
    });

    this.cluster = new ecs.Cluster(this, ` ${prefix}-vpc`, { vpc: this.vpc });

    this.securityGroup = new ec2.SecurityGroup(
      this,
      ` ${prefix}-securityGroup`,
      {
        vpc: this.vpc,
      }
    );
    this.securityGroup.addIngressRule(
      ec2.Peer.ipv4("0.0.0.0/0"),
      ec2.Port.tcp(3000)
    );
  }
}
