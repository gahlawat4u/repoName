package com.gms.xms.txndb.vo.admin.note;

import com.gms.xms.txndb.vo.NoteVo;

/**
 * Posted from Apr 26, 2016 1:52:05 PM
 * <p>
 * Author dattrinh
 */
public class NoteAndFollowUpVo extends NoteVo {

    private static final long serialVersionUID = 1L;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
