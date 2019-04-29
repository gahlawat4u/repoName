/**
 *
 */
package com.gms.xms.common.config;


import com.gms.xms.common.config.dto.LogConfig;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * Posted from AppSettings.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:49:10 PM
 */
@XmlRootElement(name = "log-setting")
public class AppLogSettings {
    private List<LogConfig> logConfigs;

    public List<LogConfig> getLogConfigs() {
        return logConfigs;
    }

    @XmlElementWrapper(name = "event-log")
    @XmlElement(name = "event")
    public void setLogConfigs(List<LogConfig> logConfigs) {
        this.logConfigs = logConfigs;
    }

    @Override
    public String toString() {
        return "AppLogSettings [logConfigs=" + logConfigs + "]";
    }

}
