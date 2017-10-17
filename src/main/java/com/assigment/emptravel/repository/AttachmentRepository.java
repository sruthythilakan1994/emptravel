package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.Attachment;
import com.assigment.emptravel.model.SkillSet;

@Repository("attachmentRepository")
public interface AttachmentRepository extends  JpaRepository<Attachment ,Integer> {

}
