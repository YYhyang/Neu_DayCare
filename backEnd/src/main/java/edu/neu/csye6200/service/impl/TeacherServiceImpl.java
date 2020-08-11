package edu.neu.csye6200.service.impl;

import org.springframework.stereotype.Service;

import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.TeacherMapper;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.service.TeacherService;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherMapper, Teacher> implements TeacherService {}
