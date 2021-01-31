package com.redditcooll.businessApplication.configuration

import com.redditcooll.businessApplication.to.ResultDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.io.Serializable

@Configuration
class RestTemplateConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }
}

@Component
class RestTemplateClient {

    // TODO: add logger, add logger config xml
    val logger: org.slf4j.Logger = LoggerFactory.getLogger(RestTemplateClient::class.java)

    @Autowired
    lateinit var restTemplate: RestTemplate

    fun <T : Serializable?> getForm(params: Map<String, String>, url: String, t:T) : ResultDTO<T>? {
        logger.info("request url===========:{}", url)
        logger.info("Request params===========:{}", params)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val requestParams = LinkedMultiValueMap<String, String>()
        params.forEach(requestParams::add)
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(requestParams, httpHeaders)
        return restTemplate.getForObject(url, httpEntity, ResultDTO<T>().javaClass)
    }

    fun <T> getString(url: String, t:T) : String {
        logger.info("request url===========:{}", url)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        val requestParams = LinkedMultiValueMap<String, String>()
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(requestParams, httpHeaders)
        return restTemplate.getForObject(url, HttpMethod.GET, httpEntity, String.javaClass)
    }

    fun <T : Serializable?> postForm(params: Map<String, String>, url: String, t:T) : ResultDTO<T>? {
        logger.info("request url===========:{}", url)
        logger.info("Request params===========:{}", params)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val requestParams = LinkedMultiValueMap<String, String>()
        params.forEach(requestParams::add)
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(requestParams, httpHeaders)
        return restTemplate.postForObject(url, httpEntity, ResultDTO<T>().javaClass)
    }

    fun <T : Serializable?> postFormRequestBody(requestBody: String, url: String, t:T) : ResultDTO<T>? {
        logger.info("request url===========:{}", url)
        logger.info("Request body===========:{}", requestBody)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        val httpEntity = HttpEntity<String>(requestBody, httpHeaders)
        return restTemplate.postForObject(url, httpEntity, ResultDTO<T>().javaClass)
    }

    fun <T> putForm(requestBody: String, url: String, t:T ): ResponseEntity<String.Companion> {
        logger.info("request url===========:{}", url)
        logger.info("Request body===========:{}", requestBody)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        val httpEntity = HttpEntity<String>(requestBody, httpHeaders)
        return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.javaClass, requestBody)
    }
}