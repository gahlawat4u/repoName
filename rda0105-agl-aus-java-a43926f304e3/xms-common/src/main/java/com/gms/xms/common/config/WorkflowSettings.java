/**
 *
 */
package com.gms.xms.common.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Posted from WorkflowSettings.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:48:57 PM
 */
@XmlRootElement(name = "workflow-setting")
public class WorkflowSettings {
    private String senderEmailPassword;

    @XmlElement(name = "sender-email-password")
    public void setSenderEmailPassword(String senderEmailPassword) {
        this.senderEmailPassword = senderEmailPassword;
    }

    public String getSenderEmailPassword() {
        return senderEmailPassword;
    }

}
