package com.zfj.xmy.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2017-02-06 9:43 </p>
 * <p>Modification Record 1: </p>
 * <pre>
 *  Modified Date：
 *  Version：
 *  Modifier：
 *  Modification Content：
 * </pre>
 * <p>Modification Record 2：…</p>
 *
 * @author <a href="wubin3347@gmail.com">wub</a>
 * @version 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataPage<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  //当前页
  private int pageNum;
  //每页的数量
  private int pageSize;
  //总记录数
  private long total;
  //总页数
  private int pages;
  //结果集
  private List<T> list;

  public DataPage(){
  }

  public DataPage(org.springframework.data.domain.Page<T> page){
    this.pageNum = page.getNumber() < 0 ? 1 : page.getNumber() + 1;
    this.pageSize = page.getSize();
    this.total = page.getTotalElements();
    this.pages = page.getTotalPages() < 0 ? 1 : page.getTotalPages();
    this.list = page.getContent();
  }

  public static <X> DataPage<X> covert(DataPage page, List<X> data){
    page.setList(data);
    return page;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }














  public static <T> DataPage<T> getEmpty(){
    return empty;
  }

  private static final DataPage empty = new EmptyDataPage<>();

  private static class EmptyDataPage<T> extends DataPage<T>{
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private List<T> list = Collections.emptyList();

    @Override
    public int getPageNum() {
      return pageNum;
    }

    @Override
    public void setPageNum(int pageNum) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int getPageSize() {
      return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
      throw new UnsupportedOperationException();
    }

    @Override
    public long getTotal() {
      return total;
    }

    @Override
    public void setTotal(long total) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int getPages() {
      return pages;
    }

    @Override
    public void setPages(int pages) {
      throw new UnsupportedOperationException();
    }

    @Override
    public List<T> getList() {
      return list;
    }

    @Override
    public void setList(List<T> list) {
      throw new UnsupportedOperationException();
    }
  }
}
