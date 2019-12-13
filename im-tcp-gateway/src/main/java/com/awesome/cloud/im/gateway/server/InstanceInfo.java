package com.awesome.cloud.im.gateway.server;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * projectName：imcloud
 * className ：InstanceInfo
 * class desc：TODO
 * createTime：2019/12/12 10:29 PM
 * creator：awesome
 */
//@Slf4j
@Data
public class InstanceInfo {
    private static final String VERSION_UNKNOWN = "unknown";
    public static final int DEFAULT_PORT = 7001;
    public static final int DEFAULT_SECURE_PORT = 7002;
    public static final int DEFAULT_COUNTRY_ID = 1;
    private volatile String instanceId;
    private volatile String appName;
    private volatile String appGroupName;
    private volatile String ipAddr;
    private static final String SID_DEFAULT = "na";
    /** @deprecated */
    @Deprecated
    private volatile String sid;
    private volatile int port;
    private volatile int securePort;
    private volatile String homePageUrl;
    private volatile String statusPageUrl;
    private volatile String healthCheckUrl;
    private volatile String secureHealthCheckUrl;
    private volatile String vipAddress;
    private volatile String secureVipAddress;
    private String statusPageRelativeUrl;
    private String statusPageExplicitUrl;
    private String healthCheckRelativeUrl;
    private String healthCheckSecureExplicitUrl;
    private String vipAddressUnresolved;
    private String secureVipAddressUnresolved;
    private String healthCheckExplicitUrl;
    /** @deprecated */
    @Deprecated
    private volatile int countryId;
    private volatile boolean isSecurePortEnabled;
    private volatile boolean isUnsecurePortEnabled;
    private volatile String hostName;
    private volatile boolean isInstanceInfoDirty;
    private volatile Boolean isCoordinatingDiscoveryServer;
    private volatile Map<String, String> metadata;
    private volatile Long lastUpdatedTimestamp;
    private volatile Long lastDirtyTimestamp;
    private volatile String asgName;
    private String version;


}
