package com.xuecheng.learning.dao;

import com.xuecheng.framework.domain.learning.XcLearningCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearingCourseJPA extends JpaRepository<XcLearningCourse, String> {
}
