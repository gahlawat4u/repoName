package com.gms.xms.txndb.vo;

/**
 * Posted from CurrencyVo
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class CurrencyVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 5172818474849847360L;

    private Long id;

    private String code;

    private String symbol;

    private String name;

    private String encodeName;

    private Integer localizationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEncodeName() {
        return encodeName;
    }

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName == null ? null : encodeName.trim();
    }

    public Integer getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Integer localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "CurrencyVo [id=" + id + ", code=" + code + ", symbol=" + symbol + ", name=" + name + ", encodeName=" + encodeName + ", localizationId=" + localizationId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((encodeName == null) ? 0 : encodeName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CurrencyVo other = (CurrencyVo) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (encodeName == null) {
            if (other.encodeName != null)
                return false;
        } else if (!encodeName.equals(other.encodeName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        return true;
    }

}