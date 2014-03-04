package com.makotan.tools;

import java.awt.*;
import java.util.Collection;
import java.util.List;

/**
 * User: kuroeda.makoto
 * Date: 13/11/15
 * Time: 15:14
 */
public class AddColorFilter {
    public Template process(Template template) {
        parameterColor(template.getParameters());
        conditionColor(template.getConditions());
        mappingColor(template.getMappings());
        resourcesColor(template.getResources());
        outputColor(template.getOutputs());
        return template;
    }

    void parameterColor(List<Node> nodeList) {
        if(nodeList == null) {
            return;
        }
        for (Node node : nodeList) {
            node.setColor(new Color(Color.LIGHT_GRAY.getRGB()));
            edgeColor(node.getEdgeList());
        }
    }

    void conditionColor(List<Node> nodeList) {
        if(nodeList == null) {
            return;
        }
        for (Node node : nodeList) {
            node.setColor(new Color(Color.LIGHT_GRAY.getRGB()));
            edgeColor(node.getEdgeList());
        }
    }

    void mappingColor(List<Node> nodeList) {
        if(nodeList == null) {
            return;
        }
        for (Node node : nodeList) {
            node.setColor(new Color(Color.gray.getRGB()));
            edgeColor(node.getEdgeList());
        }
    }

    void outputColor(List<Node> nodeList) {
        if(nodeList == null) {
            return;
        }
        for (Node node : nodeList) {
            node.setColor(new Color(Color.DARK_GRAY.getRGB()));
            edgeColor(node.getEdgeList());
        }
    }

    private Color computeColor = new Color(0xF4 , 0x86 , 0x35);
    private Color storageColor = new Color(0xE2 , 0x54 , 0x44);
    private Color databaseColor = new Color(0x2273B8);
    private Color networkingColor = new Color(0xDAA83F);
    private Color contentDeliveryColor = new Color(0xDAA83F);
    private Color applicationServiceColor = new Color(0x806DAF);
    private Color deploymentAndManagementColor = new Color(0x789E3F);
    private Color monitoringColor = new Color(0x789E3F);
    private Color nonServiceColor = new Color(0xF48534);

    void resourcesColor(List<Node> nodeList) {
        if(nodeList == null) {
            return;
        }
        for (Node node : nodeList) {
            if (node.getTypeName().startsWith("AWS::AutoScaling::")) {
                node.setColor(computeColor);

            } else if (node.getTypeName().startsWith("AWS::CloudFormation::")) {
                node.setColor(deploymentAndManagementColor);

            } else if (node.getTypeName().startsWith("AWS::CloudFront::")) {
                node.setColor(contentDeliveryColor);

            } else if (node.getTypeName().startsWith("AWS::CloudWatch::")) {
                node.setColor(monitoringColor);

            } else if (node.getTypeName().startsWith("AWS::DynamoDB::")) {
                node.setColor(databaseColor);

            } else if (node.getTypeName().startsWith("AWS::ElastiCache::")) {
                node.setColor(databaseColor);

            } else if (node.getTypeName().startsWith("AWS::ElasticBeanstalk::")) {
                node.setColor(deploymentAndManagementColor);

            } else if (node.getTypeName().startsWith("AWS::ElasticLoadBalancing::")) {
                node.setColor(networkingColor);

            } else if (node.getTypeName().startsWith("AWS::IAM::")) {
                node.setColor(deploymentAndManagementColor);

            } else if (node.getTypeName().startsWith("AWS::OpsWorks::")) {
                node.setColor(deploymentAndManagementColor);

            } else if (node.getTypeName().startsWith("AWS::Redshift::")) {
                node.setColor(databaseColor);

            } else if (node.getTypeName().startsWith("AWS::RDS::")) {
                node.setColor(databaseColor);

            } else if (node.getTypeName().startsWith("AWS::Route53::")) {
                node.setColor(networkingColor);

            } else if (node.getTypeName().startsWith("AWS::S3::")) {
                node.setColor(storageColor);

            } else if (node.getTypeName().startsWith("AWS::SDB::")) {
                node.setColor(databaseColor);

            } else if (node.getTypeName().startsWith("AWS::SNS::")) {
                node.setColor(applicationServiceColor);

            } else if (node.getTypeName().startsWith("AWS::SQS::")) {
                node.setColor(applicationServiceColor);

            } else if (node.getTypeName().startsWith("AWS::EC2::Instance")) {
                node.setColor(computeColor);

            } else if (node.getTypeName().startsWith("AWS::EC2::")) {
                node.setColor(computeColor);

            } else {
                node.setColor(nonServiceColor);

            }
            edgeColor(node.getEdgeList());
        }
    }

    void edgeColor(Collection<Edge> edges) {
        if(edges == null) {
            return;
        }
        for (Edge edge : edges) {
            switch (edge.getTitle()) {
                case "Ref":
                    edge.setColor(new Color(5 , 255 , 5));
                    break;
                case "DependsOn":
                    edge.setColor(new Color(5 , 5 ,255));
                    break;
                default:
                    edge.setColor(new Color(5 , 5 ,5));
            }
        }
    }


}
