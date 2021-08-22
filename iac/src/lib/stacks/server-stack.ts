import * as cdk from "@aws-cdk/core";
import * as ec2 from "@aws-cdk/aws-ec2";
import * as ecs from "@aws-cdk/aws-ecs";
import * as rds from "@aws-cdk/aws-rds";
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

    const prefix = props.prefix;

    const vpc = new ec2.Vpc(this, "my-cdk-vpc", {
      cidr: "10.0.0.0/16",
      natGateways: 0,
      maxAzs: 3,
      subnetConfiguration: [
        {
          name: "public-subnet-1",
          subnetType: ec2.SubnetType.PUBLIC,
          cidrMask: 24,
        },
        {
          name: "isolated-subnet-1",
          subnetType: ec2.SubnetType.ISOLATED,
          cidrMask: 28,
        },
      ],
    });

    // 👇 create a security group for the EC2 instance
    const ec2InstanceSG = new ec2.SecurityGroup(this, "ec2-instance-sg", {
      vpc,
    });

    ec2InstanceSG.addIngressRule(
      ec2.Peer.anyIpv4(),
      ec2.Port.tcp(22),
      "allow SSH connections from anywhere"
    );

    // 👇 create the EC2 instance
    const ec2Instance = new ec2.Instance(this, "ec2-instance", {
      vpc,
      vpcSubnets: {
        subnetType: ec2.SubnetType.PUBLIC,
      },
      securityGroup: ec2InstanceSG,
      instanceType: ec2.InstanceType.of(
        ec2.InstanceClass.T2,
        ec2.InstanceSize.MICRO
      ),
      machineImage: new ec2.AmazonLinuxImage({
        generation: ec2.AmazonLinuxGeneration.AMAZON_LINUX_2,
      }),
      keyName: "mealogram",
    });

    // 👇 create RDS Instance
    const dbInstance = new rds.DatabaseInstance(this, "db-instance", {
      vpc,
      vpcSubnets: {
        subnetType: ec2.SubnetType.ISOLATED,
      },
      engine: rds.DatabaseInstanceEngine.postgres({
        version: rds.PostgresEngineVersion.VER_13_1,
      }),
      instanceType: ec2.InstanceType.of(
        ec2.InstanceClass.BURSTABLE3,
        ec2.InstanceSize.MICRO
      ),
      credentials: rds.Credentials.fromGeneratedSecret("postgres"),
      multiAz: false,
      allocatedStorage: 100,
      maxAllocatedStorage: 105,
      allowMajorVersionUpgrade: false,
      autoMinorVersionUpgrade: true,
      deleteAutomatedBackups: true,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      deletionProtection: false,
      databaseName: "mealogram",
      publiclyAccessible: false,
    });

    dbInstance.connections.allowFrom(ec2Instance, ec2.Port.tcp(5432));

    new cdk.CfnOutput(this, "dbEndpoint", {
      value: dbInstance.instanceEndpoint.hostname,
    });

    new cdk.CfnOutput(this, "secretName", {
      // eslint-disable-next-line @typescript-eslint/no-non-null-asserted-optional-chain
      value: dbInstance.secret?.secretName!,
    });
  }
}
