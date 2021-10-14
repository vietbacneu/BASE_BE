package com.example.qlbhbe.util;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class PageCustomImpl<T> {

  List<T> content;
  private int number;
  private long numberOfElements;
  private int size;
  private long totalElements;
  private long totalPages;



  public PageCustomImpl(List<T> content, long total, Pageable pageable) {
    this.content = content;
    this.totalElements = total;
    this.size = pageable.getPageSize();
    this.numberOfElements = content.size();
    this.number = pageable.getPageNumber();
    this.totalPages = total % size == 0 ? (total / size) : (total / size) + 1;
  }
}
