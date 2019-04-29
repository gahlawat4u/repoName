package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class CoverSheetVo extends BaseVo {
    private static final long serialVersionUID = 8189855769835783509L;

    private Long coverSheetId;

    private String fileName;

    private Date createDate;

    public Long getCoverSheetId() {
        return coverSheetId;
    }

    public void setCoverSheetId(Long coverSheetId) {
        this.coverSheetId = coverSheetId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CoverSheetVo [coverSheetId=" + coverSheetId + ", fileName=" + fileName + ", createDate=" + createDate + "]";
    }
}