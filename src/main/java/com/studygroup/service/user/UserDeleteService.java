package com.studygroup.service.user;

import com.studygroup.service.common.DeleteService;
import org.springframework.stereotype.Service;

@Service
public interface UserDeleteService<T> extends DeleteService<T> {
  void delete(T memberSpecification);
}
