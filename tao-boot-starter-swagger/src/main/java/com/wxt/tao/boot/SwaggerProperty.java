
package com.wxt.tao.boot;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 定义Swagger文档配置。
 */
@ConfigurationProperties(prefix = "tao.boot.swagger")
public class SwaggerProperty {
    /**
     * 基础扫描包
     */
    private String basePackage;

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 团队url
     */
    private String termsOfServiceUrl;
    /**
     * api版本
     */
    private String version;
    /**
     * license信息
     */
    private String license;
    /**
     * licenseUrl地址
     */
    private String licenseUrl;

    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系url
     */
    private String contactUrl;
    /**
     * 联系邮箱
     */
    private String contactEmail;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}

