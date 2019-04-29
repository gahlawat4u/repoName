package com.gms.xms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Paging
 * <p>
 * Author DatTV Date Mar 24, 2015
 */
public class Paging<T> {
    private int currentPage = 1;
    private int firstPage = 1;
    private int lastPage = 1;
    private int totalPage;
    private List<T> records;
    private long totalRecords;
    private int nlinks = 10;
    private int pageSize = 10;
    private List<Integer> pageRange;
    private boolean hasNext = true;
    private boolean hasPrev = true;

    public Paging() {
        currentPage = 1;
        firstPage = 1;
        lastPage = 1;
        nlinks = 10;
        pageSize = 10;
        hasNext = true;
        hasPrev = true;
    }

    public void doPaging() {
        this.totalPage = (int) Math.ceil((double) totalRecords / pageSize);

        if (currentPage >= totalPage) {
            currentPage = totalPage;
            hasNext = false;
            lastPage = totalPage;
        } else {
            hasNext = true;
            lastPage = currentPage + nlinks;
            if (lastPage >= totalPage) {
                lastPage = totalPage;
            }
        }

        if (currentPage <= 1) {
            currentPage = 1;
            hasPrev = false;
            firstPage = 1;
        } else {
            hasPrev = true;
            firstPage = currentPage - nlinks;
            if (firstPage <= 1) {
                firstPage = 1;
            }
        }

        pageRange = new ArrayList<Integer>();
        for (int i = firstPage; i <= lastPage; i++) {
            pageRange.add(i);
        }
    }

    public Paging(int currentPage, int nlinks, int pageSize, long totalRecords) {
        super();
        this.currentPage = currentPage;
        this.nlinks = nlinks;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        doPaging();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getNlinks() {
        return nlinks;
    }

    public void setNlinks(int nlinks) {
        this.nlinks = nlinks;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Integer> getPageRange() {
        return pageRange;
    }

    public void setPageRange(List<Integer> pageRange) {
        this.pageRange = pageRange;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public boolean hasPrev() {
        return hasPrev;
    }

    public long getStartRecord() {
        return (currentPage - 1) * pageSize + 1;
    }

    public long getEndRecord() {
        long endRecord = currentPage * pageSize;
        if (endRecord > totalRecords) {
            endRecord = totalRecords;
        }
        return endRecord;
    }

    @Override
    public String toString() {
        return "Paging [currentPage=" + currentPage + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", totalPage=" + totalPage + ", records=" + records + ", totalRecords=" + totalRecords + ", nlinks=" + nlinks + ", pageSize=" + pageSize + ", pageRange=" + pageRange + ", hasNext=" + hasNext + ", hasPrev=" + hasPrev + "]";
    }
}
