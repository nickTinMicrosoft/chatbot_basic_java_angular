package com.nyacs.chatbot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "azure.openai")
public class AzureOpenAIConnectionProperties {
    private String endpoint;
    private String key;
    private String deploymentName;

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getDeploymentName() { return deploymentName; }
    public void setDeploymentName(String deploymentName) { this.deploymentName = deploymentName; }
}
