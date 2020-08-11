package edu.neu.csye6200.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author arronshentu
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<Mapper extends BaseMapper<T>, T> extends ServiceImpl<Mapper, T>
  implements BaseService<T> {

  @Autowired
  public Mapper repository;

}
