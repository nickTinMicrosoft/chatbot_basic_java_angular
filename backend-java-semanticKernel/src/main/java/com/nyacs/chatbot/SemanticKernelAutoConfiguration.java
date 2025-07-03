package com.nyacs.chatbot;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.textcompletion.OpenAITextGenerationService;
import com.microsoft.semantickernel.services.textcompletion.TextGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

@AutoConfiguration
@EnableConfigurationProperties(AzureOpenAIConnectionProperties.class)
public class SemanticKernelAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SemanticKernelAutoConfiguration.class);

    private static String setModelID(AzureOpenAIConnectionProperties connectionProperties) {
        String modelId;
        if (connectionProperties.getDeploymentName() == null) {
            modelId = "gpt-4o-mini";
            LOGGER.warn("No deployment name specified, using default model id: " + modelId);
        } else {
            modelId = connectionProperties.getDeploymentName();
            LOGGER.info("Using model id: " + modelId);
        }
        return modelId;
    }

    @Bean
    @ConditionalOnClass(OpenAIAsyncClient.class)
    @ConditionalOnMissingBean
    public OpenAIAsyncClient openAIAsyncClient(AzureOpenAIConnectionProperties connectionProperties) {
        Assert.hasText(connectionProperties.getEndpoint(), "Azure OpenAI endpoint must be set");
        Assert.hasText(connectionProperties.getKey(), "Azure OpenAI key must be set");
        return new OpenAIClientBuilder()
            .endpoint(connectionProperties.getEndpoint())
            .credential(new AzureKeyCredential(connectionProperties.getKey()))
            .buildAsyncClient();
    }

    @Bean
    public Kernel semanticKernel(OpenAIAsyncClient client, AzureOpenAIConnectionProperties connectionProperties) {
        return Kernel.builder()
            .withAIService(TextGenerationService.class,
                OpenAITextGenerationService.builder()
                    .withModelId(setModelID(connectionProperties))
                    .withOpenAIAsyncClient(client)
                    .build())
            .build();
    }
}
