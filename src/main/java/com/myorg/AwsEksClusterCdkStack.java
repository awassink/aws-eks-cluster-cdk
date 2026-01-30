package com.myorg;

import software.amazon.awscdk.services.ec2.InstanceClass;
import software.amazon.awscdk.services.ec2.InstanceSize;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.eks.KubernetesVersion;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.eks.Cluster;
import software.amazon.awscdk.cdk.lambdalayer.kubectl.v32.KubectlV32Layer;

public class AwsEksClusterCdkStack extends Stack {
    public AwsEksClusterCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AwsEksClusterCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // Define EKS Cluster with ManagedNodeGroup
        Cluster ngpEksCluster = Cluster.Builder.create(this, "NgpEksCluster")
                .version(KubernetesVersion.V1_32)
                .kubectlLayer(new KubectlV32Layer(this,"kubectl"))
                .defaultCapacity(1)
                .defaultCapacityInstance(InstanceType.of(InstanceClass.T3, InstanceSize.MEDIUM))
                .build();
    }
}
